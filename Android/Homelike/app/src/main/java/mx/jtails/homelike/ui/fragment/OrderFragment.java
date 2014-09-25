package mx.jtails.homelike.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.DetallePedido;
import mx.jtails.homelike.api.model.Pedido;

/**
 * Created by GrzegorzFeathers on 9/23/14.
 */
public class OrderFragment extends Fragment {

    private Pedido mOrder;

    private ViewGroup mLayoutOrderDetails;
    private TextView mLblProviderName;
    private TextView mLblProviderRating;
    private TextView mLblOrderId;
    private ImageView mImgProviderLogo;

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
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.mLayoutOrderDetails = (ViewGroup) view.findViewById(R.id.layout_order_details);
        this.mImgProviderLogo = (ImageView) view.findViewById(R.id.img_provider_logo);
        this.mLblProviderName = (TextView) view.findViewById(R.id.lbl_provider_name);
        this.mLblOrderId = (TextView) view.findViewById(R.id.lbl_order_id);
        this.mLblProviderRating = (TextView) view.findViewById(R.id.lbl_rating);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateContent(this.mOrder);
    }

    private void updateContent(Pedido order){
        this.mOrder = order;

        ImageLoader.getInstance().displayImage(this.mOrder.getProveedor().getLogo(),
                this.mImgProviderLogo, this.mLoaderOptions);
        this.mLblProviderName.setText(this.mOrder.getProveedor().getNombre());
        this.mLblOrderId.setText("ID. " + this.mOrder.getIdPedido());
        this.mLblProviderRating.setText("" + (float) this.mOrder.getProveedor().getCalificacion());

        this.addOrderContent();
        this.addClientComments();
    }

    private void addOrderContent(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());

        for(DetallePedido p : this.mOrder.getDetallePedido()){
            View rowView = inflater.inflate(
                    R.layout.list_item_order_detail, this.mLayoutOrderDetails, false);
            ((TextView) rowView.findViewById(R.id.lbl_product_name)).setText(
                    p.getProducto().getCproducto().getDescripcion() + " - "
                            + p.getProducto().getCproducto().getPresentacion());
            ((TextView) rowView.findViewById(R.id.lbl_quantity)).setText(
                    p.getCantidad() + " x " + p.getProducto().getCostoUnitario());
            this.mLayoutOrderDetails.addView(rowView);
        }
    }

    private void addClientComments(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());

        View view = inflater.inflate(R.layout.view_client_comment,
                this.mLayoutOrderDetails, false);
        ((TextView) view.findViewById(R.id.lbl_client_comment)).setText(
                this.mOrder.getComentarioCliente() == null ?
                    "No comments" : this.mOrder.getComentarioCliente());

        this.mLayoutOrderDetails.addView(view);
    }

}