package mx.jtails.homelike.ui.fragment;

import mx.jtails.homelike.util.HomeProviderMenuSection;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public class ConfirmedOrdersFragment extends ProviderOrdersFragment {

    private static final int STATUS_FILTER = 1;

    @Override
    protected int getStatusFilter() {
        return STATUS_FILTER;
    }

    @Override
    protected int getSubtitleRes() {
        return HomeProviderMenuSection.CONFIRMED_ORDERS.getSubtitleRes();
    }

    @Override
    protected boolean getShowProviderComments() {
        return true;
    }
}
