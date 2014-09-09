package mx.jtails.homelike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import mx.jtails.homelike.HomelikeApplication;
import mx.jtails.homelike.R;
import mx.jtails.homelike.ui.fragment.HomeMenuFragment;
import mx.jtails.homelike.util.HomeMenuOption;
import mx.jtails.homelike.util.HomelikePreferences;

public class HomeActivity extends ActionBarActivity
        implements HomeMenuFragment.OnHomeMenuOptionSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!HomelikePreferences.containsPreference(HomelikePreferences.SESSION_COOKIE)){
            this.goToSplash();
        }

        this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_home);
        this.setupActivity();
    }

    private void goToSplash(){
        ((HomelikeApplication) this.getApplication()).logout();
        this.startActivity(new Intent(this, SplashActivity.class));
        this.finish();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mDrawerToggle.syncState();
    }

    private void setupActivity(){
        this.setupActionBar();
        this.setupNavigationDrawer();

        this.onHomeMenuOptionSelected(HomeMenuFragment.DEFAULT_HOME_CONTENT);
    }

    private void setupActionBar(){
        ActionBar ab = this.getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

    private void setupNavigationDrawer(){
        this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.layout_home_drawer);
        this.mDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout,
                R.drawable.ic_drawer, R.string.app_name, R.string.app_name);
        this.mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.mDrawerToggle.onOptionsItemSelected(item);
                return true;
            case R.id.action_logout:
                this.goToSplash();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onHomeMenuOptionSelected(HomeMenuOption option) {
        FragmentManager fm = this.getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.layout_home_content, option.getFragmentInstance())
                .commit();
        this.supportInvalidateOptionsMenu();
    }
}
