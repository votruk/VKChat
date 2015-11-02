package ru.touchin.vkchat.requests;

import android.content.Context;

import com.google.api.client.http.GenericUrl;

import org.zuzuk.settings.Setting;

import ru.touchin.vkchat.Settings;


public class FriendsRequest extends BaseVkRequest {
    private int mCount;

    public FriendsRequest(Context context, int limit, int count) {
        super(context, limit);
        mCount = count;
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        super.setupUrlParameters(url);
        url.put("user_id", Settings.VK_USER_ID.get(context));
        url.put("order", "name");
        url.put("fields", "photo_max");
        if (mCount != 0) {
            url.put("offset", mCount);
        }
    }
}
