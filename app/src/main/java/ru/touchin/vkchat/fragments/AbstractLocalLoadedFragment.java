package ru.touchin.vkchat.fragments;

import android.animation.LayoutTransition;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;


import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.realloading.RealLoadingAggregationTaskListener;
import org.zuzuk.ui.UiUtils;

import java.util.List;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.providers.RequestFailListener;

public abstract class AbstractLocalLoadedFragment extends AbstractTransitionFragment implements RequestFailListener {
    private static final String CURRENT_FOCUS_ID_STATE = "CURRENT_FOCUS_ID_STATE";

    protected View loadingRefreshButton;
    protected View loadingProgressBar;
    protected View loadingContentContainer;

    protected View currentFocusView;

    protected final RealLoadingAggregationTaskListener listenerWithProgressBar = new RealLoadingAggregationTaskListener() {
        @Override
        public void onRealLoadingStarted(final AggregationTaskStageState currentTaskStageState) {
            findViewById(R.id.loadingProgressBar).setVisibility(View.VISIBLE);
        }

        @Override
        public void onRealLoaded(final AggregationTaskStageState currentTaskStageState) {
            findViewById(R.id.loadingProgressBar).setVisibility(View.GONE);
        }

        @Override
        public void onRealFailed(final AggregationTaskStageState currentTaskStageState) {
            findViewById(R.id.loadingProgressBar).setVisibility(View.GONE);
            onRequestFailure(currentTaskStageState.getExceptions());
        }
    };

//    protected final RealLoadingAggregationTaskListener listenerBackground = new RealLoadingAggregationTaskListener() {
//        @Override
//        public void onRealLoadingStarted(final AggregationTaskStageState currentTaskStageState) {
//            // Do nothing.
//        }
//
//        @Override
//        public void onRealLoaded(final AggregationTaskStageState currentTaskStageState) {
//            // Do nothing.
//        }
//
//        @Override
//        public void onRealFailed(final AggregationTaskStageState currentTaskStageState) {
//            onRequestFailure(currentTaskStageState.getExceptions());
//        }
//    };

    @Override
    public CharSequence getTitle() {
        final int titleResourceId = getTitleResourceId();
        return getString(titleResourceId > 0 ? titleResourceId : R.string.title);
    }

    public int getTitleResourceId() {
        return 0;
    }

//    protected CharSequence wrapActionBarText(String text) {
//        Typeface actionBarTypeface = Typefaces.getByName(getActivity(), "BeaufortforLOL-Regular");
//        return UIUtils.wrapTextWithTypeface(getActivity(), text, actionBarTypeface);
//    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.topContainer);
        final LayoutTransition layoutTransition = frameLayout.getLayoutTransition();
        frameLayout.setLayoutTransition(null);
        loadingRefreshButton = view.findViewById(R.id.loadingRefreshButton);
        loadingRefreshButton.setVisibility(View.GONE);
        loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        loadingProgressBar.setVisibility(View.GONE);
        loadingContentContainer = view.findViewById(R.id.loadingContentContainer);
        loadingContentContainer.setVisibility(getContentVisibilityOnViewCreated());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { // strange animations blinking
            frameLayout.setLayoutTransition(layoutTransition);
        }

        if (shouldRestoreFocus() && savedInstanceState != null) {
            final int currentFocusId = savedInstanceState.getInt(CURRENT_FOCUS_ID_STATE, View.NO_ID);
            if (currentFocusId != View.NO_ID) {
                currentFocusView = findViewById(currentFocusId);
                showSoftInputIfNeeded();
            }
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (shouldRestoreFocus() && (currentFocusView != null && currentFocusView.getId() != R.id.topContainer
                && currentFocusView instanceof EditText)) {
            savedInstanceState.putInt(CURRENT_FOCUS_ID_STATE, currentFocusView.getId());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showSoftInputIfNeeded();
    }

    @Override
    public void onPause() {
        super.onPause();
        currentFocusView = getMainActivity().getCurrentFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadingRefreshButton = null;
        loadingProgressBar = null;
        loadingContentContainer = null;
        currentFocusView = null;
    }

    @Override
    public void onRequestFailure(final List<Exception> exceptionList) {
        getMainActivity().onRequestFailure(exceptionList);
    }

    protected RealLoadingAggregationTaskListener getListenerWithProgressBar() {
        return listenerWithProgressBar;
    }

    protected int getContentVisibilityOnViewCreated() {
        return View.VISIBLE;
    }

    public boolean navigationDrawerEnabled() {
        return true;
    }

    protected boolean shouldRestoreFocus() {
        return true;
    }

    private void showSoftInputIfNeeded() {
        if (currentFocusView != null && currentFocusView.getId() != R.id.topContainer
                && currentFocusView instanceof EditText) {
            postRequestFocus();
        }
    }

    protected void postRequestFocus() {
        getPostHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isResumed()
                        && currentFocusView != null) {
                    currentFocusView.requestFocus();
                    UiUtils.showSoftInput(getMainActivity(), currentFocusView);
                }
            }
        }, 500);
    }
}
