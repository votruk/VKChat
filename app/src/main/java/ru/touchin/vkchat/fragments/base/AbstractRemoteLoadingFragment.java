package ru.touchin.vkchat.fragments.base;

import android.os.Bundle;
import android.view.View;


import org.zuzuk.tasks.aggregationtask.AggregationTask;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStage;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.ui.UiUtils;

import ru.touchin.vkchat.R;

public abstract class AbstractRemoteLoadingFragment extends AbstractLocalLoadedFragment implements AggregationTask {

    private boolean fragmentDataLoaded;

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingRefreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View loadingRefreshButtonView) {
                reload();
            }
        });
    }

    @Override
    protected int getContentVisibilityOnViewCreated() {
        return fragmentDataLoaded ? View.VISIBLE : View.INVISIBLE;
    }

    protected void reload() {
        fragmentDataLoaded = false;
        loadFragment();
    }

    private void loadFragment() {
        executeAggregationTask(AbstractRemoteLoadingFragment.this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFragment();
    }

    @Override
    public boolean isLoadingNeeded(final AggregationTaskStageState currentTaskStageState) {
        return true;
    }

    @Override
    public boolean isLoaded(final AggregationTaskStageState currentTaskStageState) {
        return !currentTaskStageState.hasExceptions() && (fragmentDataLoaded
                || currentTaskStageState.getTaskStage() != AggregationTaskStage.PRE_LOADING);
    }

    @Override
    public void onLoadingStarted(final AggregationTaskStageState currentTaskStageState) {
        if (currentTaskStageState.getTaskStage() == AggregationTaskStage.REAL_LOADING) {
            loadingRefreshButton.setVisibility(View.GONE);
            final boolean loadingWithCacheMiss = !currentTaskStageState.getPreviousStageState().isLoaded();
            loadingProgressBar.setVisibility(loadingWithCacheMiss ? View.VISIBLE : View.GONE);
            loadingContentContainer.setVisibility(loadingWithCacheMiss ? View.INVISIBLE : View.VISIBLE);
            fragmentDataLoaded = loadingWithCacheMiss ? false : true;
        }
    }

    @Override
    public void onLoaded(final AggregationTaskStageState currentTaskStageState) {
        if (!canAccess()) {
            popBackStack();
            return;
        }
        fragmentDataLoaded = true;
        loadingProgressBar.setVisibility(View.GONE);
        loadingContentContainer.setVisibility(View.VISIBLE);
        loadingRefreshButton.setVisibility(View.GONE);
        onFragmentDataLoaded(currentTaskStageState);
    }

    @Override
    public void onFailed(final AggregationTaskStageState currentTaskStageState) {
        if (currentTaskStageState.getTaskStage() == AggregationTaskStage.REAL_LOADING) {
            final boolean noDataToShow = !currentTaskStageState.isLoaded()
                    && !currentTaskStageState.getPreviousStageState().isLoaded()
                    && !currentTaskStageState.getPreviousStageState().getPreviousStageState().isLoaded();
            loadingProgressBar.setVisibility(View.GONE);
            loadingContentContainer.setVisibility(noDataToShow ? View.INVISIBLE : View.VISIBLE);
            loadingRefreshButton.setVisibility(noDataToShow ? View.VISIBLE : View.GONE);
            fragmentDataLoaded = noDataToShow ? false : true;
            handleErrors(currentTaskStageState);
            requestFocus();
            UiUtils.hideSoftInput(getMainActivity());
        }
    }

    @Override
    public void load(final RequestAndTaskExecutor executor, final AggregationTaskStageState currentTaskStageState) {
        loadFragmentData(executor, currentTaskStageState);
    }

    public void requestFocus() {
        if (currentFocusView != null) {
            findViewById(R.id.topContainer).requestFocus();
            currentFocusView = null;
        }
    }

    public void handleErrors(final AggregationTaskStageState currentTaskStageState) {
        if (currentTaskStageState.hasExceptions()) {
            onRequestFailure(currentTaskStageState.getExceptions());
        }
    }

    public boolean isFragmentDataLoaded() {
        return fragmentDataLoaded;
    }

    public void setNeedInvalidating() {
        this.fragmentDataLoaded = false;
        if (getTargetFragment() instanceof AbstractRemoteLoadingFragment) {
            ((AbstractRemoteLoadingFragment) getTargetFragment()).setNeedInvalidating();
        }
    }

    protected abstract void loadFragmentData(final RequestAndTaskExecutor executor, final AggregationTaskStageState currentTaskStageState);

    protected abstract void onFragmentDataLoaded(final AggregationTaskStageState currentTaskStageState);

    protected boolean canAccess() {
        return true;
    }

    @Override
    protected void onDestroyRenewable() {
        super.onDestroyRenewable();
        fragmentDataLoaded = false;
    }

}
