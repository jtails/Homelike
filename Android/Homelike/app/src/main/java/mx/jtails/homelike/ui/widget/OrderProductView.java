package mx.jtails.homelike.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Producto;

/**
 * Created by GrzegorzFeathers on 9/11/14.
 */
public class OrderProductView extends LinearLayout {

    private Producto mProduct;
    private int mPosition;
    private int mQuantity = 0;

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

    public void setProduct(Producto product, int position){
        this.mProduct = product;
        this.mPosition = position;

        this.mLblProductName.setText(this.mProduct.getCproducto().getDescripcion()
            + " - " + this.mProduct.getCproducto().getPresentacion());
        this.mLblUnitPrice.setText("$" + String.valueOf(this.mProduct.getCostoUnitario()));
        this.mLblQuantity.setText(String.valueOf(this.mQuantity));
    }

    public void setOnProductQuantityChangedListener(OnProductQuantityChangedListener listener){
        this.mOnProductQuantityChangedListener = listener;
    }

    private void onAddProductClicked() {
        this.mQuantity++;
        this.mLblQuantity.setText(String.valueOf(this.mQuantity));
        if(this.mOnProductQuantityChangedListener != null){
            this.mOnProductQuantityChangedListener.onProductQuantityChanged(
                    this.mPosition, this.mQuantity);
        }
    }

    private void onRemoveProductClicked() {
        if(this.mQuantity > 0) this.mQuantity--;
        this.mLblQuantity.setText(String.valueOf(this.mQuantity));
        if(this.mOnProductQuantityChangedListener != null){
            this.mOnProductQuantityChangedListener.onProductQuantityChanged(
                    this.mPosition, this.mQuantity);
        }
    }

    public interface OnProductQuantityChangedListener {
        public void onProductQuantityChanged(int position, int newQuantity);
    }

}
