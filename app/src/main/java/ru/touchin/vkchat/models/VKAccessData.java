package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class VKAccessData extends ObjectFromJson {

    @Key("access_token")
    private String mAccessToken;

    @Key("user_id")
    private String mUserId;

    @Key("expires_in")
    private String mExpiresIn;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        mExpiresIn = expiresIn;
    }

    public VKAccessData(String accessToken, String userId) {
        mAccessToken = accessToken;
        mUserId = userId;
    }
}
