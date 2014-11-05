package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.request.InsertOrderRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.util.HomeMenuSection;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/16/14.
 */
public class ConfirmOrderFragment extends Fragment
    implements InsertOrderRequest.OnInsertOrderResponseHandler {

    private ViewGroup mRootView;

    private TextView mLblTotal;
    private EditText mEditComments;

    private ProgressDialog mCreatingDialog;

    private Map<Producto, Integer> mOrder;

    private int mAddressId;
    private Proveedor mProvider;

    public static ConfirmOrderFragment newInstance(int addressId, Proveedor provider,
            Map<Producto, Integer> order){

        ConfirmOrderFragment fragment = new ConfirmOrderFragment();
        fragment.mAddressId = addressId;
        fragment.mOrder = order;
        fragment.mProvider = provider;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirm_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLblTotal = (TextView) view.findViewById(R.id.lbl_total);
        this.mEditComments = (EditText) view.findViewById(R.id.edit_comments);
        this.mRootView = (ViewGroup) view.findViewById(R.id.layout_order_details);

        view.findViewById(R.id.btn_confirm_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmClicked();
            }
        });

        this.updateContent();
    }

    private void updateContent(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        float total = 0.0f;

        this.mRootView.removeAllViews();
        for(Producto p : this.mOrder.keySet()){
            total += this.mOrder.get(p) * (float) p.getCostoUnitario();

            String description = p.getCproducto() == null ? p.getDescripcion()
                    : p.getCproducto().getDescripcion();
            String presentation = p.getCproducto() == null ? p.getPresentacion()
                    : p.getCproducto().getPresentacion();

            View rowView = inflater.inflate(
                    R.layout.list_item_product_confirm, this.mRootView, false);
            ((TextView) rowView.findViewById(R.id.lbl_product_name)).setText(
                    description + " - " + presentation);
            ((TextView) rowView.findViewById(R.id.lbl_quantity)).setText(
                    this.mOrder.get(p) + " x " + p.getCostoUnitario());
            this.mRootView.addView(rowView);
        }

        this.mLblTotal.setText(String.valueOf(total));
    }

    private void onConfirmClicked(){
        this.mCreatingDialog = ProgressDialog.show(this.getActivity(),
                this.getString(R.string.new_order), this.getString(R.string.wait), false, false);
        new InsertOrderRequest(this, HomelikePreferences.loadInt(HomelikePreferences.ACCOUNT_ID, -1),
                this.mOrder, this.mProvider, this.mAddressId,
                this.mEditComments.getText().toString()).executeAsync();
    }

    @Override
    public void onInsertOrderResponse(Pedido order) {
        this.mCreatingDialog.dismiss();
        if(order == null){
            new AlertDialog.Builder(this.getActivity())
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_create_order)
                    .setPositiveButton(R.string.ok, null).show();
            ((HomeActivity) this.getActivity()).singlePop();
        } else {
            HomelikePreferences.saveBoolean(HomelikePreferences.HAS_TEMP_ORDER, false);
            ((HomeActivity) this.getActivity()).replaceStack(
                    HomeMenuSection.ORDERS.getFragmentClass(), null);
        }
    }

}
