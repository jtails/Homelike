package mx.jtails.homelike.ui.fragment;

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

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.ApiRequest;
import mx.jtails.homelike.request.ApiResponseHandler;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.ui.adapter.ListProviderOrdersRequest;
import mx.jtails.homelike.ui.adapter.ProviderOrdersAdapter;
import mx.jtails.homelike.util.HomelikePreferences;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public abstract class ProviderOrdersFragment extends Fragment
        implements AdapterView.OnItemClickListener, ApiResponseHandler<List<Pedido>> {

    private List<Pedido> mOrders = new ArrayList<Pedido>();

    private ProviderOrdersAdapter mAdapter;

    protected ApiRequest mApiRequest;

    private AbsListView mListView;
    private View mLayoutContent;
    private View mLayoutLoading;
    private View mLblEmpty;

    protected enum ContentDisplayMode {
        LOAD, PARTIAL_LOAD, CONTENT;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar ab = ((ActionBarActivity) this.getActivity()).getSupportActionBar();
        ab.setSubtitle(this.getSubtitleRes());
        ((ActionBarActivity) this.getActivity())
                .setSupportProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.orders, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_provider_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mListView = (AbsListView) view.findViewById(R.id.list_orders);
        this.mLayoutContent = view.findViewById(R.id.layout_orders_content);
        this.mLayoutLoading = view.findViewById(R.id.layout_loading);
        this.mLblEmpty = view.findViewById(R.id.lbl_empty);
        this.mAdapter = new ProviderOrdersAdapter(this.getActivity(), this.mOrders);

        this.mListView.setOnItemClickListener(this);
        this.mListView.setAdapter(this.mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mApiRequest = new ListProviderOrdersRequest(this,
                HomelikePreferences.loadInt(HomelikePreferences.ACCOUNT_ID, -1));
        this.mApiRequest.executeAsync();
        if(this.mOrders.isEmpty()){
            this.displayContentMode(ContentDisplayMode.LOAD);
        } else {
            this.displayContentMode(ContentDisplayMode.PARTIAL_LOAD);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        ((ActionBarActivity) this.getActivity())
                .setSupportProgressBarIndeterminateVisibility(false);
        if(this.mApiRequest != null){
            this.mApiRequest.cancelRequest();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                this.displayContentMode(ContentDisplayMode.LOAD);
                this.mApiRequest.executeAsync();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void displayContentMode(ContentDisplayMode displayMode){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.GONE);
                this.mLayoutLoading.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(false);
                break;
            }
            case PARTIAL_LOAD: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);

                this.mLblEmpty.setVisibility(View.GONE);
                this.mListView.setVisibility(View.VISIBLE);

                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(true);
                break;
            }
            case CONTENT: {
                this.mLayoutLoading.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                ((ActionBarActivity) this.getActivity())
                        .setSupportProgressBarIndeterminateVisibility(false);

                this.mAdapter.updateContent(this.mOrders);

                if(this.mOrders.isEmpty()){
                    this.mLblEmpty.setVisibility(View.VISIBLE);
                    this.mListView.setVisibility(View.GONE);
                } else {
                    this.mLblEmpty.setVisibility(View.GONE);
                    this.mListView.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    }

    @Override
    public void onResponse(List<Pedido> response) {
        this.mOrders = HomelikeUtils.getStatusFilteredOrders(response, this.getStatusFilter());
        this.displayContentMode(ContentDisplayMode.CONTENT);
    }

    protected abstract int getStatusFilter();
    protected abstract int getSubtitleRes();
    protected abstract boolean getShowProviderComments();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pedido order = this.mAdapter.getItem(position);
        ((HomeActivity) this.getActivity()).pushToStack(
                ProviderOrderFragment.getInstance(order, this.getShowProviderComments()),
                ProviderOrderFragment.class.getName());
    }

    public void openOrderWithId(String orderId){
        ((HomeActivity) this.getActivity()).pushToStack(
                ProviderOrderFragment.getInstance(orderId), ProviderOrderFragment.class.getName());
    }

}