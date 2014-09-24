package mx.jtails.homelike.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.CantidadPago;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.request.ListPaymentQuantitiesRequest;

/**
 * Created by GrzegorzFeathers on 9/16/14.
 */
public class ConfirmOrderDialog extends DialogFragment
    implements ListPaymentQuantitiesRequest.OnListPaymentQuantitiesResponseHandler {

    private ViewGroup mRootView;
    private TextView mLblTotal;
    private View mProgressLayout;
    private View mMainContainer;
    private Spinner mSpinnerPayment;

    private Map<Producto, Integer> mOrder;
    private List<CantidadPago> mPaymentQuantities;

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
        View view = inflater.inflate(R.layout.dialog_confirm_order, null);
        this.mLblTotal = (TextView) view.findViewById(R.id.lbl_total);
        this.mProgressLayout = view.findViewById(R.id.layout_loading);
        this.mMainContainer = view.findViewById(R.id.layout_content);
        this.mSpinnerPayment = (Spinner) view.findViewById(R.id.spinner_payments);
        this.mRootView = (ViewGroup) view.findViewById(R.id.layout_order_details);
        this.insertOrderProductsToView();

        this.mProgressLayout.setVisibility(View.VISIBLE);
        this.mMainContainer.setVisibility(View.INVISIBLE);

        new ListPaymentQuantitiesRequest(this).executeAsync();

        return new AlertDialog.Builder(this.getActivity())
                .setTitle("Confirm Order")
                .setView(view)
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
        float total = 0.0f;

        for(Producto p : this.mOrder.keySet()){
            total += this.mOrder.get(p) * (float) p.getCostoUnitario();

            View rowView = inflater.inflate(
                    R.layout.list_item_product_confirm, this.mRootView, false);
            ((TextView) rowView.findViewById(R.id.lbl_product_name)).setText(
                    p.getCproducto().getDescripcion() + " - "
                            + p.getCproducto().getPresentacion());
            ((TextView) rowView.findViewById(R.id.lbl_quantity)).setText(
                    this.mOrder.get(p) + " x " + p.getCostoUnitario());
            this.mRootView.addView(rowView);
        }

        this.mLblTotal.setText(String.valueOf(total));
    }

    private void onConfirmClicked(){
        if(this.mCallbacks != null){
            int position = this.mSpinnerPayment.getSelectedItemPosition();
            this.mCallbacks.onConfirmOrderClicked(this.mPaymentQuantities.get(position));
        }
    }

    private void onEditClicked(){
        if(this.mCallbacks != null){
            this.mCallbacks.onEditOrderClicked();
        }
    }

    @Override
    public void onListPaymentQuantities(List<CantidadPago> paymentQuantities) {
        this.mPaymentQuantities = paymentQuantities;

        List<CharSequence> paymentQString = new ArrayList<CharSequence>();
        for(CantidadPago cp : this.mPaymentQuantities){
            paymentQString.add("$" + cp.getCantidadNumero());
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this.getActivity(), android.R.layout.simple_spinner_item, paymentQString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mSpinnerPayment.setAdapter(adapter);

        this.mMainContainer.setVisibility(View.VISIBLE);
        this.mProgressLayout.setVisibility(View.GONE);
    }

    public interface ConfirmOrderDialogCallbacks {
        public void onConfirmOrderClicked(CantidadPago paymentQuantity);
        public void onEditOrderClicked();
    }
}
