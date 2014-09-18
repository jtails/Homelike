package mx.jtails.homelike.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import mx.jtails.homelike.R;
import mx.jtails.homelike.util.HomeMenuOption;
import mx.jtails.homelike.util.HomelikePreferences;
import mx.jtails.homelike.util.HomelikeUtils;

public class HomeMenuFragment extends Fragment {

    public static final HomeMenuOption DEFAULT_HOME_CONTENT = HomeMenuOption.SERVICES;

    private OnHomeMenuOptionSelectedListener mListener;

    private DisplayImageOptions mLoaderOptions;

    public HomeMenuFragment(){
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ((TextView) view.findViewById(R.id.lbl_user_name)).setText(
                HomelikePreferences.loadString(HomelikePreferences.USER_NAME, "No user name"));
        ImageView displayImage = (ImageView) view.findViewById(R.id.img_display_img);
        ImageLoader.getInstance().displayImage(
                HomelikeUtils.getImageUrlInSize(displayImage.getLayoutParams().height,
                        HomelikePreferences.loadString(HomelikePreferences.USER_IMG, "")),
                displayImage, this.mLoaderOptions);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnHomeMenuOptionSelectedListener {
        public void onHomeMenuOptionSelected(HomeMenuOption option);
    }

}
