package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
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
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.ListProductsRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.ui.adapter.ProductsAdapter;
import mx.jtails.homelike.ui.widget.OrderProductView;
import mx.jtails.homelike.util.HomelikePreferences;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class CreateOrderFragment extends Fragment
    implements ListProductsRequest.ListProductsResponseHandler,
    OrderProductView.OnProductQuantityChangedListener {

    private enum ContentDisplayMode {
        LOAD, CONTENT, CONTENT_EMPTY;
    }

    private ListProductsRequest mProductsRequest;

    private Proveedor mProvider;
    private List<Producto> mProducts;
    private Map<Integer, Integer> mSubtotals = new HashMap<Integer, Integer>();
    private Map<Producto, Integer> mOrder = new LinkedHashMap<Producto, Integer>();

    private View mRootView;
    private ImageView mImgProviderLogo;
    private TextView mLblProviderName;
    private TextView mLblProviderSlogan;
    private View mLayoutContent;
    private View mLayoutLoading;
    private TextView mLblEmpty;
    private TextView mLblTotal;
    private RatingBar mRatingProvider;
    private TextView mLblProviderPhone;

    private AbsListView mListView;
    private ProductsAdapter mProductsAdapter;

    private DisplayImageOptions mLoaderOptions;

    private Direccion mAddress;
    private int mAddressId;
    private int mServiceId;

    public static CreateOrderFragment newInstance(int addressId, int serviceId, Proveedor provider){
        CreateOrderFragment fragment = new CreateOrderFragment();

        fragment.mAddressId = addressId;
        fragment.mServiceId = serviceId;
        fragment.mProvider = provider;
        fragment.mProducts = provider.getProductos() == null ?
                new ArrayList<Producto>() : provider.getProductos();

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
        this.mAddress = HomelikeDBManager.getDBManager().getAddress(this.mAddressId);
        if(this.mAddress == null){
            Toast.makeText(this.getActivity(),
                    String.format(this.getString(R.string.error_load_address), this.mAddressId),
                    Toast.LENGTH_SHORT).show();
            ((HomeActivity) this.getActivity()).clearStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.fragment_create_order, container, false);

        this.mImgProviderLogo = (ImageView) this.mRootView.findViewById(R.id.img_provider_logo);
        this.mLblProviderName = (TextView) this.mRootView.findViewById(R.id.lbl_provider_name);
        this.mLblProviderSlogan = (TextView) this.mRootView.findViewById(R.id.lbl_provider_slogan);
        this.mLblProviderPhone = (TextView) this.mRootView.findViewById(R.id.lbl_provider_phone);
        this.mLayoutContent = this.mRootView.findViewById(R.id.layout_products_content);
        this.mListView = (ListView) this.mRootView.findViewById(R.id.list_products);
        this.mLayoutLoading = this.mRootView.findViewById(R.id.layout_loading);
        this.mLblEmpty = (TextView) this.mRootView.findViewById(R.id.lbl_empty);
        this.mLblTotal = (TextView) this.mRootView.findViewById(R.id.lbl_total);
        this.mRatingProvider = (RatingBar) this.mRootView.findViewById(R.id.rating_provider);

        return this.mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageLoader.getInstance().displayImage(
                this.mProvider.getLogo(), this.mImgProviderLogo, this.mLoaderOptions);
        this.mLblProviderName.setText(this.mProvider.getNombre());
        this.mLblProviderPhone.setText(this.getString(R.string.phone_short) + ": "
                + this.mProvider.getTelefono());
        this.mLblProviderSlogan.setText(this.mProvider.getSlogan());
        this.mRatingProvider.setRating((float) this.mProvider.getCalificacion());

        this.updateTotal();

        this.mProductsAdapter = new ProductsAdapter(this.getActivity(),
                this.mProducts, this.mSubtotals, this);
        this.mListView.setAdapter(this.mProductsAdapter);

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
        if(this.mProducts.isEmpty()){
            this.displayContentMode(ContentDisplayMode.LOAD);
            this.mProductsRequest = new ListProductsRequest(this, this.mProvider.getIdProveedor());
            this.mProductsRequest.executeAsync();
        } else {
            this.displayContentMode(ContentDisplayMode.CONTENT);
        }
    }

    public void confirmOrder(){
        if(!this.buildOrderMap()){
            new AlertDialog.Builder(this.getActivity())
                .setTitle(R.string.empty_order)
                .setMessage(R.string.empty_order_message)
                .setPositiveButton(R.string.ok, null).show();
            return;
        }

        ((HomeActivity) this.getActivity()).pushToStack(
                ConfirmOrderFragment.newInstance(this.mAddressId, this.mProvider, this.mOrder),
                ConfirmOrderFragment.class.getName());
    }

    private boolean buildOrderMap(){
        this.mOrder.clear();
        int quantities = 0;

        for(Integer position : this.mSubtotals.keySet()) {
            if(this.mSubtotals.get(position) <= 0) { continue; }
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
                this.mLayoutLoading.setVisibility(View.VISIBLE);
                break;
            }
            case CONTENT: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.GONE);
                this.mListView.setVisibility(View.VISIBLE);

                this.loadTempOrder();
                this.mProductsAdapter.updateContent(this.mProducts, this.mSubtotals);
                this.updateTotal();
                break;
            }
            case CONTENT_EMPTY: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.VISIBLE);
                this.mListView.setVisibility(View.GONE);
                break;
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        this.saveTempOrder();
    }

    public void saveTempOrder(){
        if(!this.buildOrderMap()){
            return;
        }
        HomelikePreferences.saveBoolean(HomelikePreferences.HAS_TEMP_ORDER, true);
        HomelikePreferences.saveInt(HomelikePreferences.TEMP_ORDER_PROVIDER_ID,
                this.mProvider.getIdProveedor());
        HomelikePreferences.saveInt(HomelikePreferences.TEMP_ORDER_ADDRESS_ID,
                this.mAddressId);
        HomelikePreferences.saveInt(HomelikePreferences.TEMP_ORDER_SERVICE_ID,
                this.mServiceId);
        HomelikePreferences.saveStringSet(HomelikePreferences.TEMP_ORDER_SUBTOTAL_SET,
                HomelikeUtils.getSerializedSubtotal(this.mSubtotals));
    }

    private void loadTempOrder(){
        boolean hasTempOrder = HomelikePreferences.loadBoolean(
                HomelikePreferences.HAS_TEMP_ORDER, false);
        HomelikePreferences.saveBoolean(HomelikePreferences.HAS_TEMP_ORDER, false);

        int providerId = HomelikePreferences.loadInt(
                HomelikePreferences.TEMP_ORDER_PROVIDER_ID, -1);
        int addressId = HomelikePreferences.loadInt(
                HomelikePreferences.TEMP_ORDER_ADDRESS_ID, -1);
        int serviceId = HomelikePreferences.loadInt(
                HomelikePreferences.TEMP_ORDER_SERVICE_ID, -1);
        if(!hasTempOrder || this.mProvider.getIdProveedor() != providerId
                || this.mAddressId != addressId || this.mServiceId != serviceId){
            this.mSubtotals = new HashMap<Integer, Integer>();
            return;
        }

        this.mSubtotals = HomelikeUtils.getDeserializedSubtotal(HomelikePreferences
                .loadStringSet(HomelikePreferences.TEMP_ORDER_SUBTOTAL_SET, null));
    }

}
