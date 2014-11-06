package mx.jtails.homelike.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.UpdateOrderRequest;
import mx.jtails.homelike.ui.HomeActivity;

/**
 * Created by GrzegorzFeathers on 9/30/14.
 */
public class CommentsFragment extends Fragment
    implements UpdateOrderRequest.OnUpdateOrderResponseHandler {

    private Pedido mOrder;

    private EditText mEditComments;
    private RatingBar mRatingOrder;

    private ProgressDialog mProgressSendingComment;

    public static CommentsFragment newInstance(Pedido order){
        CommentsFragment fragment = new CommentsFragment();
        fragment.mOrder = order;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mEditComments = (EditText) view.findViewById(R.id.edit_comments);
        this.mRatingOrder = (RatingBar) view.findViewById(R.id.rating_order);
        view.findViewById(R.id.btn_send_comments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendCommentsClicked();
            }
        });
    }

    private void backToHome(){
        ((HomeActivity) this.getActivity()).clearStack();
    }

    public void onSendCommentsClicked(){
        this.mProgressSendingComment = ProgressDialog.show(this.getActivity(),
                this.getString(R.string.sending), this.getString(R.string.wait), false, false);
        String comment = this.mEditComments.getText().toString();
        comment = comment.trim();
        int rating = (int) this.mRatingOrder.getRating();

        this.mOrder.setComentarioEntregaCliente(comment);
        this.mOrder.setCalificacion(rating);
        new UpdateOrderRequest(this, this.mOrder).executeAsync();
    }

    @Override
    public void onUpdateOrderResponse(Pedido order) {
        this.mProgressSendingComment.dismiss();
        this.backToHome();
    }
}
