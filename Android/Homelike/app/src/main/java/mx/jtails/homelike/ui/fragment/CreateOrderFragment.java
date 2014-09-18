package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.CantidadPago;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.InsertOrderRequest;
import mx.jtails.homelike.request.ListProductsRequest;
import mx.jtails.homelike.ui.CheckOrderActivity;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.ui.adapter.ProductsAdapter;
import mx.jtails.homelike.ui.fragment.dialog.ConfirmOrderDialog;
import mx.jtails.homelike.ui.widget.OrderProductView;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class CreateOrderFragment extends Fragment
    implements ListProductsRequest.ListProductsResponseHandler,
    OrderProductView.OnProductQuantityChangedListener,
    ConfirmOrderDialog.ConfirmOrderDialogCallbacks,
    InsertOrderRequest.OnInsertOrderResponseHandler {

    private enum ContentDisplayMode {
        LOAD, CONTENT, CONTENT_EMPTY;
    }

    private ListProductsRequest mProductsRequest;

    private Proveedor mProvider;
    private List<Producto> mProducts = new ArrayList<Producto>();
    private Map<Integer, Integer> mSubtotals = new HashMap<Integer, Integer>();
    private Map<Producto, Integer> mOrder = new LinkedHashMap<Producto, Integer>();

    private ImageView mProviderLogo;
    private View mLayoutContent;
    private ProgressBar mProgress;
    private TextView mLblEmpty;
    private TextView mLblTotal;

    private AbsListView mListView;
    private ProductsAdapter mProductsAdapter;

    private DisplayImageOptions mLoaderOptions;

    private ProgressDialog mCreatingDialog;

    private int mAddressId;
    private int mServiceId;
    private Direccion mAddress;

    private static final String ARG_ADDRESS_ID = "arg_address_id";
    private static final String ARG_SERVICE_ID = "arg_service_id";

    public static CreateOrderFragment getInstance(int addressId, int serviceId){
        Bundle extras = new Bundle();
        extras.putInt(ARG_ADDRESS_ID, addressId);
        extras.putInt(ARG_SERVICE_ID, serviceId);

        CreateOrderFragment fragment = new CreateOrderFragment();
        fragment.setArguments(extras);

        return fragment;
    }

    public CreateOrderFragment(){
        this.mLoaderOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_homelike_splash_logo)
                .showImageForEmptyUri(R.drawable.ic_homelike_splash_logo)
                .showImageOnFail(R.drawable.ic_homelike_splash_logo)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loadAddress(savedInstanceState == null ? this.getArguments() : savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_order, container, false);
    }

    private void loadAddress(Bundle args){
        this.mAddressId = args.getInt(ARG_ADDRESS_ID);
        this.mServiceId = args.getInt(ARG_SERVICE_ID);

        this.mAddress = HomelikeDBManager.getDBManager().getAddress(this.mAddressId);
        if(this.mAddress == null){
            Toast.makeText(this.getActivity(), "Failed to recover address with id: "
                    + this.mAddressId, Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mProviderLogo = (ImageView) view.findViewById(R.id.img_provider_logo);
        this.mLayoutContent = view.findViewById(R.id.layout_products_content);
        this.mListView = (ListView) view.findViewById(R.id.list_products);
        this.mProgress = (ProgressBar) view.findViewById(R.id.progress_products);
        this.mLblEmpty = (TextView) view.findViewById(R.id.lbl_empty);
        this.mLblTotal = (TextView) view.findViewById(R.id.lbl_total);

        this.mProductsAdapter = new ProductsAdapter(this.getActivity(),
                this.mProducts, this);
        this.mListView.setAdapter(this.mProductsAdapter);

        view.findViewById(R.id.btn_cancel_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCancelation();
            }
        });
        view.findViewById(R.id.btn_check_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        this.refreshProviderView(null);
    }

    public void refreshProviderView(Proveedor provider){
        if(provider == null){ return; }
        this.mProvider = provider;

        ImageLoader.getInstance().displayImage(
            this.mProvider.getLogo(), this.mProviderLogo, this.mLoaderOptions);

        this.displayContentMode(ContentDisplayMode.LOAD);
        this.mProductsRequest = new ListProductsRequest(this, this.mProvider.getIdProveedor());
        this.mProductsRequest.executeAsync();
    }

    public void confirmCancelation(){
        new AlertDialog.Builder(this.getActivity())
                .setTitle("Cancel")
                .setMessage("Are you sure you want to cancel this order?")
                .setPositiveButton("Cancel Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((CheckOrderActivity) getActivity()).cancelOrder();
                    }
                })
                .setNegativeButton("Continue", null)
                .show();
    }

    public void confirmOrder(){
        if(!this.buildOrderMap()){
            new AlertDialog.Builder(this.getActivity())
                .setTitle("Empty Order")
                .setMessage("The order is empty, please select your products")
                .setPositiveButton("Ok", null).show();
            return;
        }

        ConfirmOrderDialog confirmDialog = ConfirmOrderDialog.getInstance(
                this.mOrder, this);
        confirmDialog.show(this.getFragmentManager(), null);
    }

    private boolean buildOrderMap(){
        this.mOrder.clear();
        int quantities = 0;

        for(Integer position : this.mSubtotals.keySet()) {
            quantities += this.mSubtotals.get(position);
            this.mOrder.put(this.mProducts.get(position), this.mSubtotals.get(position));
        }

        return quantities > 0;
    }

    @Override
    public void onListProductsResponse(List<Producto> products) {
        this.mProducts = products;
        if(this.mProducts.isEmpty()){
            this.displayContentMode(ContentDisplayMode.CONTENT_EMPTY);
        } else {
            this.displayContentMode(ContentDisplayMode.CONTENT);
        }
    }

    @Override
    public void onProductQuantityChanged(int position, int newQuantity) {
        this.mSubtotals.put(position, newQuantity);
        this.updateTotal();
    }

    private void updateTotal(){
        this.mLblTotal.setText(String.valueOf(this.getOrderTotal()));
    }

    private float getOrderTotal(){
        float total = 0.0f;

        for(Integer position : this.mSubtotals.keySet()) {
            total += this.mSubtotals.get(position)
                    * (float) this.mProducts.get(position).getCostoUnitario();
        }

        return total;
    }

    private void displayContentMode(ContentDisplayMode displayMode){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.GONE);
                this.mProgress.setVisibility(View.VISIBLE);
                break;
            }
            case CONTENT: {
                this.mProgress.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.GONE);
                this.mListView.setVisibility(View.VISIBLE);

                this.mProductsAdapter.updateContent(this.mProducts);
                break;
            }
            case CONTENT_EMPTY: {
                this.mProgress.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.VISIBLE);
                this.mListView.setVisibility(View.GONE);
                break;
            }
        }
    }

    @Override
    public void onConfirmOrderClicked(CantidadPago paymentQuantity) {
        this.mCreatingDialog = ProgressDialog.show(this.getActivity(),
                "New Order", "Please wait...", false, false);
        new InsertOrderRequest(this, this.getActivity(),
                HomelikePreferences.loadInt(HomelikePreferences.ACCOUNT_ID, -1),
                this.mOrder, this.mProvider, paymentQuantity, this.mAddressId).executeAsync();
    }

    @Override
    public void onEditOrderClicked() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_ADDRESS_ID, this.mAddressId);
        outState.putInt(ARG_SERVICE_ID, this.mServiceId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onInsertOrderResponse(Pedido order) {
        this.mCreatingDialog.dismiss();
        if(order == null){
            new AlertDialog.Builder(this.getActivity())
                    .setTitle("Error")
                    .setMessage("Failed to create your order, try again later")
                    .setPositiveButton("Ok", null).show();
            return;
        } else {
            // TODO set current content preference to orders
            Intent intent = new Intent(this.getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.getActivity().startActivity(intent);
        }
    }
}
