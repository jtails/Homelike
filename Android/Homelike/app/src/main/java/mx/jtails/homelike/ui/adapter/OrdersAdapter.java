package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public class OrdersAdapter extends ArrayAdapter<Pedido> {

    private List<Pedido> mOrders;

    private DisplayImageOptions mLoaderOptions;
    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mTimeFormat;

    public OrdersAdapter(Context context, List<Pedido> orders){
        super(context, R.layout.list_item_order, orders);
        this.mOrders = orders;
        this.mDateFormat = new SimpleDateFormat("dd.MMM.y");
        this.mTimeFormat = new SimpleDateFormat("HH:mm");
        this.mLoaderOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_homelike_splash_logo)
                .showImageForEmptyUri(R.drawable.ic_homelike_splash_logo)
                .showImageOnFail(R.drawable.ic_homelike_splash_logo)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
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
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.list_item_order,
                    parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Pedido order = this.getItem(position);

        holder.lblOrderDate.get().setText(this.mDateFormat.format(
                new Date(order.getFechaHoraPedido().getValue())));
        holder.lblOrderTime.get().setText(this.mTimeFormat.format(
                new Date(order.getFechaHoraPedido().getValue())) + " hrs");
        holder.lblOrderProvider.get().setText(order.getProveedor().getNombre());
        holder.lblOrderId.get().setText("ID." + order.getIdPedido());
        holder.lblOrderStatus.get().setText(HomelikeUtils.getOrderStatusStringRes(order.getStatus()));
        ImageLoader.getInstance().displayImage(order.getProveedor().getLogo(),
                holder.imgProviderLogo.get(), this.mLoaderOptions);

        return view;
    }

    public void updateContent(List<Pedido> orders){
        this.mOrders = orders;
        this.notifyDataSetInvalidated();
    }

    private class ViewHolder {

        private WeakReference<ImageView> imgProviderLogo;
        private WeakReference<TextView> lblOrderDate;
        private WeakReference<TextView> lblOrderTime;
        private WeakReference<TextView> lblOrderProvider;
        private WeakReference<TextView> lblOrderId;
        private WeakReference<TextView> lblOrderStatus;

        private ViewHolder(View view) {
            this.imgProviderLogo = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_provider_logo));
            this.lblOrderDate = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_order_date));
            this.lblOrderStatus = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_status));
            this.lblOrderTime = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_order_time));
            this.lblOrderProvider = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_order_provider));
            this.lblOrderId = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_order_id));
        }

    }
}
