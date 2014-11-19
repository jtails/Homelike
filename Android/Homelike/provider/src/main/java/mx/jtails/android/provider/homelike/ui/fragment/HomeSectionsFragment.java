package mx.jtails.android.provider.homelike.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import mx.jtails.android.provider.homelike.R;
import mx.jtails.android.provider.homelike.util.HomelikePreferences;
import mx.jtails.android.provider.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public class HomeSectionsFragment extends Fragment
        implements AdapterView.OnItemClickListener {

    private ListView mListSections;

    private DisplayImageOptions mLoaderOptions;

    private OnHomeMenuOptionSelectedListener mListener;

    public HomeSectionsFragment(){
        this.mLoaderOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_homelike_splash_logo)
                .showImageForEmptyUri(R.drawable.ic_homelike_splash_logo)
                .showImageOnFail(R.drawable.ic_homelike_splash_logo)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHomeMenuOptionSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(HomelikePreferences.loadString(HomelikePreferences.USER_NAME, null) != null){
            ((TextView) view.findViewById(R.id.lbl_user_name)).setText(
                    HomelikePreferences.loadString(HomelikePreferences.USER_NAME,
                            this.getString(R.string.no_username)));
            ImageView displayImage = (ImageView) view.findViewById(R.id.img_display_img);
            ImageLoader.getInstance().displayImage(
                    HomelikeUtils.getImageUrlInSize(displayImage.getLayoutParams().height,
                            HomelikePreferences.loadString(HomelikePreferences.USER_IMG, "")),
                    displayImage, this.mLoaderOptions);
        } else {
            view.findViewById(R.id.layout_me).setVisibility(View.GONE);
        }

        this.mListSections = (ListView) view.findViewById(R.id.list_home_menu);
        this.mListSections.setOnItemClickListener(this);
        this.mListSections.setAdapter(new HomeMenuAdapter(this.getActivity()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.notifyNewContent(position);
    }

    private void notifyNewContent(int section){
        if(this.mListener != null){
            this.mListener.onHomeMenuOptionSelected(HomeMenuSection.values()[section]);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public interface OnHomeMenuOptionSelectedListener {
        public void onHomeMenuOptionSelected(HomeMenuSection option);
    }

}
