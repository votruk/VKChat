package ru.touchin.vkchat.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



import org.zuzuk.events.BroadcastEvents;
import org.zuzuk.events.EventAnnotation;
import org.zuzuk.ui.UiUtils;
import org.zuzuk.utils.serialization.json.ObjectFromJson;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.fragments.AbstractLocalLoadedFragment;
import ru.touchin.vkchat.fragments.VKAuthFragment;

@BroadcastEvents({@EventAnnotation(value = Settings.RESPONDENT_PROFILE_NAME)})
public class MainActivity extends BaseActivity {


    private AbstractLocalLoadedFragment currentFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActionBar();
        if (savedInstanceState == null) {
            setFirstFragment();
        }
    }

    @Override
    public void onBackStackChanged() {
        super.onBackStackChanged();
        updateActionBarState();
    }

    @Override
    public void onFragmentStarted(final Fragment fragment) {
        super.onFragmentStarted(fragment);
        UiUtils.hideSoftInput(this);
        currentFragment = (AbstractLocalLoadedFragment) fragment;
        updateActionBarState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentFragment = null;
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            UiUtils.hideSoftInput(MainActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        updateActionBarState();
    }

    @Override
    public void onEvent(final Context context, @NonNull final String eventName, final Intent intent) {
        super.onEvent(context, eventName, intent);

    }

    public int getFragmentContainerId() {
        return R.id.mainFragmentContainer;
    }

    private void updateActionBarState() {
        final boolean homeButtonVisible = currentFragment == null || currentFragment.isHomeButtonVisible();
        final boolean drawerIndicatorEnabled = isCurrentFragmentTop() && homeButtonVisible;
        if (currentFragment != null) {
            currentFragment.configureActionBar();
        }

        if (!drawerIndicatorEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (!homeButtonVisible) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    public AbstractLocalLoadedFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setFirstFragment() {
        setFirstFragment(Settings.LOGIN_DATA.get(this) == null ? VKAuthFragment.class : TaskListFragment.class);
    }


    protected void setupActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}