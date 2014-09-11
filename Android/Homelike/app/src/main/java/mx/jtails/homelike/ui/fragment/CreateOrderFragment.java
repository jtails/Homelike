package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import mx.jtails.homelike.ui.CheckOrderActivity;

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
        view.findViewById(R.id.btn_cancel_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCancelation();
            }
        });
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

    public void confirmCancelation(){
        new AlertDialog.Builder(this.getActivity())
                .setTitle("Cancel")
                .setMessage("Are you sure you want to cancel this order?")
                .setPositiveButton("Cancel Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((CheckOrderActivity) getActivity()).cancelOrder();
                    }
                })
                .setNegativeButton("Continue", null)
                .show();
    }

}
