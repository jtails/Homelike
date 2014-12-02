package mx.jtails.homelike.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.ApiResponseHandler;
import mx.jtails.homelike.request.UpdateProviderOrderRequest;

/**
 * Created by GrzegorzFeathers on 11/26/14.
 */
public class ConfirmOrderDialog extends DialogFragment
    implements ApiResponseHandler<Pedido> {

    private Pedido mOrder;
    private ConfirmDialogDialogCallbacks listener;
    private ProgressDialog mLoadingDialog;
    private EditText mEditComment;

    public static ConfirmOrderDialog newInstance(Pedido order, ConfirmDialogDialogCallbacks listener) {
        ConfirmOrderDialog dialog = new ConfirmOrderDialog();
        dialog.mOrder = order;
        dialog.listener = listener;
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(this.getActivity()).inflate(
                R.layout.dialog_confirm_order, null);
        this.mEditComment = (EditText) rootView.findViewById(R.id.edit_comment);
        return new AlertDialog.Builder(this.getActivity())
                .setTitle(R.string.confirm_order)
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmClicked();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onCancelClicked();
                    }
                })
                .create();
    }

    private void onConfirmClicked() {
        this.mLoadingDialog = ProgressDialog.show(this.getActivity(),
                this.getString(R.string.confirm_order),
                this.getString(R.string.wait));
        this.mLoadingDialog.setCancelable(false);
        this.mOrder.setStatus(1);
        this.mOrder.setComentarioEntregaProveedor(this.mEditComment.getText().toString().trim());
        new UpdateProviderOrderRequest(this, this.mOrder).executeAsync();
    }

    private void onCancelClicked() {
        if(this.mLoadingDialog != null){
            this.mLoadingDialog.dismiss();
        }
        this.dismiss();
        if(this.listener != null){
            this.listener.onCancelConfirmation();
        }
    }

    @Override
    public void onResponse(Pedido response) {
        if(this.mLoadingDialog != null){
            this.mLoadingDialog.dismiss();
        }
        this.dismiss();
        if(response == null){
            if(this.listener != null){
                this.listener.onConfirmOrderFailed();
            }
        } else {
            if(this.listener != null){
                this.listener.onOrderConfirmed();
            }
        }
    }

    public interface ConfirmDialogDialogCallbacks {
        public void onOrderConfirmed();
        public void onConfirmOrderFailed();
        public void onCancelConfirmation();
    }

}
