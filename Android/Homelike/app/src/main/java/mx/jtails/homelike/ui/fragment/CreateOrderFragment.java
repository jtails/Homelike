package mx.jtails.homelike.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Proveedor;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class CreateOrderFragment extends Fragment {

    private Proveedor mProvider;

    private ImageView mProviderLogo;

    private DisplayImageOptions mLoaderOptions;

    public CreateOrderFragment(){
        this.mLoaderOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_homelike_splash_logo)
                .showImageForEmptyUri(R.drawable.ic_homelike_splash_logo)
                .showImageOnFail(R.drawable.ic_homelike_splash_logo)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mProviderLogo = (ImageView) view.findViewById(R.id.img_provider_logo);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.refreshProviderView(null);
    }

    public void refreshProviderView(Proveedor provider){
        if(provider == null){ return; }
        this.mProvider = provider;

        ImageLoader.getInstance().displayImage(
            this.mProvider.getLogo(), this.mProviderLogo, this.mLoaderOptions);
    }

}
