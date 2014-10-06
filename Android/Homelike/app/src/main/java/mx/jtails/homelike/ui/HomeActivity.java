package mx.jtails.homelike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import mx.jtails.homelike.HomelikeApplication;
import mx.jtails.homelike.R;
import mx.jtails.homelike.ui.fragment.HomeSectionsFragment;
import mx.jtails.homelike.ui.fragment.SuggestionsFragment;
import mx.jtails.homelike.util.HomeMenuSection;
import mx.jtails.homelike.util.HomelikePreferences;

public class HomeActivity extends ActionBarActivity
    implements HomeSectionsFragment.OnHomeMenuOptionSelectedListener {

    public static final String ARG_HOME_CONTENT_ORD = "arg_home_content";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private int mTempHomeSection = -1;
    private HomeMenuSection mCurrentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.validateUserSignedIn();
        this.loadArguments(this.getIntent().getExtras());

        this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_home);
        this.setupActivity();
    }

    private void loadArguments(Bundle args){
        if(args == null){
            return;
        }

        this.mTempHomeSection = args.getInt(ARG_HOME_CONTENT_ORD, -1);
    }

    private void validateUserSignedIn(){
        if(!HomelikePreferences.containsPreference(HomelikePreferences.ACCOUNT_ID)
                || !HomelikePreferences.containsPreference(HomelikePreferences.DEVICE_ID)){
            this.goToSplash();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mDrawerToggle.syncState();
    }

    private void setupActivity(){
        this.setupActionBar();
        this.setupNavigationDrawer();
    }

    private void setupActionBar(){
        ActionBar ab = this.getSupportActionBar();
        ab.setIcon(R.drawable.ic_ab_navigation);
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(this.mTempHomeSection < 0){
            return;
        }

        this.onHomeMenuOptionSelected(HomeMenuSection.values()[this.mTempHomeSection]);
        this.mTempHomeSection = -1;
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
        inflater.inflate(R.menu.logout, menu);
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

    private void goToSplash(){
        ((HomelikeApplication) this.getApplication()).logout();
        this.startActivity(new Intent(this, SplashActivity.class));
        this.finish();
    }

    @Override
    public void onHomeMenuOptionSelected(HomeMenuSection option) {
        this.mCurrentSection = option;
        if(option != HomeMenuSection.ORDERS){
            //HomelikePreferences.saveInt(HomelikePreferences.CURRENT_HOME_SECTION, option.ordinal());
        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        FragmentManager fm = this.getSupportFragmentManager();

        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fm.beginTransaction()
                .replace(R.id.layout_home_content, option.getFragmentInstance())
                .commit();
        this.supportInvalidateOptionsMenu();
    }

    public void onSuggestionsOptionSelected(){
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        FragmentManager fm = this.getSupportFragmentManager();

        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fm.beginTransaction()
                .replace(R.id.layout_home_content, new SuggestionsFragment())
                .commit();
        this.supportInvalidateOptionsMenu();
    }

    public void addToStack(Fragment stackFragment){
        FragmentManager fm = this.getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.layout_home_content, stackFragment)
                .addToBackStack("")
                .commit();
    }
}
