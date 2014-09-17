package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.ui.widget.OrderProductView;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class ProductsAdapter extends ArrayAdapter<Producto> {

    private List<Producto> mProducts;
    private OrderProductView.OnProductQuantityChangedListener mListener;

    public ProductsAdapter(Context context, List<Producto> products,
                           OrderProductView.OnProductQuantityChangedListener listener){
        super(context, R.layout.list_item_product_order, products);
        this.mProducts = products;
        this.mListener = listener;
    }

    public void updateContent(List<Producto> products){
        this.mProducts = products;
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
        holder.ordProduct.get().setProduct(product, position);
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

    private class ViewHolder {

        private WeakReference<OrderProductView> ordProduct;

        public ViewHolder(View view) {
            this.ordProduct = new WeakReference<OrderProductView>(
                    (OrderProductView) view.findViewById(R.id.ord_product));
        }
    }

}
