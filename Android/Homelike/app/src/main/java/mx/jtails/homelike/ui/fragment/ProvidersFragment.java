package mx.jtails.homelike.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.ListProvidersRequest;
import mx.jtails.homelike.ui.adapter.ProvidersAdapter;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class ProvidersFragment extends Fragment
    implements AdapterView.OnItemClickListener,
        ListProvidersRequest.ListProvidersResponseHandler {

    public interface OnProviderSelectedListener {
        public void onProviderSelected(Proveedor proveedor);
    }

    private enum ContentDisplayMode {
        LOAD, CONTENT, CONTENT_EMPTY;
    }

    private ListProvidersRequest mProvidersRequest;
    private OnProviderSelectedListener mProviderListener;

    private static final String ARG_ADDRESS_ID = "arg_address_id";
    private static final String ARG_SERVICE_ID = "arg_service_id";

    private int mAddressId;
    private int mServiceId;
    private Direccion mAddress;

    private List<Proveedor> mProviders = new ArrayList<Proveedor>();
    private ProvidersAdapter mProvidersAdapter;

    private View mLayoutContent;
    private AbsListView mListView;
    private ProgressBar mProgress;
    private TextView mLblEmpty;

    public static ProvidersFragment getInstance(int addressId, int serviceId){
        Bundle extras = new Bundle();
        extras.putInt(ARG_ADDRESS_ID, addressId);
        extras.putInt(ARG_SERVICE_ID, serviceId);

        ProvidersFragment fragment = new ProvidersFragment();
        fragment.setArguments(extras);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loadAddress(savedInstanceState == null ? this.getArguments() : savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mProviderListener = (OnProviderSelectedListener) activity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLayoutContent = view.findViewById(R.id.layout_providers_content);
        this.mListView = (AbsListView) view.findViewById(R.id.list_providers);
        this.mProgress = (ProgressBar) view.findViewById(R.id.progress_providers);
        this.mLblEmpty = (TextView) view.findViewById(R.id.lbl_empty);
        this.mProvidersAdapter = new ProvidersAdapter(this.getActivity(), this.mProviders);

        this.mListView.setAdapter(this.mProvidersAdapter);
        this.mListView.setOnItemClickListener(this);
        this.displayContentMode(ContentDisplayMode.LOAD);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mProvidersRequest = new ListProvidersRequest(this,
                this.mServiceId, this.mAddress);
        this.mProvidersRequest.executeAsync();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(this.mProvidersRequest != null){
            this.mProvidersRequest.cancelRequest();
        }
    }

    private void loadAddress(Bundle args){
        this.mAddressId = args.getInt(ARG_ADDRESS_ID);
        this.mServiceId = args.getInt(ARG_SERVICE_ID);

        this.mAddress = HomelikeDBManager.getDBManager().getAddress(this.mAddressId);
        if(this.mAddress == null){
            Toast.makeText(this.getActivity(), "Failed to recover address with id: "
                + this.mAddressId, Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
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

                this.mProvidersAdapter.updateContent(this.mProviders);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_providers, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(this.mProviderListener != null){
            this.mProviderListener.onProviderSelected(this.mProvidersAdapter.getItem(position));
        }
    }

    @Override
    public void onListProvidersResponse(List<Proveedor> providers) {
        this.mProviders = providers;

        if(this.mProviders.size() > 0){
            this.displayContentMode(ContentDisplayMode.CONTENT);
        } else {
            this.displayContentMode(ContentDisplayMode.CONTENT_EMPTY);
        }
    }

}
