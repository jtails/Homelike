package mx.jtails.homelike.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Servicio;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListAddressesRequest;
import mx.jtails.homelike.ui.AddressActivity;
import mx.jtails.homelike.ui.CheckOrderActivity;
import mx.jtails.homelike.ui.widget.TrackableScrollView;

/**
 * Created by GrzegorzFeathers on 9/19/14.
 */
public class MyAddressesFragment extends Fragment
    implements TrackableScrollView.Callbacks, ListAddressesRequest.OnListAddressesResponseHandler {

    public static final String ARG_REQUESTED_SERVICE_ID = "requested_service_id";

    private TrackableScrollView mScrollView;
    private View mHeader;
    private ViewGroup mLayoutAddresses;

    private int mServiceId;
    private Servicio mService;

    private HomelikeApiRequest mListAddressesRequest;

    private List<Direccion> mAddresses = HomelikeDBManager.getDBManager().loadAddresses();

    public static MyAddressesFragment getInstance(int serviceId){
        Bundle args = new Bundle();
        args.putInt(ARG_REQUESTED_SERVICE_ID, serviceId);

        MyAddressesFragment fragment = new MyAddressesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            this.loadService(this.getArguments());
        } else {
            this.loadService(savedInstanceState);
        }
        this.mListAddressesRequest = new ListAddressesRequest(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar ab = ((ActionBarActivity) this.getActivity()).getSupportActionBar();
        ab.setSubtitle("My Addresses");
    }

    private void loadService(Bundle args) {
        this.mServiceId = args.getInt(ARG_REQUESTED_SERVICE_ID, -1);
        this.mService = HomelikeDBManager.getDBManager().getService(this.mServiceId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_addresses, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.mScrollView = (TrackableScrollView) view.findViewById(R.id.scroll_view);
        this.mHeader = view.findViewById(R.id.layout_header);
        this.mLayoutAddresses = (ViewGroup) view.findViewById(R.id.layout_addresses);

        this.mScrollView.addCallbacks(this);
        this.onScrollChanged(0, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) this.getActivity()).setSupportProgressBarIndeterminateVisibility(true);
        this.mListAddressesRequest.executeAsync();
        this.mAddresses = HomelikeDBManager.getDBManager().loadAddresses();
        this.updateContent();
    }

    private void updateContent() {
        this.mLayoutAddresses.removeAllViews();
        int count = this.mAddresses.size();
        for (int i = 0 ; i < count ; i++) {
            final View rowView = LayoutInflater.from(this.getActivity()).inflate(
                    R.layout.list_item_address, this.mLayoutAddresses, false);
            ViewHolder holder = new ViewHolder(rowView);
            rowView.setTag(holder);

            Direccion a = this.mAddresses.get(i);
            holder.lblAlias.get().setText(a.getAlias());
            holder.lblAddress.get().setText(
                    a.getCalle() + " #" + a.getNexterior() + ", "
                            + a.getColonia() + ", " + a.getDelegacion());
            holder.imgBookmark.get().setVisibility(
                    a.getEsDefault() == 1 ? View.VISIBLE : View.GONE);

            final int position = i;
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(rowView, position);
                }
            });

            this.mLayoutAddresses.addView(rowView);
        }

        final View addAddressView = LayoutInflater.from(this.getActivity()).inflate(
                R.layout.list_item_new_address, this.mLayoutAddresses, false);
        addAddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(addAddressView, -1);
            }
        });
        this.mLayoutAddresses.addView(addAddressView);
    }

    public void onItemClick(View view, int position) {
        if(this.mServiceId < 0 || this.mService == null){
            // TODO start addresses to be edited
            return;
        }

        Bundle args = new Bundle();
        if(position < 0) {
            Intent intent = new Intent(this.getActivity(), AddressActivity.class);
            args.putString(AddressActivity.ARG_ADDRESS_MODE, AddressActivity.MODE_REGISTER_ADDRESS);
            intent.putExtras(args);
            this.startActivity(intent);
        } else {
            Direccion address = this.mAddresses.get(position);
            Intent intent = new Intent(this.getActivity(), CheckOrderActivity.class);
            args.putInt(CheckOrderActivity.ARG_ADDRESS_ID, address.getIdDireccion());
            args.putInt(CheckOrderActivity.ARG_SERVICE_ID, this.mServiceId);
            intent.putExtras(args);
            this.startActivity(intent);
        }
    }

    private class ViewHolder {

        private WeakReference<TextView> lblAlias;
        private WeakReference<TextView> lblAddress;
        private WeakReference<ImageView> imgBookmark;

        public ViewHolder(View view) {
            this.lblAlias = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_alias));
            this.lblAddress = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_resumed_address));
            this.imgBookmark = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_bookmark));
        }

    }

    @Override
    public void onScrollChanged(int deltaX, int deltaY) {
        int scrollY = this.mScrollView.getScrollY();
        this.mHeader.setTranslationY(scrollY * 0.5f);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_REQUESTED_SERVICE_ID, this.mServiceId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onListAddressesResponse(List<Direccion> addresses) {
        HomelikeDBManager dbManager = HomelikeDBManager.getDBManager();
        dbManager.saveAddresses(addresses);
        this.mAddresses = dbManager.loadAddresses();
        this.updateContent();
        ((ActionBarActivity) this.getActivity()).setSupportProgressBarIndeterminateVisibility(false);
    }
}
