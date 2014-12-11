package mx.jtails.homelike.ui.fragment;

import mx.jtails.homelike.util.HomeProviderMenuSection;

/**
 * Created by GrzegorzFeathers on 11/25/14.
 */
public class NewOrdersFragment extends ProviderOrdersFragment {

    private static final int STATUS_FILTER = 0;

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
