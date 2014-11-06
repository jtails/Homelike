package mx.jtails.homelike.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.ListProvidersRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.ui.adapter.ProvidersAdapter;
import mx.jtails.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class ProvidersFragment extends Fragment
    implements AdapterView.OnItemClickListener,
        ListProvidersRequest.ListProvidersResponseHandler,
        ProvidersAdapter.OnShowCommentsClickedListener {

    private enum ContentDisplayMode {
        LOAD, PARTIAL_LOAD, CONTENT, CONTENT_EMPTY;
    }

    private ListProvidersRequest mProvidersRequest;

    public static final String ARG_ADDRESS_ID = "arg_address_id";
    public static final String ARG_SERVICE_ID = "arg_service_id";

    private int mAddressId;
    private int mServiceId;
    private Direccion mAddress;

    private List<Proveedor> mProviders = new ArrayList<Proveedor>();
    private ProvidersAdapter mProvidersAdapter;

    private View mLayoutContent;
    private AbsListView mListView;
    private View mLayoutLoading;
    private View mLayoutEmpty;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLayoutContent = view.findViewById(R.id.layout_providers_content);
        this.mListView = (AbsListView) view.findViewById(R.id.list_providers);
        this.mLayoutLoading = view.findViewById(R.id.layout_loading);
        this.mLayoutEmpty = view.findViewById(R.id.layout_empty);
        this.mProvidersAdapter = new ProvidersAdapter(this.getActivity(), this, this.mProviders);

        this.mListView.setAdapter(this.mProvidersAdapter);
        this.mListView.setOnItemClickListener(this);

        view.findViewById(R.id.btn_request_provider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestProvider();
            }
        });
    }

    private void requestProvider(){
        Bundle args = new Bundle();
        args.putString(SuggestionsFragment.EXTRA_DEFAULT_OPTION, "Solicitud de Proveedor");
        ((HomeActivity) this.getActivity()).replaceStack(
                HomeMenuSection.SUGGESTIONS.getFragmentClass(), args);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mProvidersRequest = new ListProvidersRequest(this,
                this.mServiceId, this.mAddress);
        this.mProvidersRequest.executeAsync();

        if(this.mProviders.isEmpty()){
            this.displayContentMode(ContentDisplayMode.LOAD);
        } else {
            this.displayContentMode(ContentDisplayMode.PARTIAL_LOAD);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(this.mProvidersRequest != null){
            this.mProvidersRequest.cancelRequest();
        }
        ((ActionBarActivity) this.getActivity()).setSupportProgressBarIndeterminateVisibility(false);
    }

    private void loadAddress(Bundle args){
        this.mAddressId = args.getInt(ARG_ADDRESS_ID);
        this.mServiceId = args.getInt(ARG_SERVICE_ID);

        this.mAddress = HomelikeDBManager.getDBManager().getAddress(this.mAddressId);
        if(this.mAddress == null){
            Toast.makeText(this.getActivity(),
                String.format(this.getString(R.string.error_load_address), this.mAddressId),
                Toast.LENGTH_SHORT).show();
            ((HomeActivity) this.getActivity()).clearStack();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_ADDRESS_ID, this.mAddressId);
        outState.putInt(ARG_SERVICE_ID, this.mServiceId);
        super.onSaveInstanceState(outState);
    }

    private void displayContentMode(ContentDisplayMode displayMode){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.GONE);
                this.mLayoutLoading.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(false);
                break;
            }
            case PARTIAL_LOAD: {
                this.mLayoutEmpty.setVisibility(View.GONE);
                this.mListView.setVisibility(View.VISIBLE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLayoutLoading.setVisibility(View.GONE);

                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(true);
                break;
            }
            case CONTENT: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLayoutEmpty.setVisibility(View.GONE);
                this.mListView.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(false);

                if(this.mProvidersAdapter.getCount() != this.mProviders.size()){
                    this.mProvidersAdapter.updateContent(this.mProviders);
                }
                break;
            }
            case CONTENT_EMPTY: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLayoutEmpty.setVisibility(View.VISIBLE);
                this.mListView.setVisibility(View.GONE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(false);
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
        Proveedor provider = this.mProvidersAdapter.getItem(position);

        ((HomeActivity) this.getActivity()).pushToStack(
                CreateOrderFragment.newInstance(this.mAddressId, this.mServiceId, provider),
                CreateOrderFragment.class.getName());
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

    @Override
    public void onShowCommentsClicked(int position) {
        Proveedor provider = this.mProvidersAdapter.getItem(position);
        ((HomeActivity) this.getActivity()).pushToStack(
                CommentsToProviderFragment.newInstance(provider.getIdProveedor()),
                CommentsToProviderFragment.class.getName());
    }

}
