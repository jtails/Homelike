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
import java.util.Date;
import java.util.List;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.HorariosProveedor;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class ProvidersAdapter extends ArrayAdapter<Proveedor> {

    private List<Proveedor> mProviders;
    private OnShowCommentsClickedListener mListener;
    private DisplayImageOptions loaderOptions;

    public ProvidersAdapter(Context context, OnShowCommentsClickedListener listener,
                            List<Proveedor> providers){
        super(context, R.layout.list_item_provider, providers);
        this.mProviders = providers;
        this.mListener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        holder.lblProviderAddress.get().setText(provider.getCalle() + " #" + provider.getNexterior() + ", "
                + provider.getColonia() + ", " + provider.getDelegacion());
        holder.lblProviderSlogan.get().setText(provider.getSlogan());
        holder.ratingProvider.get().setRating((float) provider.getCalificacion());
        ImageLoader.getInstance().displayImage(
                provider.getLogo(), holder.imgProviderLogo.get(), this.loaderOptions);
        holder.layoutComments.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onShowCommentsClicked(position);
            }
        });

        holder.layoutSchedules.get().removeAllViews();
        if(provider.getHorarios() != null && !provider.getHorarios().isEmpty()){
            this.addSchedules(holder.layoutSchedules.get(), provider.getHorarios());
        }

        return view;
    }

    private void addSchedules(ViewGroup container, List<HorariosProveedor> schedules){
        Context ctx = this.getContext();
        for(HorariosProveedor schedule : schedules){
            int scheduleDaysRes = HomelikeUtils.getScheduleDaysStringRes(schedule.getIdHorario());
            if(scheduleDaysRes < 0){
                continue;
            }

            String scheduleDays = ctx.getString(scheduleDaysRes);
            String scheduleTime = HomelikeUtils.getScheduleString(ctx,
                    new Date(schedule.getAbrimos().getValue()),
                    new Date(schedule.getCerramos().getValue()));

            TextView lblSchedule = (TextView) LayoutInflater.from(ctx)
                    .inflate(R.layout.list_item_schedule, container, false);
            lblSchedule.setText(new StringBuilder()
                    .append(scheduleDays).append(" ")
                    .append(scheduleTime).toString());
            container.addView(lblSchedule);
        }
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
        private WeakReference<TextView> lblProviderAddress;
        private WeakReference<RatingBar> ratingProvider;
        private WeakReference<View> layoutComments;
        private WeakReference<ViewGroup> layoutSchedules;

        public ViewHolder(View view) {
            this.lblProviderName = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_provider_name));
            this.lblProviderAddress = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_provider_address));
            this.lblProviderSlogan = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_provider_slogan));
            this.imgProviderLogo = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_provider_logo));
            this.ratingProvider = new WeakReference<RatingBar>(
                    (RatingBar) view.findViewById(R.id.rating_provider));
            this.layoutComments = new WeakReference<View>(
                    view.findViewById(R.id.layout_comments));
            this.layoutSchedules = new WeakReference<ViewGroup>(
                    (ViewGroup) view.findViewById(R.id.layout_schedules));
        }
    }

    public interface OnShowCommentsClickedListener {
        public void onShowCommentsClicked(int position);
    }
}
