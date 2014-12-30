package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.DetallePedido;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.ApiResponseHandler;
import mx.jtails.homelike.request.GetOrderRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.ui.fragment.dialog.ConfirmOrderDialog;
import mx.jtails.homelike.util.HomeProviderMenuSection;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 11/26/14.
 */
public class ProviderOrderFragment extends Fragment
        implements ConfirmOrderDialog.ConfirmDialogDialogCallbacks {

    private Pedido mOrder = null;
    private String mOrderId = null;
    private boolean mShowProviderComments;

    private View mRootView;
    private ViewGroup mLayoutOrderDetails;
    private TextView mLblOrderId;
    private TextView mLblOrderAddress;
    private ImageView mImgProviderLogo;
    private TextView mLblStatus;
    private Button mFinishButton;

    private DisplayImageOptions mLoaderOptions;

    static public ProviderOrderFragment getInstance(Pedido order, boolean showProviderComments){
        ProviderOrderFragment fragment = new ProviderOrderFragment();

        fragment.mOrder = order;
        fragment.mShowProviderComments = showProviderComments;

        return fragment;
    }

    static public ProviderOrderFragment getInstance(String orderId){
        ProviderOrderFragment fragment = new ProviderOrderFragment();

        fragment.mOrderId = orderId;
        fragment.mShowProviderComments = false;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.fragment_provider_order, container, false);
        return this.mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLayoutOrderDetails = (ViewGroup) view.findViewById(R.id.layout_order_details);
        this.mImgProviderLogo = (ImageView) view.findViewById(R.id.img_provider_logo);
        this.mLblOrderId = (TextView) view.findViewById(R.id.lbl_order_id);
        this.mLblOrderAddress = (TextView) view.findViewById(R.id.lbl_order_address);
        this.mLblStatus = (TextView) view.findViewById(R.id.lbl_status);
        this.mFinishButton = (Button) view.findViewById(R.id.btn_finish_order);
        this.mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishOrderClicked();
            }
        });
    }

    private void onFinishOrderClicked(){
        if(this.mShowProviderComments){
            ((HomeActivity) this.getActivity()).singlePop();
        } else {
            ConfirmOrderDialog.newInstance(this.mOrder, this).show(this.getFragmentManager(), null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.mOrderId != null){
            this.mRootView.findViewById(R.id.layout_order).setVisibility(View.GONE);
            this.mRootView.findViewById(R.id.progress).setVisibility(View.VISIBLE);
            new GetOrderRequest(new ApiResponseHandler<Pedido>() {
                @Override
                public void onResponse(Pedido response) {
                    mOrderId = null;
                    updateContent(response);
                }
            }, Long.parseLong(this.mOrderId)).executeAsync();
        } else {
            this.updateContent(this.mOrder);
        }
    }

    private void updateContent(Pedido order){
        this.mRootView.findViewById(R.id.progress).setVisibility(View.GONE);
        this.mRootView.findViewById(R.id.layout_order).setVisibility(View.VISIBLE);

        this.mOrder = order;
        this.mLayoutOrderDetails.removeAllViews();

        ImageLoader.getInstance().displayImage(this.mOrder.getProveedor().getLogo(),
                this.mImgProviderLogo, this.mLoaderOptions);
        this.mLblOrderId.setText("ID. " + this.mOrder.getIdPedido());
        this.mLblStatus.setText(HomelikeUtils.getOrderStatusStringRes(this.mOrder.getStatus()));
        Direccion address = this.mOrder.getDireccion();
        this.mLblOrderAddress.setText(address.getCalle() + " #" + address.getNexterior()
                + (address.getNinterior() == null || address.getNinterior().equals("") ?
                    "" : (" int. " + address.getNinterior()))
                + ", " + address.getColonia() + ", " + address.getDelegacion());

        this.addOrderContent();
        this.addClientComments();

        if(this.mShowProviderComments){
            this.addProviderComments();
            this.mFinishButton.setText(R.string.back);
        } else {
            this.mFinishButton.setText(R.string.confirm_delivery);
        }
    }

    private void addOrderContent(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());

        for(DetallePedido p : this.mOrder.getDetallePedido()){
            String description = p.getProducto().getCproducto() == null
                    ? p.getProducto().getDescripcion()
                    : p.getProducto().getCproducto().getDescripcion();
            String presentation = p.getProducto().getCproducto() == null
                    ? p.getProducto().getPresentacion()
                    : p.getProducto().getCproducto().getPresentacion();

            View rowView = inflater.inflate(
                    R.layout.list_item_order_detail, this.mLayoutOrderDetails, false);
            ((TextView) rowView.findViewById(R.id.lbl_product_name)).setText(
                    description + " - " + presentation);
            ((TextView) rowView.findViewById(R.id.lbl_quantity)).setText(
                    p.getCantidad() + " x " + p.getProducto().getCostoUnitario());
            this.mLayoutOrderDetails.addView(rowView);
        }
    }

    private void addClientComments(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());

        View view = inflater.inflate(R.layout.view_comment,
                this.mLayoutOrderDetails, false);
        ((TextView) view.findViewById(R.id.lbl_comment_title)).setText(R.string.client_comment);
        ((TextView) view.findViewById(R.id.lbl_comment)).setText(
                this.mOrder.getComentarioCliente() == null || this.mOrder.getComentarioCliente().isEmpty() ?
                        this.getString(R.string.no_comments) : this.mOrder.getComentarioCliente());

        this.mLayoutOrderDetails.addView(view);
    }

    private void addProviderComments(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());

        View view = inflater.inflate(R.layout.view_comment,
                this.mLayoutOrderDetails, false);
        ((TextView) view.findViewById(R.id.lbl_comment_title)).setText(R.string.provider_comment);
        ((TextView) view.findViewById(R.id.lbl_comment)).setText(
                this.mOrder.getComentarioProveedor() == null || this.mOrder.getComentarioProveedor().isEmpty() ?
                        this.getString(R.string.no_comments) : this.mOrder.getComentarioProveedor());

        this.mLayoutOrderDetails.addView(view);
    }

    @Override
    public void onOrderConfirmed() {
        Toast.makeText(this.getActivity(), R.string.comment_sent, Toast.LENGTH_SHORT).show();
        ((HomeActivity) this.getActivity()).replaceStack(
                HomeProviderMenuSection.CONFIRMED_ORDERS.getFragmentClass(), null);
    }

    @Override
    public void onConfirmOrderFailed() {
        ActionBarActivity a = (ActionBarActivity) this.getActivity();
        new AlertDialog.Builder(a)
                .setMessage(R.string.error_update_order)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public void onCancelConfirmation() {}

}
