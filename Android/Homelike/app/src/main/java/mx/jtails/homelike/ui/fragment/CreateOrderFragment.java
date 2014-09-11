package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.request.ListProductsRequest;
import mx.jtails.homelike.ui.CheckOrderActivity;
import mx.jtails.homelike.ui.adapter.ProductsAdapter;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class CreateOrderFragment extends Fragment
    implements ListProductsRequest.ListProductsResponseHandler {

    private enum ContentDisplayMode {
        LOAD, CONTENT, CONTENT_EMPTY;
    }

    private ListProductsRequest mProductsRequest;

    private Proveedor mProvider;
    private List<Producto> mProducts = new ArrayList<Producto>();

    private ImageView mProviderLogo;
    private View mLayoutContent;
    private ProgressBar mProgress;
    private TextView mLblEmpty;

    private AbsListView mListView;
    private ProductsAdapter mProductsAdapter;

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
        this.mLayoutContent = view.findViewById(R.id.layout_products_content);
        this.mListView = (ListView) view.findViewById(R.id.list_products);
        this.mProgress = (ProgressBar) view.findViewById(R.id.progress_products);
        this.mLblEmpty = (TextView) view.findViewById(R.id.lbl_empty);

        this.mProductsAdapter = new ProductsAdapter(this.getActivity(), this.mProducts);
        this.mListView.setAdapter(this.mProductsAdapter);

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

        this.displayContentMode(ContentDisplayMode.LOAD);
        this.mProductsRequest = new ListProductsRequest(this, this.mProvider.getIdProveedor());
        this.mProductsRequest.executeAsync();
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

    @Override
    public void onListProductsResponse(List<Producto> products) {
        this.mProducts = products;
        if(this.mProducts.isEmpty()){
            this.displayContentMode(ContentDisplayMode.CONTENT_EMPTY);
        } else {
            this.displayContentMode(ContentDisplayMode.CONTENT);
        }
    }

    private void displayContentMode(ContentDisplayMode displayMode){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.GONE);
                this.mProgress.setVisibility(View.VISIBLE);
                break;
            }
            case CONTENT: {
                this.mProgress.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.GONE);
                this.mListView.setVisibility(View.VISIBLE);

                this.mProductsAdapter.updateContent(this.mProducts);
                break;
            }
            case CONTENT_EMPTY: {
                this.mProgress.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.VISIBLE);
                this.mListView.setVisibility(View.GONE);
                break;
            }
        }
    }
}
