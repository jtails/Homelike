package mx.jtails.homelike.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.jtails.homelike.R;
import mx.jtails.homelike.util.HomeMenuOption;

public class HomeMenuFragment extends Fragment {

    public static final HomeMenuOption DEFAULT_HOME_CONTENT = HomeMenuOption.SERVICES;

    private OnHomeMenuOptionSelectedListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHomeMenuOptionSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_menu, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnHomeMenuOptionSelectedListener {
        public void onHomeMenuOptionSelected(HomeMenuOption option);
    }

}
