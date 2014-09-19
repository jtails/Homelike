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
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Servicio;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListServicesRequest;
import mx.jtails.homelike.ui.MyAddressesActivity;
import mx.jtails.homelike.ui.adapter.ServicesAdapter;
import mx.jtails.homelike.util.HomelikePreferences;

public class ServicesFragment extends Fragment implements AdapterView.OnItemClickListener,
        ListServicesRequest.ListServicesResponseHandler {

    private ServicesAdapter mAdapter;
    private HomelikeApiRequest mApiRequest;

    private AbsListView mListView;
    private View mLayoutContent;
    private ProgressBar mProgressMain;

    private List<Servicio> mServices = new ArrayList<Servicio>();

    private enum ContentDisplayMode {
        LOAD, PARTIAL_LOAD, CONTENT;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar ab = ((ActionBarActivity) this.getActivity()).getSupportActionBar();
        ab.setSubtitle("Services");
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
        this.mProgressMain = (ProgressBar) view.findViewById(R.id.progress_main);
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
        if(HomelikePreferences.containsPreference(HomelikePreferences.DEFAULT_ADDRESS)) {
            // TODO display dialog with default address
        }
        Servicio service = this.mAdapter.getItem(position);
        Bundle args = new Bundle();
        args.putInt(MyAddressesActivity.ARG_REQUESTED_SERVICE_ID, service.getIdServicio());

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
                this.mLayoutContent.setVisibility(View.GONE);
                this.mProgressMain.setVisibility(View.VISIBLE);
                break;
            }
            case PARTIAL_LOAD: {
                this.mProgressMain.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(true);

                this.mAdapter.updateContent(this.mServices);
                break;
            }
            case CONTENT: {
                this.mProgressMain.setVisibility(View.GONE);
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
