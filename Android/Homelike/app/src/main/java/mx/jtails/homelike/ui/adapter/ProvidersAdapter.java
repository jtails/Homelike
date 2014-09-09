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
import mx.jtails.homelike.api.model.Proveedor;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class ProvidersAdapter extends ArrayAdapter<Proveedor> {

    private List<Proveedor> mProviders;

    public ProvidersAdapter(Context context, List<Proveedor> providers){
        super(context, R.layout.list_item_provider, providers);
        this.mProviders = providers;
    }

    public void updateContent(List<Proveedor> providers){
        this.mProviders = providers;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.list_item_provider,
                    parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Proveedor provider = this.getItem(position);
        holder.lblProviderName.get().setText(provider.getNombre());

        return view;
    }

    @Override
    public Proveedor getItem(int position) {
        return this.mProviders.get(position);
    }

    @Override
    public int getCount() {
        return this.mProviders.size();
    }

    private class ViewHolder {
        private WeakReference<TextView> lblProviderName;

        public ViewHolder(View view) {
            this.lblProviderName = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_provider_name));
        }
    }
}
