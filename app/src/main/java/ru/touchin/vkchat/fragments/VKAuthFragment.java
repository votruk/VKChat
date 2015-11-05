package ru.touchin.vkchat.fragments;

import android.net.Uri;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.util.Date;

import ru.touchin.vkchat.BuildConfig;
import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.activities.MainActivity;
import ru.touchin.vkchat.fragments.base.AbstractWebViewFragment;

public class VKAuthFragment extends AbstractWebViewFragment {
	public static final String SCOPES = "friends,messages";
	public static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";
	public static final String API_VERSION = "5.37";
    public static final int FIVE_MINUTES = 5 * 60 * 1000;

    @Override
    protected boolean isActionBarVisible() {
        return false;
    }

    @Override
    protected void loadFragmentDataInner(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        String urlToLoad = "https://oauth.vk.com/authorize?" +
                "client_id=" + BuildConfig.VK_APP_ID +
                "&scope=" + SCOPES +
                "&display=mobile" +
                "&redirect_uri=" + REDIRECT_URL +
                "&v=" + API_VERSION +
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
                Settings.VK_ACCESS_TOKEN.set(VKChatApp.getInstance(), accessToken);
            } else {
                isAnyFieldWrong = true;
            }

            if (StringUtils.isNotBlank(userId)) {
                Settings.VK_USER_ID.set(VKChatApp.getInstance(), userId);
            } else {
                isAnyFieldWrong = true;
            }

            long expiresInMilliseconds = Long.parseLong(expiresIn) * 1000;
            long expirationDate = (new Date().getTime() + expiresInMilliseconds - FIVE_MINUTES);
            if (StringUtils.isNotBlank(userId)) {
                Settings.VK_TOKEN_EXPIRES_IN.set(VKChatApp.getInstance(), expirationDate);
            } else {
                isAnyFieldWrong = true;
            }

            if (isAnyFieldWrong) {
                String errorDescription = uri.getQueryParameter("error_description");
                if (StringUtils.isNotBlank(errorDescription)) {
                    Toast.makeText(getActivity(), errorDescription, Toast.LENGTH_LONG).show();
                }
            }

            setFirstFragment(FriendsListFragment.class);
            return true;
        }
        return false;
    }

}