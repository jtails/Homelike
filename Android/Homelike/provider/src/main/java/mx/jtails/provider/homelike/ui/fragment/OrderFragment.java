package mx.jtails.provider.homelike.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import mx.jtails.provider.homelike.R;
import mx.jtails.provider.homelike.api.model.DetallePedido;
import mx.jtails.provider.homelike.api.model.Direccion;
import mx.jtails.provider.homelike.api.model.Pedido;
import mx.jtails.provider.homelike.ui.HomeActivity;
import mx.jtails.provider.homelike.ui.fragment.dialog.ConfirmOrderDialog;
import mx.jtails.provider.homelike.util.HomeMenuSection;
import mx.jtails.provider.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 11/26/14.
 */
public class OrderFragment extends Fragment
        implements ConfirmOrderDialog.ConfirmDialogDialogCallbacks {

    private Pedido mOrder;

    private ViewGroup mLayoutOrderDetails;
    private TextView mLblProviderName;
    private TextView mLblOrderId;
    private TextView mLblOrderAddress;
    private ImageView mImgProviderLogo;
    private TextView mLblStatus;
    private View mFinishButton;

    private DisplayImageOptions mLoaderOptions;

    static public OrderFragment getInstance(Pedido order){
        OrderFragment fragment = new OrderFragment();

        fragment.mOrder = order;

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
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLayoutOrderDetails = (ViewGroup) view.findViewById(R.id.layout_order_details);
        this.mImgProviderLogo = (ImageView) view.findViewById(R.id.img_provider_logo);
        this.mLblProviderName = (TextView) view.findViewById(R.id.lbl_provider_name);
        this.mLblOrderId = (TextView) view.findViewById(R.id.lbl_order_id);
        this.mLblOrderAddress = (TextView) view.findViewById(R.id.lbl_order_address);
        this.mLblStatus = (TextView) view.findViewById(R.id.lbl_status);
        this.mFinishButton = view.findViewById(R.id.btn_finish_order);
        this.mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishOrderClicked();
            }
        });
    }

    private void onFinishOrderClicked(){
        ConfirmOrderDialog.newInstance(this.mOrder, this).show(this.getFragmentManager(), null);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateContent(this.mOrder);
    }

    private void updateContent(Pedido order){
        this.mOrder = order;
        this.mLayoutOrderDetails.removeAllViews();

        ImageLoader.getInstance().displayImage(this.mOrder.getProveedor().getLogo(),
                this.mImgProviderLogo, this.mLoaderOptions);
        this.mLblProviderName.setText(this.mOrder.getProveedor().getNombre());
        this.mLblOrderId.setText("ID. " + this.mOrder.getIdPedido());
        this.mLblStatus.setText(HomelikeUtils.getOrderStatusStringRes(this.mOrder.getStatus()));
        Direccion address = this.mOrder.getDireccion();
        this.mLblOrderAddress.setText(address.getCalle() + " #" + address.getNexterior() + ", "
                + address.getColonia() + ", " + address.getDelegacion());

        this.addOrderContent();
        this.addClientComments();

        if(this.mOrder.getStatus() == 0){
            this.mFinishButton.setVisibility(View.VISIBLE);
        } else {
            this.mFinishButton.setVisibility(View.GONE);
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

    @Override
    public void onOrderConfirmed() {
        Toast.makeText(this.getActivity(), R.string.comment_sent, Toast.LENGTH_SHORT).show();
        ((HomeActivity) this.getActivity()).replaceStack(
                HomeMenuSection.CONFIRMED_ORDERS.getFragmentClass(), null);
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
