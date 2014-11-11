package mx.jtails.homelike.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListAddressesRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.ui.adapter.MyAddressesAdapter;
import mx.jtails.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 9/19/14.
 */
public class MyAddressesFragment extends Fragment
    implements AdapterView.OnItemClickListener, ListAddressesRequest.OnListAddressesResponseHandler {

    public static final String ARG_REQUESTED_SERVICE_ID = "requested_service_id";

    private AddressMode mAddressMode;
    private int mServiceId;

    private HomelikeApiRequest mListAddressesRequest;

    private ListView mListAddresses;
    private MyAddressesAdapter mAdapter;

    private List<Direccion> mAddresses = HomelikeDBManager.getDBManager().loadAddresses();

    private enum AddressMode {
        EDIT, ORDER
    }

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
        if(this.getArguments() == null && savedInstanceState == null){
            this.mAddressMode = AddressMode.EDIT;
        } else if (savedInstanceState == null){
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
        ab.setSubtitle(HomeMenuSection.ADDRESSES.getSubtitleRes());

        ((ActionBarActivity) this.getActivity()).setSupportProgressBarIndeterminateVisibility(true);
        this.mListAddressesRequest.executeAsync();
        this.mAddresses = HomelikeDBManager.getDBManager().loadAddresses();
    }

    private void loadService(Bundle args) {
        this.mServiceId = args.getInt(ARG_REQUESTED_SERVICE_ID, -1);
        if(this.mServiceId  < 0){
            this.mAddressMode = AddressMode.EDIT;
        } else {
            this.mAddressMode = AddressMode.ORDER;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_addresses, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mListAddresses = (ListView) view.findViewById(R.id.list_my_addresses);
        this.mAdapter = new MyAddressesAdapter(this.getActivity(), this.mAddresses);
        this.mListAddresses.setAdapter(this.mAdapter);
        this.mListAddresses.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mAddresses = HomelikeDBManager.getDBManager().loadAddresses();
        this.mAdapter.updateContent(this.mAddresses);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == this.mAddresses.size()) {
            this.startRegister();
            return;
        }

        switch (this.mAddressMode) {
            case ORDER:{
                this.startOrder(this.mAddresses.get(position));
                break;
            }
            case EDIT: {
                this.startEdit(this.mAddresses.get(position));
                break;
            }
        }
    }

    private void startRegister(){
        ((HomeActivity) this.getActivity()).pushToStack(AddressMapFragment.class, null);
    }

    private void startEdit(Direccion address){
        ((HomeActivity) this.getActivity()).pushToStack(
                AddressDetailsFragment.newInstance(address, true),
                AddressDetailsFragment.class.getName());
    }

    private void startOrder(Direccion address){
        Bundle args = new Bundle();
        args.putInt(ProvidersFragment.ARG_ADDRESS_ID, address.getIdDireccion());
        args.putInt(ProvidersFragment.ARG_SERVICE_ID, this.mServiceId);
        ((HomeActivity) this.getActivity()).pushToStack(
                ProvidersFragment.class, args);
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
        this.mAdapter.updateContent(this.mAddresses);
        if(this.getActivity() != null){
            ((ActionBarActivity) this.getActivity()).setSupportProgressBarIndeterminateVisibility(false);
        }
    }
}
