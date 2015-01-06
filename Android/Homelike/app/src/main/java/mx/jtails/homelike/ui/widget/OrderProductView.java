package mx.jtails.homelike.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.Producto;

/**
 * Created by GrzegorzFeathers on 9/11/14.
 */
public class OrderProductView extends LinearLayout {

    private Producto mProduct;
    private int mPosition;
    private QuantityHolder mHolder;

    private TextView mLblProductName;
    private TextView mLblUnitPrice;
    private TextView mLblQuantity;

    private View mBtnAddProduct;
    private View mBtnRemoveProduct;

    private OnProductQuantityChangedListener mOnProductQuantityChangedListener;

    public OrderProductView(Context context) {
        this(context, null, 0);
    }

    public OrderProductView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderProductView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.view_order_product, this, true);

        this.mLblProductName = (TextView) this.findViewById(R.id.lbl_product_name);
        this.mLblUnitPrice = (TextView) this.findViewById(R.id.lbl_unit_price);
        this.mLblQuantity = (TextView) this.findViewById(R.id.lbl_quantity);

        this.mBtnAddProduct = this.findViewById(R.id.btn_add_product);
        this.mBtnRemoveProduct = this.findViewById(R.id.btn_remove_product);

        this.mBtnAddProduct.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddProductClicked();
            }
        });
        this.mBtnRemoveProduct.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveProductClicked();
            }
        });
    }

    public void setProduct(Producto product, int position, QuantityHolder holder){
        this.mProduct = product;
        this.mPosition = position;
        this.mHolder = holder;

        String description = this.mProduct.getCproducto() == null ? this.mProduct.getDescripcion()
                : this.mProduct.getCproducto().getDescripcion();
        String presentation = this.mProduct.getCproducto() == null ? this.mProduct.getPresentacion()
                : this.mProduct.getCproducto().getPresentacion();

        this.mLblProductName.setText(description + " - " + presentation);
        this.mLblUnitPrice.setText("$" + String.valueOf(this.mProduct.getCostoUnitario()));
        this.mLblQuantity.setText(String.valueOf(this.mHolder.getProductQuantity(this.mPosition)));
    }

    public void setOnProductQuantityChangedListener(OnProductQuantityChangedListener listener){
        this.mOnProductQuantityChangedListener = listener;
    }

    private void onAddProductClicked() {
        this.mHolder.addProduct(this.mPosition);
        this.mLblQuantity.setText(String.valueOf(this.mHolder.getProductQuantity(this.mPosition)));
        if(this.mOnProductQuantityChangedListener != null){
            this.mOnProductQuantityChangedListener.onProductQuantityChanged(
                    this.mPosition, this.mHolder.getProductQuantity(this.mPosition));
        }
    }

    private void onRemoveProductClicked() {
        if(this.mHolder.getProductQuantity(this.mPosition) > 0){
            this.mHolder.removeProduct(this.mPosition);
        }
        this.mLblQuantity.setText(String.valueOf(this.mHolder.getProductQuantity(this.mPosition)));
        if(this.mOnProductQuantityChangedListener != null){
            this.mOnProductQuantityChangedListener.onProductQuantityChanged(
                    this.mPosition, this.mHolder.getProductQuantity(this.mPosition));
        }
    }

    public interface OnProductQuantityChangedListener {
        public void onProductQuantityChanged(int position, int newQuantity);
    }

    public interface QuantityHolder {
        public int getProductQuantity(int position);
        public void addProduct(int position);
        public void removeProduct(int position);
    }

}
