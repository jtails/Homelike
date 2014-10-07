package mx.jtails.homelike.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Servicio;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListAddressesRequest;
import mx.jtails.homelike.ui.AddressActivity;
import mx.jtails.homelike.ui.CheckOrderActivity;
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
    private Servicio mService;

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
            this.mService = HomelikeDBManager.getDBManager().getService(this.mServiceId);
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
                this.startEdit(this.mAddresses.get(position).getIdDireccion());
                break;
            }
        }
    }

    private void startRegister(){
        Bundle args = new Bundle();
        Intent intent = new Intent(this.getActivity(), AddressActivity.class);
        args.putString(AddressActivity.ARG_ADDRESS_MODE, AddressActivity.MODE_REGISTER_ADDRESS);
        intent.putExtras(args);
        this.startActivity(intent);
    }

    private void startEdit(int addressId){
        Bundle args = new Bundle();
        Intent intent = new Intent(this.getActivity(), AddressActivity.class);
        args.putString(AddressActivity.ARG_ADDRESS_MODE, AddressActivity.MODE_OPEN_ADDRESS);
        args.putInt(AddressActivity.ARG_ADDRESS_ID, addressId);
        intent.putExtras(args);
        this.startActivity(intent);
    }

    private void startOrder(Direccion address){
        Bundle args = new Bundle();
        Intent intent = new Intent(this.getActivity(), CheckOrderActivity.class);
        args.putInt(CheckOrderActivity.ARG_ADDRESS_ID, address.getIdDireccion());
        args.putInt(CheckOrderActivity.ARG_SERVICE_ID, this.mServiceId);
        intent.putExtras(args);
        this.startActivity(intent);
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
