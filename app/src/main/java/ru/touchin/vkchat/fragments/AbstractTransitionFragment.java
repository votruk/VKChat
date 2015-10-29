package ru.touchin.vkchat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.zuzuk.ui.fragments.AbstractExecutorFragment;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.activities.MainActivity;

public abstract class AbstractTransitionFragment extends AbstractExecutorFragment {

    @Override
    public View onCreateViewInternal(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_loading, container, false);

        final ViewGroup contentContainerView = (ViewGroup) view.findViewById(R.id.loadingContentContainer);
        final View contentView = createContentView(inflater, contentContainerView, savedInstanceState);
        contentContainerView.addView(contentView);

        return view;
    }

    public boolean isHomeButtonVisible() {
        return true;
    }

    @Override
    public void configureActionBar() {
        super.configureActionBar();
        getActivity().findViewById(R.id.toolbar).setVisibility(isActionBarVisible() ? View.VISIBLE : View.GONE);
    }

    protected boolean isActionBarVisible() {
        return true;
    }

    protected abstract View createContentView(final LayoutInflater inflater, final ViewGroup container,
                                              final Bundle savedInstanceState);

    protected MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public Fragment setFirstFragment(final Class<?> fragmentClass) {
        return setFirstFragment(fragmentClass, null);
    }

    public Fragment setFirstFragment(final Class<?> fragmentClass, final Bundle args) {
        return getMainActivity().setFirstFragment(fragmentClass, args);
    }

    public Fragment setFragment(final Class fragmentClass) {
        return setFragment(fragmentClass, null);
    }

    public Fragment setFragment(final Class fragmentClass, final Bundle args) {
        return getMainActivity().setFragment(fragmentClass, args);
    }

    public Fragment pushFragment(final Class fragmentClass) {
        return pushFragment(fragmentClass, null);
    }

    public Fragment pushFragment(final Class fragmentClass, final Bundle args) {
        return getMainActivity().pushFragment(fragmentClass, args);
    }

    public Fragment pushFragmentForResult(final Class fragmentClass) {
        return pushFragmentForResult(fragmentClass, null);
    }

    public Fragment pushFragmentForResult(final Class fragmentClass, final Bundle args) {
        final Fragment pushedFragment = getMainActivity().pushFragment(fragmentClass, args);
        pushedFragment.setTargetFragment(this, 1); // TODO request code ???
        return pushedFragment;
    }

    public Fragment replaceCurrFragment(final Class fragmentClass) {
        return replaceCurrFragment(fragmentClass, null);
    }

    public Fragment replaceCurrFragment(final Class fragmentClass, final Bundle args) {
        final FragmentManager fragmentManager = getMainActivity().getSupportFragmentManager();

        if (0 < fragmentManager.getBackStackEntryCount()) {
            popBackStack();
            return setFragment(fragmentClass, args);
        } else {
            return setFirstFragment(fragmentClass, args);
        }
    }

    public void popBackStack() {
        getMainActivity().getSupportFragmentManager().popBackStack();
    }

    public void popBackStackWithInvalidate() {
        if (getTargetFragment() instanceof AbstractRemoteLoadingFragment) {
            ((AbstractRemoteLoadingFragment) getTargetFragment()).setNeedInvalidating();
        }
        getMainActivity().getSupportFragmentManager().popBackStack();
    }
}
