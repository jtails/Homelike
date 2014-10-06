package mx.jtails.homelike.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.UpdateOrderRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 9/30/14.
 */
public class CommentsFragment extends Fragment
    implements UpdateOrderRequest.OnUpdateOrderResponseHandler {

    private Pedido mOrder;

    private EditText mEditComments;
    private RatingBar mRatingOrder;

    private ProgressDialog mProgressSendingComment;

    public static CommentsFragment getInstance(Pedido order){
        CommentsFragment fragment = new CommentsFragment();
        fragment.mOrder = order;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
        FragmentManager fm = this.getFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        ((HomeActivity) this.getActivity()).onHomeMenuOptionSelected(HomeMenuSection.SERVICES);
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
        Toast.makeText(this.getActivity(), R.string.comment_sent, Toast.LENGTH_SHORT).show();
        this.backToHome();
    }
}
