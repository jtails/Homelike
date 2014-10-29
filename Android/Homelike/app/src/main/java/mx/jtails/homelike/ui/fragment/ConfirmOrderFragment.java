package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.CantidadPago;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.InsertOrderRequest;
import mx.jtails.homelike.request.ListPaymentQuantitiesRequest;
import mx.jtails.homelike.ui.CheckOrderActivity;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.util.HomeMenuSection;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/16/14.
 */
public class ConfirmOrderFragment extends Fragment
    implements ListPaymentQuantitiesRequest.OnListPaymentQuantitiesResponseHandler,
        InsertOrderRequest.OnInsertOrderResponseHandler {

    private ViewGroup mRootView;
    private TextView mLblTotal;
    private View mProgressLayout;
    private View mMainContainer;
    private Spinner mSpinnerPayment;
    private EditText mEditComments;

    private ProgressDialog mCreatingDialog;

    private Map<Producto, Integer> mOrder;
    private List<CantidadPago> mPaymentQuantities;

    private int mAddressId;
    private Proveedor mProvider;

    private HomelikeApiRequest mListPayments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mListPayments = new ListPaymentQuantitiesRequest(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirm_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLblTotal = (TextView) view.findViewById(R.id.lbl_total);
        this.mProgressLayout = view.findViewById(R.id.layout_loading);
        this.mMainContainer = view.findViewById(R.id.layout_content);
        this.mSpinnerPayment = (Spinner) view.findViewById(R.id.spinner_payments);
        this.mEditComments = (EditText) view.findViewById(R.id.edit_comments);
        this.mRootView = (ViewGroup) view.findViewById(R.id.layout_order_details);

        view.findViewById(R.id.btn_confirm_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmClicked();
            }
        });
        view.findViewById(R.id.btn_edit_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditClicked();
            }
        });

        this.mProgressLayout.setVisibility(View.VISIBLE);
        this.mMainContainer.setVisibility(View.INVISIBLE);
        this.mListPayments.executeAsync();
    }

    @Override
    public void onListPaymentQuantities(List<CantidadPago> paymentQuantities) {
        if(this.getActivity() == null){
            return;
        }

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

    public void setupFragmentWith(int addressId, Map<Producto, Integer> order, Proveedor provider){
        this.mAddressId = addressId;
        this.mOrder = order;
        this.mProvider = provider;
        this.mEditComments.setText("");
        this.updateContent();
    }

    private void updateContent(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        float total = 0.0f;

        this.mRootView.removeAllViews();
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
        int position = this.mSpinnerPayment.getSelectedItemPosition();
        CantidadPago paymentQuantity = this.mPaymentQuantities.isEmpty() ?
                null : this.mPaymentQuantities.get(position);
        this.mCreatingDialog = ProgressDialog.show(this.getActivity(),
                this.getString(R.string.new_order), this.getString(R.string.wait), false, false);
        new InsertOrderRequest(this, this.getActivity(),
                HomelikePreferences.loadInt(HomelikePreferences.ACCOUNT_ID, -1),
                this.mOrder, this.mProvider, paymentQuantity, this.mAddressId,
                this.mEditComments.getText().toString()).executeAsync();
    }

    private void onEditClicked(){
        ((CheckOrderActivity) this.getActivity()).editOrder();
    }

    @Override
    public void onInsertOrderResponse(Pedido order) {
        this.mCreatingDialog.dismiss();
        if(order == null){
            new AlertDialog.Builder(this.getActivity())
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_create_order)
                    .setPositiveButton(R.string.ok, null).show();
            this.onEditClicked();
        } else {
            Intent intent = new Intent(this.getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Bundle args = new Bundle();
            args.putInt(HomeActivity.ARG_HOME_CONTENT_ORD, HomeMenuSection.ORDERS.ordinal());
            intent.putExtras(args);
            this.getActivity().startActivity(intent);
        }
    }

}
