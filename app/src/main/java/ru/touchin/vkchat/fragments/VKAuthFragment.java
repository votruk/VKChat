package ru.touchin.vkchat.fragments;

import android.net.Uri;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import ru.touchin.vkchat.BuildConfig;
import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKHelper;
import ru.touchin.vkchat.models.VKAccessTokenSetting;

public class VKAuthFragment extends AbstractWebViewFragment {

    private static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";

    @Override
    protected boolean isActionBarVisible() {
        return false;
    }

    @Override
    protected void loadFragmentDataInner(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        String urlToLoad = "https://oauth.vk.com/authorize?" +
                "client_id=" + BuildConfig.VK_APP_ID +
                "&scope=" + VKHelper.SCOPES +
                "&display=mobile" +
                "&redirect_uri=" + REDIRECT_URL +
                "&v=" + VKHelper.API_VERSION +
                "&response_type=token";
        loadUrl(executor, currentTaskStageState, urlToLoad, null);
    }

    @Override
    protected boolean processUrl(String url) {
        if (url.startsWith(REDIRECT_URL)) {
            Uri uri = Uri.parse(url.replaceFirst("#", "?"));
            boolean isAnyFieldWrong = false;
            String accessToken = uri.getQueryParameter("access_token");
            String userId = uri.getQueryParameter("user_id");
            String expiresIn = uri.getQueryParameter("expires_in");
            if (StringUtils.isNotBlank(accessToken)) {
                Settings.VK_ACCESS_TOKEN.set(getActivity(), accessToken);
            } else {
                isAnyFieldWrong = true;
            }

            if (StringUtils.isNotBlank(userId)) {
                Settings.VK_USER_ID.set(getActivity(), userId);
            } else {
                isAnyFieldWrong = true;
            }

//            if (StringUtils.isNotBlank(expiresIn)) {
//                Settings.VK_ACCESS_TOKEN.set(getActivity(), expiresIn);
//            } else {
//                isAnyFieldWrong = true;
//            }


            if (isAnyFieldWrong) {
                String errorDescription = uri.getQueryParameter("error_description");
                if (StringUtils.isNotBlank(errorDescription)) {
                    Toast.makeText(getActivity(), errorDescription, Toast.LENGTH_LONG).show();
                }
            }

            popBackStack();
            return true;
        }
        return false;
    }

}