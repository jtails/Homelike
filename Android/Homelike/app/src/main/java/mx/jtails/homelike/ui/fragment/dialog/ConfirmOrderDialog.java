package mx.jtails.homelike.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Producto;

/**
 * Created by GrzegorzFeathers on 9/16/14.
 */
public class ConfirmOrderDialog extends DialogFragment {

    private ViewGroup mRootView;
    private Map<Producto, Integer> mOrder;

    private ConfirmOrderDialogCallbacks mCallbacks;

    public static ConfirmOrderDialog getInstance(
            Map<Producto, Integer> order, ConfirmOrderDialogCallbacks callbacks){
        ConfirmOrderDialog confirmDialog = new ConfirmOrderDialog();
        confirmDialog.mOrder = order;
        confirmDialog.mCallbacks = callbacks;
        return confirmDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        this.mRootView = (ViewGroup) inflater.inflate(R.layout.dialog_confirm_order, null);
        this.insertOrderProductsToView();

        return new AlertDialog.Builder(this.getActivity())
                .setTitle("Confirm Order")
                .setView(this.mRootView)
                .setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmClicked();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onEditClicked();
                    }
                }).create();
    }

    private void insertOrderProductsToView(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());

        for(Producto p : this.mOrder.keySet()){
            View rowView = inflater.inflate(
                    R.layout.list_item_product_confirm, this.mRootView, false);
            ((TextView) rowView.findViewById(R.id.lbl_product_name)).setText(
                    p.getCproducto().getDescripcion() + " - "
                    + p.getCproducto().getPresentacion());
            ((TextView) rowView.findViewById(R.id.lbl_quantity)).setText(
                    String.valueOf(this.mOrder.get(p)));
            this.mRootView.addView(rowView);
        }
    }

    private void onConfirmClicked(){
        if(this.mCallbacks != null){
            this.mCallbacks.onConfirmOrderClicked();
        }
    }

    private void onEditClicked(){
        if(this.mCallbacks != null){
            this.mCallbacks.onEditOrderClicked();
        }
    }

    public interface ConfirmOrderDialogCallbacks {
        public void onConfirmOrderClicked();
        public void onEditOrderClicked();
    }
}
