package ru.touchin.vkchat.fragments;

import android.net.Uri;
import android.widget.Toast;

import com.riotrus.guilds.BuildConfig;
import com.riotrus.guilds.Settings;
import com.riotrus.guilds.api.requests.vk.base.BaseVKRequest;
import com.riotrus.guilds.fragments.base.BaseWebViewFragment;
import com.riotrus.guilds.utils.vk.VKHelper;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

public class VKAuthFragment extends BaseWebViewFragment {

    private static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";

    @Override
    protected boolean isActionBarVisible() {
        return false;
    }

    @Override
    protected void loadFragmentDataInner(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        String urlToLoad = "https://oauth.vk.com/authorize?" +
                "client_id=" + BuildConfig.VK_APP_ID +
                "&scope=" + VKHelper.AUTH_SCOPE +
                "&display=mobile" +
                "&redirect_uri=" + REDIRECT_URL +
                "&v=" + BaseVKRequest.API_VERSION +
                "&response_type=token";
        loadUrl(executor, currentTaskStageState, urlToLoad, null);
    }

    @Override
    protected boolean processUrl(String url) {
        if (url.startsWith(REDIRECT_URL)) {
            Uri uri = Uri.parse(url.replaceFirst("#", "?"));
            String accessToken = uri.getQueryParameter("access_token");
            if (StringUtils.isNotBlank(accessToken)) {
                Settings.VKAccessTokenSetting.set(getActivity(), accessToken);
            } else {
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