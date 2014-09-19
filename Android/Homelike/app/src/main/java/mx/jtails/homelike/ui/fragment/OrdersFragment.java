package mx.jtails.homelike.ui.fragment;

import android.app.Activity;
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
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListOrdersRequest;
import mx.jtails.homelike.ui.adapter.OrdersAdapter;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class OrdersFragment extends Fragment
    implements AdapterView.OnItemClickListener,
        ListOrdersRequest.ListOrdersResponseHandler {

    private List<Pedido> mOrders = new ArrayList<Pedido>();

    private OrdersAdapter mAdapter;

    private HomelikeApiRequest mApiRequest;

    private AbsListView mListView;
    private View mLayoutContent;
    private ProgressBar mProgressMain;

    private enum ContentDisplayMode {
        LOAD, CONTENT;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ActionBar ab = ((ActionBarActivity) activity).getSupportActionBar();
        ab.setSubtitle("Orders");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        this.mApiRequest = new ListOrdersRequest(this,
                HomelikePreferences.loadInt(HomelikePreferences.ACCOUNT_ID, -1));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.services, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mListView = (AbsListView) view.findViewById(R.id.list_orders);
        this.mLayoutContent = view.findViewById(R.id.layout_orders_content);
        this.mProgressMain = (ProgressBar) view.findViewById(R.id.progress_main);
        this.mAdapter = new OrdersAdapter(this.getActivity(), this.mOrders);

        this.mListView.setOnItemClickListener(this);
        this.mListView.setAdapter(this.mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadOrders();
    }

    private void loadOrders(){
        this.displayContentMode(ContentDisplayMode.LOAD, true);
        this.mApiRequest.executeAsync();
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
                this.loadOrders();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayContentMode(ContentDisplayMode displayMode, boolean invalidate){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.GONE);
                this.mProgressMain.setVisibility(View.VISIBLE);
                break;
            }
            case CONTENT: {
                this.mProgressMain.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(false);

                if( invalidate ) { this.mAdapter.updateContent(this.mOrders); }
                break;
            }
        }
    }

    @Override
    public void onListOrdersResponse(List<Pedido> orders) {
        this.mOrders = orders;
        this.displayContentMode(ContentDisplayMode.CONTENT, true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pedido order = this.mAdapter.getItem(position);

    }

}
