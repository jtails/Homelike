package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Pedido;

/**
 * Created by GrzegorzFeathers on 10/2/14.
 */
public class CommentsAdapter extends ArrayAdapter<Pedido> {

    private List<Pedido> mOrders;
    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mTimeFormat;

    public CommentsAdapter(Context context, List<Pedido> orders) {
        super(context, R.layout.list_item_comment, orders);
        this.mOrders = orders;
        this.mDateFormat = new SimpleDateFormat("dd.MMM.y");
        this.mTimeFormat = new SimpleDateFormat("HH:mm");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(this.getContext()).inflate(
                    R.layout.list_item_comment, parent, false);
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
        holder.ratingService.get().setRating(order.getCalificacion());
        holder.lblComment.get().setText(order.getComentarioEntregaCliente());

        return view;
    }

    @Override
    public int getCount() {
        return this.mOrders.size();
    }

    @Override
    public Pedido getItem(int position) {
        return this.mOrders.get(position);
    }

    public void updateContent(List<Pedido> orders){
        this.mOrders = orders;
        this.notifyDataSetChanged();
    }

    private class ViewHolder {

        private WeakReference<TextView> lblOrderDate;
        private WeakReference<TextView> lblOrderTime;
        private WeakReference<RatingBar> ratingService;
        private WeakReference<TextView> lblComment;

        public ViewHolder(View view){
            this.lblOrderDate = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_comment_date));
            this.lblOrderTime = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_comment_time));
            this.ratingService = new WeakReference<RatingBar>(
                    (RatingBar) view.findViewById(R.id.rating_order));
            this.lblComment = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_comment_content));
        }

    }

}
