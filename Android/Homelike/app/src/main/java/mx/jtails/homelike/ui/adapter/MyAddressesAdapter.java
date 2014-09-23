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
import mx.jtails.homelike.api.model.Direccion;

/**
 * Created by GrzegorzFeathers on 9/22/14.
 */
public class MyAddressesAdapter extends ArrayAdapter<Direccion> {

    private List<Direccion> mAddresses;

    public MyAddressesAdapter(Context context, List<Direccion> addresses) {
        super(context, R.layout.list_item_address, addresses);
        this.mAddresses = addresses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(this.getItemViewType(position) == 1){
            return LayoutInflater.from(this.getContext()).inflate(
                    R.layout.list_item_new_address, parent, false);
        }

        if(view == null){
            view = LayoutInflater.from(this.getContext()).inflate(
                    R.layout.list_item_address, parent, false);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Direccion address = this.getItem(position);
        holder.lblAlias.get().setText(address.getAlias());
        holder.lblAddress.get().setText(
                address.getCalle() + " #" + address.getNexterior() + ", "
                        + address.getColonia() + ", " + address.getDelegacion());
        holder.imgBookmark.get().setVisibility(View.GONE);

        return view;
    }

    public void updateContent(List<Direccion> addresses){
        this.mAddresses = addresses;
        this.notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == this.mAddresses.size() ? 1 : 0;
    }

    @Override
    public Direccion getItem(int position) {
        return this.mAddresses.get(position);
    }

    @Override
    public int getCount() {
        return this.mAddresses.size() + 1;
    }

    private class ViewHolder {

        private WeakReference<TextView> lblAlias;
        private WeakReference<TextView> lblAddress;
        private WeakReference<ImageView> imgBookmark;

        public ViewHolder(View view) {
            this.lblAlias = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_alias));
            this.lblAddress = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_resumed_address));
            this.imgBookmark = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_bookmark));
        }

    }

}
