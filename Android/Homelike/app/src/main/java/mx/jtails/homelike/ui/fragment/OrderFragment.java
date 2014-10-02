package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.DetallePedido;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.UpdateOrderRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 9/23/14.
 */
public class OrderFragment extends Fragment implements UpdateOrderRequest.OnUpdateOrderResponseHandler {

    private Pedido mOrder;

    private ViewGroup mLayoutOrderDetails;
    private TextView mLblProviderName;
    private RatingBar mRatingProvider;
    private TextView mLblOrderId;
    private ImageView mImgProviderLogo;
    private TextView mLblStatus;
    private View mFinishButton;

    private ProgressDialog mUpdatingOrder;

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
        //this.mLblProviderRating = (TextView) view.findViewById(R.id.lbl_rating);
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
        this.mOrder.setStatus(2);
        this.mUpdatingOrder = ProgressDialog.show(this.getActivity(),
                this.getString(R.string.updating), this.getString(R.string.wait), false, false);
        new UpdateOrderRequest(this, this.mOrder).executeAsync();
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
        this.mRatingProvider.setRating((float) this.mOrder.getProveedor().getCalificacion());
        this.mLblStatus.setText(HomelikeUtils.getOrderStatusString(this.mOrder.getStatus()));

        this.addOrderContent();
        this.addClientComments();
        this.addProviderComments();

        if(this.mOrder.getStatus() != 1){
            this.mFinishButton.setVisibility(View.GONE);
        } else {
            this.mFinishButton.setVisibility(View.VISIBLE);
        }
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
    public void onUpdateOrderResponse(Pedido order) {
        this.mUpdatingOrder.dismiss();
        if(order == null){
            new AlertDialog.Builder(this.getActivity())
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_update_order)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        } else {
            FragmentManager fm = this.getFragmentManager();
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ((HomeActivity) this.getActivity()).addToStack(
                    CommentsAndSuggestionsFragment.getInstance(this.mOrder));
        }
    }
}
