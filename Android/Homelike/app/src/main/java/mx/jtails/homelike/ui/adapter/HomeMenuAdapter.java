package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.util.HomeLikeConfiguration;

/**
 * Created by GrzegorzFeathers on 9/18/14.
 */
public class HomeMenuAdapter extends ArrayAdapter<HomeLikeConfiguration.HomeMenuOption> {

    public HomeMenuAdapter(Context context) {
        super(context, R.layout.list_item_home_section, HomeLikeConfiguration.getMenuOptions());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(this.getContext()).inflate(
                    R.layout.list_item_home_section, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        HomeLikeConfiguration.HomeMenuOption section = this.getItem(position);
        holder.imgIcon.get().setImageResource(section.getIconRes());
        holder.lblTitle.get().setText(section.getSubtitleRes());

        return view;
    }

    @Override
    public int getCount() {
        return HomeLikeConfiguration.getMenuOptions().length;
    }

    @Override
    public HomeLikeConfiguration.HomeMenuOption getItem(int position) {
        return HomeLikeConfiguration.getMenuOptions()[position];
    }

    private class ViewHolder {

        private WeakReference<ImageView> imgIcon;
        private WeakReference<TextView> lblTitle;

        private ViewHolder(View view){
            this.imgIcon = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_icon));
            this.lblTitle = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_title));
        }

    }
}
