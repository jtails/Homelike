package mx.jtails.homelike.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.DetallePedido;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.ApiResponseHandler;
import mx.jtails.homelike.request.GetOrderRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 9/23/14.
 */
public class OrderFragment extends Fragment {

    private Pedido mOrder = null;
    private String mOrderId = null;

    private View mRootView;
    private ViewGroup mLayoutOrderDetails;
    private TextView mLblProviderName;
    private RatingBar mRatingProvider;
    private TextView mLblOrderId;
    private ImageView mImgProviderLogo;
    private TextView mLblStatus;
    private View mFinishButton;

    //private ProgressDialog mUpdatingOrder;

    private DisplayImageOptions mLoaderOptions;

    static public OrderFragment getInstance(Pedido order){
        OrderFragment fragment = new OrderFragment();

        fragment.mOrder = order;

        return fragment;
    }

    static public OrderFragment getInstance(String orderId){
        OrderFragment fragment = new OrderFragment();

        fragment.mOrderId = orderId;

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
        this.mRootView = inflater.inflate(R.layout.fragment_order, container, false);
        return this.mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLayoutOrderDetails = (ViewGroup) view.findViewById(R.id.layout_order_details);
        this.mImgProviderLogo = (ImageView) view.findViewById(R.id.img_provider_logo);
        this.mLblProviderName = (TextView) view.findViewById(R.id.lbl_provider_name);
        this.mLblOrderId = (TextView) view.findViewById(R.id.lbl_order_id);
        this.mRatingProvider = (RatingBar) view.findViewById(R.id.rating_provider);
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
        ((HomeActivity) this.getActivity()).pushToStack(
                CommentsFragment.newInstance(this.mOrder),
                CommentsFragment.class.getName());
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
        this.mLblProviderName.setText(this.mOrder.getProveedor().getNombre());
        this.mLblOrderId.setText("ID. " + this.mOrder.getIdPedido());
        this.mRatingProvider.setRating((float) this.mOrder.getProveedor().getCalificacion());
        this.mLblStatus.setText(HomelikeUtils.getOrderStatusStringRes(this.mOrder.getStatus()));

        this.addOrderContent();
        this.addClientComments();
        this.addProviderComments();

        if(this.mOrder.getStatus() != 1){
            this.mFinishButton.setClickable(false);
        } else {
            this.mFinishButton.setClickable(true);
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

    /*
    @Override
    public void onUpdateOrderResponse(Pedido order) {
        this.mUpdatingOrder.dismiss();
        if(order == null){
            new AlertDialog.Builder(this.getActivity())
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_update_order)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        } else {
            ((HomeActivity) this.getActivity()).singlePop();
            ((HomeActivity) this.getActivity()).pushToStack(
                    CommentsFragment.newInstance(this.mOrder),
                    CommentsFragment.class.getName());
        }
    }
    */
}
