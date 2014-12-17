package mx.jtails.homelike.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.request.ListOrderCommentsByProviderRequest;
import mx.jtails.homelike.ui.adapter.CommentsAdapter;

/**
 * Created by GrzegorzFeathers on 10/2/14.
 */
public class CommentsToProviderFragment extends ListFragment
    implements ListOrderCommentsByProviderRequest.OnListCommentsResponseHandler {

    private int mProviderId;

    private View mLayoutContent;
    private View mProgressMain;

    private CommentsAdapter mAdapter;

    private enum ContentDisplayMode {
        LOAD, CONTENT;
    }

    public static CommentsToProviderFragment newInstance(int providerId){
        CommentsToProviderFragment fragment = new CommentsToProviderFragment();
        fragment.mProviderId = providerId;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAdapter = new CommentsAdapter(this.getActivity(), new ArrayList<Pedido>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments_to_provider, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mLayoutContent = view.findViewById(R.id.layout_content);
        this.mProgressMain = view.findViewById(R.id.layout_loading);
        this.displayContentMode(ContentDisplayMode.LOAD);
        this.setListAdapter(this.mAdapter);
        new ListOrderCommentsByProviderRequest(this, this.mProviderId).executeAsync();
    }

    private void displayContentMode(ContentDisplayMode displayMode){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.GONE);
                this.mProgressMain.setVisibility(View.VISIBLE);
                break;
            }
            case CONTENT: {
                this.mProgressMain.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    @Override
    public void onListCommentsResponse(List<Pedido> orders) {
        this.mAdapter.updateContent(orders);
        this.getListView().setAdapter(this.mAdapter);
        this.displayContentMode(ContentDisplayMode.CONTENT);
    }
}
