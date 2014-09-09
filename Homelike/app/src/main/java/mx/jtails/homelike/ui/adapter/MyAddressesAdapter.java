package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;

/**
 * Created by GrzegorzFeathers on 9/3/14.
 */
public class MyAddressesAdapter extends ArrayAdapter<Direccion> {

    private static final int VIEW_TYPES = 2;

    private List<Direccion> mAddresses;

    public MyAddressesAdapter(Context context, List<Direccion> addresses) {
        super(context, 0, addresses);
        this.mAddresses = addresses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position == this.getCount() - 1){
            return this.getAddAddressView(parent);
        }

        View view = LayoutInflater.from(this.getContext()).inflate(
                R.layout.list_item_address, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        Direccion address = this.getItem(position);
        holder.lblAlias.get().setText(address.getReferencia2());
        holder.lblAddress.get().setText(
                address.getCalle() + " #" + address.getNexterior() + ", "
                + address.getColonia() + ", " + address.getDelegacion());

        return view;
    }

    private View getAddAddressView(ViewGroup parent){
        View view = LayoutInflater.from(this.getContext()).inflate(
                R.layout.list_item_new_address, parent, false);
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPES;
    }

    @Override
    public Direccion getItem(int position) {
        if(position == this.getCount() - 1) {
            return null;
        } else {
            return this.mAddresses.get(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == this.getCount() - 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getCount() {
        return this.mAddresses.size() + 1;
    }

    public void updateContent(List<Direccion> addresses) {
        this.mAddresses = addresses;
    }


    private class ViewHolder {

        private WeakReference<TextView> lblAlias;
        private WeakReference<TextView> lblAddress;

        public ViewHolder(View view) {
            this.lblAlias = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_alias));
            this.lblAddress = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_resumed_address));
        }

    }

}
