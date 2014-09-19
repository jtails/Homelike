package mx.jtails.homelike.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class OrdersFragment extends Fragment {
    //implements AdapterView.OnItemClickListener,
      //  ListOrdersRequest.ListOrdersResponseHandler {
/*
    private OrdersAdapter mAdapter;
    private HomelikeApiRequest mApiRequest;

    private AbsListView mListView;
    private View mLayoutContent;
    private ProgressBar mProgressMain;

    private List<Pedido> mOrders = new ArrayList<Pedido>();

    private enum ContentDisplayMode {
        LOAD, PARTIAL_LOAD, CONTENT;
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
        return inflater.inflate(R.layout.fragment_services, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mListView = (AbsListView) view.findViewById(R.id.list_services);
        this.mLayoutContent = view.findViewById(R.id.layout_services_content);
        this.mProgressMain = (ProgressBar) view.findViewById(R.id.progress_main);
        this.mAdapter = new OrdersAdapter(this.getActivity(), this.mOrders);

        this.mListView.setOnItemClickListener(this);
        this.mListView.setAdapter(this.mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //this.mOrders = HomelikeDBManager.getDBManager().loadServices();
        this.loadServices(this.mOrders.isEmpty());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(HomelikePreferences.containsPreference(HomelikePreferences.DEFAULT_ADDRESS)) {
            // TODO display dialog with default address
        }
        Pedido order = this.mAdapter.getItem(position);
        Bundle args = new Bundle();
        //args.putInt(MyAddressesActivity.ARG_REQUESTED_SERVICE_ID, service.getIdServicio());

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

                this.mAdapter.updateContent(this.mOrders);
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
    public void onListOrdersResponse(List <Pedido> orders) {
        HomelikeDBManager dbManager = HomelikeDBManager.getDBManager();

        int affectedRows = dbManager.saveServices(services);
        if(affectedRows > this.mOrders.size()){
            //this.mOrders = dbManager.loadServices();
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
    */
}
