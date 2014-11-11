package mx.jtails.homelike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import mx.jtails.homelike.HomelikeApplication;
import mx.jtails.homelike.R;
import mx.jtails.homelike.ui.fragment.HomeSectionsFragment;
import mx.jtails.homelike.util.HomeMenuSection;
import mx.jtails.homelike.util.HomelikePreferences;

public class HomeActivity extends ActionBarActivity
    implements HomeSectionsFragment.OnHomeMenuOptionSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    public static final HomeMenuSection DEFAULT_HOME_CONTENT = HomeMenuSection.SERVICES;
    public static final int DEFAULT_FRAGMENT_TRANSITION = FragmentTransaction.TRANSIT_FRAGMENT_FADE;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.validateUserSignedIn();

        this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_home);

        this.setupActivity();
        this.clearStack();
        this.pushToStack(this.DEFAULT_HOME_CONTENT.getFragmentClass(), null, -1, false);
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
        this.mDrawerLayout.closeDrawer(GravityCompat.START);

        if(option.equals(DEFAULT_HOME_CONTENT)){
            this.clearStack();
        } else {
            this.replaceStack(option.getFragmentClass(), null);
        }

        this.supportInvalidateOptionsMenu();
    }

    public void clearStack(){
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void replaceStack(Class<? extends Fragment> newContentClass, Bundle extras){
        this.clearStack();
        this.pushToStack(newContentClass, extras);
    }

    public void pushToStack(Fragment fragment, String tag){
        this.pushToStack(fragment, tag, DEFAULT_FRAGMENT_TRANSITION);
    }

    public void pushToStack(Fragment fragment, String tag, int transition){
        this.pushToStack(fragment, tag, transition, true);
    }

    private void pushToStack(Fragment fragment, String tag, int transition, boolean addToBackStack){
        FragmentManager fm = this.getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layout_home_content, fragment, tag);

        if(transition > 0){
            transaction.setTransition(transition);
        }
        if(addToBackStack){
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    private void pushToStack(Class<? extends Fragment> fragmentClass, Bundle extras,
                             int transition, boolean addToBackStack){
        Fragment fragment = this.getFragmentInstance(fragmentClass, extras);

        if(fragment == null){
            Log.d(TAG, "Unable to create the fragment instance");
            return;
        }

        this.pushToStack(fragment, fragmentClass.getName(), transition, addToBackStack);
    }

    public void pushToStack(Class<? extends Fragment> fragmentClass, Bundle extras, int transition){
        this.pushToStack(fragmentClass, extras, transition, true);
    }

    public void pushToStack(Class<? extends Fragment> fragmentClass, Bundle extras) {
        this.pushToStack(fragmentClass, extras, DEFAULT_FRAGMENT_TRANSITION);
    }

    public void singlePop(){
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack();
    }

    private Fragment getFragmentInstance(Class<? extends Fragment> fragmentClass, Bundle extras){
        Fragment fragment = null;

        try {
            fragment = fragmentClass.newInstance();
            if(extras != null){
                fragment.setArguments(extras);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public void onBackPressed() {
        if(this.mDrawerLayout.isDrawerOpen(this.findViewById(R.id.drawer_menu))){
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
