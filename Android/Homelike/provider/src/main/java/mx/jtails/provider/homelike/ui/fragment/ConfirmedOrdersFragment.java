package mx.jtails.provider.homelike.ui.fragment;

import mx.jtails.provider.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public class ConfirmedOrdersFragment extends OrdersFragment {

    private static final int STATUS_FILTER = 1;

    @Override
    protected int getStatusFilter() {
        return STATUS_FILTER;
    }

    @Override
    protected int getSubtitleRes() {
        return HomeMenuSection.CONFIRMED_ORDERS.getSubtitleRes();
    }
}
