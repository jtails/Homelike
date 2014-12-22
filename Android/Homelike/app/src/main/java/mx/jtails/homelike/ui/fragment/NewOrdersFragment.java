package mx.jtails.homelike.ui.fragment;

import mx.jtails.homelike.HomelikeApplication;
import mx.jtails.homelike.util.HomeProviderMenuSection;

/**
 * Created by GrzegorzFeathers on 11/25/14.
 */
public class NewOrdersFragment extends ProviderOrdersFragment {

    private static final int STATUS_FILTER = 0;

    @Override
    public void onResume() {
        super.onResume();
        ((HomelikeApplication) this.getActivity().getApplication()).setNewOrderListener(
                new HomelikeApplication.OnNewOrderReceivedListener() {
                    @Override
                    public void onNewOrderReceived() {
                        displayContentMode(ContentDisplayMode.LOAD);
                        mApiRequest.executeAsync();
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        ((HomelikeApplication) this.getActivity().getApplication()).removeNewOrderListener();
    }

    @Override
    protected int getStatusFilter() {
        return STATUS_FILTER;
    }

    @Override
    protected int getSubtitleRes() {
        return HomeProviderMenuSection.NEW_ORDERS.getSubtitleRes();
    }

    @Override
    protected boolean getShowProviderComments() {
        return false;
    }
}
