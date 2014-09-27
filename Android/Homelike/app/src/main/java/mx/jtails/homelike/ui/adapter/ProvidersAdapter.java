package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.lang.ref.WeakReference;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Proveedor;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class ProvidersAdapter extends ArrayAdapter<Proveedor> {

    private List<Proveedor> mProviders;

    private DisplayImageOptions loaderOptions;

    public ProvidersAdapter(Context context, List<Proveedor> providers){
        super(context, R.layout.list_item_provider, providers);
        this.mProviders = providers;
        this.loaderOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_homelike_splash_logo)
                .showImageForEmptyUri(R.drawable.ic_homelike_splash_logo)
                .showImageOnFail(R.drawable.ic_homelike_splash_logo)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
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
        holder.lblProviderSlogan.get().setText(provider.getSlogan());
        //holder.lblRating.get().setText(String.valueOf((float) provider.getCalificacion()));
        holder.ratingProvider.get().setRating((float) provider.getCalificacion());
        ImageLoader.getInstance().displayImage(
                provider.getLogo(), holder.imgProviderLogo.get(), this.loaderOptions);

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

        private WeakReference<ImageView> imgProviderLogo;
        private WeakReference<TextView> lblProviderName;
        private WeakReference<TextView> lblProviderSlogan;
        private WeakReference<RatingBar> ratingProvider;
        //private WeakReference<TextView> lblRating;

        public ViewHolder(View view) {
            this.lblProviderName = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_provider_name));
            this.lblProviderSlogan = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_provider_slogan));
            this.imgProviderLogo = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_provider_logo));
            this.ratingProvider = new WeakReference<RatingBar>(
                    (RatingBar) view.findViewById(R.id.rating_provider));
            //this.lblRating = new WeakReference<TextView>(
              //      (TextView) view.findViewById(R.id.lbl_rating));
        }
    }
}
