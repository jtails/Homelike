package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Servicio;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public class ServicesAdapter extends ArrayAdapter<Servicio> {

    private List<Servicio> mServices;

    public ServicesAdapter(Context context, List<Servicio> services){
        super(context, R.layout.list_item_service, services);
        this.mServices = services;
    }

    @Override
    public int getCount() {
        return this.mServices.size();
    }

    @Override
    public Servicio getItem(int position) {
        return this.mServices.get(position);
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

        Servicio service = this.getItem(position);
        holder.lblServiceName.get().setText(service.getNombre());
        holder.imgServiceIcon.get().setImageResource(
                service.getNombre().toLowerCase(Locale.ENGLISH).equals("agua") ?
                    R.drawable.ic_service_water : R.drawable.ic_service_gas);
        holder.frameRightDivider.get().setVisibility(position % 2 == 0 ?
            View.VISIBLE : View.GONE);

        return view;
    }

    public void updateContent(List<Servicio> services){
        this.mServices = services;
        this.notifyDataSetInvalidated();
    }

    private class ViewHolder {

        private ViewHolder(View view) {
            this.lblServiceName = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_service_name));
            this.imgServiceIcon = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_service_icon));
            this.frameRightDivider = new WeakReference<FrameLayout>(
                    (FrameLayout) view.findViewById(R.id.frame_right_divider));
        }

        private WeakReference<TextView> lblServiceName;
        private WeakReference<ImageView> imgServiceIcon;
        private WeakReference<FrameLayout> frameRightDivider;

    }
}
