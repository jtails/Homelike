package mx.jtails.provider.homelike.ui.fragment;

import mx.jtails.provider.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 11/25/14.
 */
public class NewOrdersFragment extends OrdersFragment {

    private static final int STATUS_FILTER = 0;

    @Override
    protected int getStatusFilter() {
        return STATUS_FILTER;
    }

    @Override
    protected int getSubtitleRes() {
        return HomeMenuSection.NEW_ORDERS.getSubtitleRes();
    }
}
