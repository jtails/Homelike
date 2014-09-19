package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Pedido;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public class OrdersAdapter extends ArrayAdapter<Pedido> {

    private List<Pedido> mOrders;

    public OrdersAdapter(Context context, List<Pedido> orders){
        super(context, R.layout.list_item_service, orders);
        this.mOrders = orders;
    }

    @Override
    public int getCount() {
        return this.mOrders.size();
    }

    @Override
    public Pedido getItem(int position) {
        return this.mOrders.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if(view == null){
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.list_item_service,
                    parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Pedido order = this.getItem(position);
        holder.lblServiceName.get().setText(order.getCuenta().getUsuario());
        //holder.imgServiceIcon.get().setImageResource(
          //      service.getNombre().toLowerCase(Locale.ENGLISH).equals("agua") ?
            //        R.drawable.ic_service_water : R.drawable.ic_service_gas);

        return view;
    }

    public void updateContent(List<Pedido> orders){
        this.mOrders = orders;
        this.notifyDataSetInvalidated();
    }

    private class ViewHolder {

        private ViewHolder(View view) {
            this.lblServiceName = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_service_name));
            this.imgServiceIcon = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_service_icon));
        }

        private WeakReference<TextView> lblServiceName;
        private WeakReference<ImageView> imgServiceIcon;

    }
}
