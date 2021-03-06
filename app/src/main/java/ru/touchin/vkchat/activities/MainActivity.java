package ru.touchin.vkchat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.ui.UiUtils;
import org.zuzuk.ui.activities.AbstractExecutorActivity;
import org.zuzuk.utils.log.Lc;
import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.Date;
import java.util.List;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.fragments.FriendsListFragment;
import ru.touchin.vkchat.fragments.VKAuthFragment;
import ru.touchin.vkchat.fragments.base.AbstractLocalLoadedFragment;
import ru.touchin.vkchat.providers.RequestFailListener;

public class MainActivity extends AbstractExecutorActivity implements RequestFailListener {


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
			homeButtonClicked();
			return true;


		}
		return false;
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
		final boolean enabled = isCurrentFragmentTop() && homeButtonVisible;
		if (currentFragment != null) {
			currentFragment.configureActionBar();
		}

		if (!enabled) {
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
		if (StringUtils.isNotBlank(Settings.VK_ACCESS_TOKEN.get(VKChatApp.getInstance()))) {
			long now = new Date().getTime();
			if (now > Settings.VK_TOKEN_EXPIRES_IN.get(VKChatApp.getInstance())) {
				setFirstFragment(VKAuthFragment.class);
			} else {
				setFirstFragment(FriendsListFragment.class);
			}
		} else {
			setFirstFragment(VKAuthFragment.class);
		}
	}


	protected void setupActionBar() {
		setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		actionBar.setElevation(4.0f);
	}

	@Override
	public void onRequestFailure(List<Exception> exceptionList) {
		Exception error = exceptionList.get(0);

		if (error.getCause() instanceof Exception) {
			error = (Exception) error.getCause();
		}
		Lc.e(error.getMessage());

		String msg = error.getMessage();

		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}


}