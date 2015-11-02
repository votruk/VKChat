package ru.touchin.vkchat.fragments.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.octo.android.robospice.persistence.exception.CacheLoadingException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStage;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.tasks.base.AbstractNoRetryTask;
import org.zuzuk.ui.UiUtils;

import java.util.concurrent.CountDownLatch;

import javax.annotation.Nullable;

import ru.touchin.vkchat.R;

public abstract class AbstractWebViewFragment extends AbstractRemoteLoadingFragment {

    private WebView webView;

    // don't save state in onSaveInstanceState because state restoring is not correct on some devices like htc android 4.1
    private Bundle webViewState;

    private boolean isError;

    private String url;

    private CountDownLatch countDownLatch;

    @Override
    protected View createContentView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_web_view, container, false);
    }

    @Override
    protected boolean shouldRestoreFocus() {
        return false;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = findViewById(R.id.webView);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(createWebViewClient());

        if (webViewState != null) {
            webView.restoreState(webViewState);
        }
    }

    protected boolean processUrl(final String url) {
        return false;
    }

    protected void loadUrl(final RequestAndTaskExecutor executor, final AggregationTaskStageState currentTaskStageState,
                           final String url, final RequestListener<Void> requestListener) {
        final boolean needLoading;
        if (!StringUtils.equals(this.url, url) && currentTaskStageState.getTaskStage() == AggregationTaskStage.REAL_LOADING) {
            this.url = url;
            needLoading = true;
            countDownLatch = new CountDownLatch(1);
            webView.loadUrl(url);
        } else {
            needLoading = false;
        }
        executor.executeTask(new AbstractNoRetryTask<Void>(Void.class) {
            @Override
            public Void execute() throws Exception {
                if (currentTaskStageState.getTaskStage() != AggregationTaskStage.REAL_LOADING) {
                    throw new CacheLoadingException("Web view loading can be only real");
                }
                if (needLoading) {
                    countDownLatch.await();
                }
                if (isError) {
                    throw new Exception();
                }
                return null;
            }
        }, requestListener);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webView.setWebViewClient(null);
        webView = null;
    }

    @Override
    protected void reload() {
        super.reload();
        webView.clearHistory();
        isError = false;
        url = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isError && shouldSaveWebViewState()) {
            webViewState = new Bundle();
            webView.saveState(webViewState);
        } else {
            webViewState = null;
        }
    }

    @Override
    protected void loadFragmentData(final RequestAndTaskExecutor executor, final AggregationTaskStageState currentTaskStageState) {
        if (webViewState == null) {
            loadFragmentDataInner(executor, currentTaskStageState);
        }
    }

    protected abstract void loadFragmentDataInner(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState);

    @Override
    protected void onFragmentDataLoaded(final AggregationTaskStageState currentTaskStageState) {
        // do nothing
    }

    protected boolean shouldSaveWebViewState() {
        return true;
    }

    @Override
    public boolean onBackPressed() {
        if (!isError && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onBackPressed();
    }

    private WebViewClient createWebViewClient() {
        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                if (!processUrl(url)) {
                    webView.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (isError) {
                    return;
                }
                isError = false;
                loadingProgressBar.setVisibility(View.VISIBLE);
                UiUtils.hideSoftInput(getMainActivity());
            }

            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);
                pageFinished();
            }


            @Override
            public void onReceivedError(final WebView view, final WebResourceRequest request, final WebResourceError error) {
                super.onReceivedError(view, request, error);
                isError = true;
            }
        };
    }

    private void pageFinished() {
        loadingProgressBar.setVisibility(View.GONE);
        if (isFragmentDataLoaded() && isError) {
            loadingContentContainer.setVisibility(View.INVISIBLE);
            loadingRefreshButton.setVisibility(View.VISIBLE);
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
        if (isError) {
            webViewState = null;
        }
    }

}
