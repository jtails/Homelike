package mx.jtails.homelike.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Servicio;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListServicesRequest;
import mx.jtails.homelike.ui.CheckOrderActivity;
import mx.jtails.homelike.ui.MyAddressesActivity;
import mx.jtails.homelike.ui.adapter.ServicesAdapter;
import mx.jtails.homelike.util.HomeMenuSection;

public class ServicesFragment extends Fragment implements AdapterView.OnItemClickListener,
        ListServicesRequest.ListServicesResponseHandler {

    private ServicesAdapter mAdapter;
    private HomelikeApiRequest mApiRequest;

    private AbsListView mListView;
    private View mLayoutContent;
    private View mLayoutLoading;

    private List<Servicio> mServices = new ArrayList<Servicio>();

    private enum ContentDisplayMode {
        LOAD, PARTIAL_LOAD, CONTENT;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar ab = ((ActionBarActivity) this.getActivity()).getSupportActionBar();
        ab.setSubtitle(HomeMenuSection.SERVICES.getSubtitleRes());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        this.mApiRequest = new ListServicesRequest(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.services, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mListView = (AbsListView) view.findViewById(R.id.list_services);
        this.mLayoutContent = view.findViewById(R.id.layout_services_content);
        this.mLayoutLoading = view.findViewById(R.id.layout_loading);
        this.mAdapter = new ServicesAdapter(this.getActivity(), this.mServices);

        this.mListView.setOnItemClickListener(this);
        this.mListView.setAdapter(this.mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mServices = HomelikeDBManager.getDBManager().loadServices();
        this.loadServices(this.mServices.isEmpty());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final int serviceId = this.mAdapter.getItem(position).getIdServicio();
        /*
        if(HomelikeDBManager.getDBManager().hasFavoriteAddress()) {
            final Direccion a = HomelikeDBManager.getDBManager().getFavouriteAddress();

            String addressString = a.getAlias() + ":\n\n" + a.getCalle() + " #" + a.getNexterior()
                    + ", " + a.getColonia() + ", " + a.getDelegacion();
            new AlertDialog.Builder(this.getActivity())
                    .setTitle(R.string.favourite_address)
                    .setMessage(String.format(this.getString(R.string.use_favourite_address),
                            addressString))
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goToNewOrder(a.getIdDireccion(), serviceId);
                        }
                    })
                    .setNegativeButton(R.string.change_address, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goToAddressSelection(serviceId);
                        }
                    })
                    .show();
        } else {
        */
            this.goToAddressSelection(serviceId);
        //}
    }

    private void goToNewOrder(int addressId, int serviceId){
        Bundle args = new Bundle();
        Intent intent = new Intent(this.getActivity(), CheckOrderActivity.class);
        args.putInt(CheckOrderActivity.ARG_ADDRESS_ID, addressId);
        args.putInt(CheckOrderActivity.ARG_SERVICE_ID, serviceId);
        intent.putExtras(args);
        this.startActivity(intent);
    }

    private void goToAddressSelection(int serviceId){
        Bundle args = new Bundle();
        args.putInt(MyAddressesActivity.ARG_REQUESTED_SERVICE_ID, serviceId);

        Intent intent = new Intent(this.getActivity(), MyAddressesActivity.class);
        intent.putExtras(args);
        this.getActivity().startActivity(intent);
    }

    private void loadServices(boolean fullLoad){
        if(fullLoad){
            this.displayContentMode(ContentDisplayMode.LOAD, false);
        } else {
            this.displayContentMode(ContentDisplayMode.PARTIAL_LOAD, true);
        }
        this.mApiRequest.executeAsync();
    }

    private void displayContentMode(ContentDisplayMode displayMode, boolean invalidate){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.INVISIBLE);
                this.mLayoutLoading.setVisibility(View.VISIBLE);
                break;
            }
            case PARTIAL_LOAD: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(true);

                this.mAdapter.updateContent(this.mServices);
                break;
            }
            case CONTENT: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(false);

                if( invalidate ) { this.mAdapter.updateContent(this.mServices); }
                break;
            }
        }
    }

    @Override
    public void onListServicesResponse(List<Servicio> services) {
        HomelikeDBManager dbManager = HomelikeDBManager.getDBManager();

        int affectedRows = dbManager.saveServices(services);
        if(affectedRows > this.mServices.size()){
            this.mServices = dbManager.loadServices();
            this.displayContentMode(ContentDisplayMode.CONTENT, true);
        } else {
            this.displayContentMode(ContentDisplayMode.CONTENT, false);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(this.mApiRequest != null){
            this.mApiRequest.cancelRequest();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                this.loadServices(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
