package mx.jtails.provider.homelike.ui.fragment;

/**
 * Created by GrzegorzFeathers on 11/25/14.
 */
public class NewOrdersFragment extends OrdersFragment {

    private static final int STATUS_FILTER = 0;

    @Override
    protected int getStatusFilter() {
        return STATUS_FILTER;
    }
}
