package mx.jtails.provider.homelike.ui.fragment;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public class ConfirmedOrdersFragment extends OrdersFragment {

    private static final int STATUS_FILTER = 1;

    @Override
    protected int getStatusFilter() {
        return STATUS_FILTER;
    }
}
