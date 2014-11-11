package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.DeleteAddressRequest;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListAddressesRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.ui.adapter.MyAddressesAdapter;
import mx.jtails.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 9/19/14.
 */
public class MyAddressesFragment extends Fragment
    implements AdapterView.OnItemClickListener, ListAddressesRequest.OnListAddressesResponseHandler,
        ActionMode.Callback, AdapterView.OnItemLongClickListener, DeleteAddressRequest.OndeleteAddressResponseHandler {

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
        this.mListAddresses.setOnItemLongClickListener(this);
        this.mListAddresses.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mAddresses = HomelikeDBManager.getDBManager().loadAddresses();
        this.mAdapter.updateContent(this.mAddresses);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(this.mActionMode != null){ this.mActionMode.finish(); return; }
        if(position == this.mAddresses.size()) {
            this.startRegister();
            return;
        } else if(this.mAddressMode != AddressMode.EDIT) {
            this.startOrder(this.mAddresses.get(position));
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
        List<Integer> newAddressIds = new ArrayList<Integer>(addresses.size());
        for(Direccion address : addresses){
            newAddressIds.add(address.getIdDireccion());
        }
        for(Direccion address : this.mAddresses){
            if(!newAddressIds.contains(address.getIdDireccion())){
                HomelikeDBManager.getDBManager().removeAddress(address.getIdDireccion());
            }
        }

        HomelikeDBManager dbManager = HomelikeDBManager.getDBManager();
        dbManager.saveAddresses(addresses);
        this.mAddresses = dbManager.loadAddresses();
        this.mAdapter.updateContent(this.mAddresses);
        if(this.getActivity() != null){
            ((ActionBarActivity) this.getActivity()).setSupportProgressBarIndeterminateVisibility(false);
        }
    }

    private int currentPosition = 0;
    private ProgressDialog mDeletingDialog;
    private ActionMode mActionMode = null;

    @Override
    public void onStop() {
        super.onStop();
        if(this.mActionMode != null){
            this.mActionMode.finish();
        }
    }

    @Override
    public void onDeleteAddressResponse() {
        if(this.mDeletingDialog != null){
            this.mDeletingDialog.dismiss();
            this.mDeletingDialog = null;
        }

        HomelikeDBManager dbManager = HomelikeDBManager.getDBManager();
        dbManager.removeAddress(this.mAdapter.getItem(this.currentPosition)
                .getIdDireccion());
        this.mAddresses = dbManager.loadAddresses();
        this.mAdapter.updateContent(this.mAddresses);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == this.mAddresses.size() || this.mAddressMode != AddressMode.EDIT){
            return false;
        }
        this.currentPosition = position;
        this.mListAddresses.setSelection(this.currentPosition);
        ((ActionBarActivity) this.getActivity()).startSupportActionMode(this);
        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        this.mActionMode = actionMode;
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.addresses, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_edit:{
                actionMode.finish();
                this.startEdit(this.mAdapter.getItem(this.currentPosition));
                return true;
            }
            case R.id.action_delete:{
                actionMode.finish();
                new AlertDialog.Builder(this.getActivity())
                        .setCancelable(true)
                        .setTitle(R.string.confirm)
                        .setMessage(R.string.delete_address_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onDeletionConfirmed();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
                return true;
            }
            default: return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        this.mActionMode = null;
    }

    private void onDeletionConfirmed(){
        this.mDeletingDialog = ProgressDialog.show(getActivity(),
                null, getString(R.string.deleting_address), false, false);
        new DeleteAddressRequest(MyAddressesFragment.this,
                this.mAdapter.getItem(this.currentPosition)).executeAsync();
    }
}
