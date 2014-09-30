package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.ui.widget.OrderProductView;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class ProductsAdapter extends ArrayAdapter<Producto> implements OrderProductView.QuantityHolder {

    private List<Producto> mProducts;
    private OrderProductView.OnProductQuantityChangedListener mListener;
    private Map<Integer, Integer> mSubtotals;

    public ProductsAdapter(Context context, List<Producto> products, Map<Integer, Integer> subtotals,
                           OrderProductView.OnProductQuantityChangedListener listener){
        super(context, R.layout.list_item_product_order, products);
        this.mProducts = products;
        this.mSubtotals = subtotals;
        this.mListener = listener;
    }

    public void updateContent(List<Producto> products, Map<Integer, Integer> subtotals){
        this.mProducts = products;
        this.mSubtotals = subtotals;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.list_item_product_order,
                    parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Producto product = this.getItem(position);
        holder.ordProduct.get().setProduct(product, position, this);
        holder.ordProduct.get().setOnProductQuantityChangedListener(this.mListener);

        return view;
    }

    @Override
    public Producto getItem(int position) {
        return this.mProducts.get(position);
    }

    @Override
    public int getCount() {
        return this.mProducts.size();
    }

    @Override
    public int getProductQuantity(int position) {
        if(this.mSubtotals.containsKey(position)){
            return this.mSubtotals.get(position);
        } else {
            return 0;
        }
    }

    @Override
    public void addProduct(int position) {
        if(this.mSubtotals.containsKey(position)){
            this.mSubtotals.put(position,
                    this.mSubtotals.get(position) + 1);
        } else {
            this.mSubtotals.put(position, 1);
        }
    }

    @Override
    public void removeProduct(int position) {
        this.mSubtotals.put(position,
                this.mSubtotals.get(position) - 1);
    }

    private class ViewHolder {

        private WeakReference<OrderProductView> ordProduct;

        public ViewHolder(View view) {
            this.ordProduct = new WeakReference<OrderProductView>(
                    (OrderProductView) view.findViewById(R.id.ord_product));
        }
    }

}
