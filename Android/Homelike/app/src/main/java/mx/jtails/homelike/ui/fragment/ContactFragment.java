package mx.jtails.homelike.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.jtails.homelike.R;
import mx.jtails.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 10/21/14.
 */
public class ContactFragment extends Fragment {

    private View rootView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBarActivity activity = (ActionBarActivity) this.getActivity();
        activity.setSupportProgressBarIndeterminateVisibility(false);
        ActionBar ab = activity.getSupportActionBar();
        ab.setSubtitle(HomeMenuSection.CONTACT.getSubtitleRes());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_contact, container, false);



        return this.rootView;
    }
}
