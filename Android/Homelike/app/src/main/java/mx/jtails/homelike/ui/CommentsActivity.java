package mx.jtails.homelike.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import mx.jtails.homelike.R;
import mx.jtails.homelike.ui.fragment.CommentsToProviderFragment;

/**
 * Created by GrzegorzFeathers on 10/3/14.
 */
public class CommentsActivity extends ActionBarActivity {

    public static final String ARG_PROVIDER_ID = "arg_provider_id";

    private int mProviderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setTitle(R.string.comments);

        if(savedInstanceState == null){
            this.mProviderId = this.getIntent().getIntExtra(ARG_PROVIDER_ID, -1);
        } else {
            this.mProviderId = savedInstanceState.getInt(ARG_PROVIDER_ID, -1);
        }

        this.setContentView(R.layout.activity_comments);
        FragmentManager fm = this.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.layout_content,
                CommentsToProviderFragment.getInstance(this.mProviderId)).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_PROVIDER_ID, this.mProviderId);
        super.onSaveInstanceState(outState);
    }
}
