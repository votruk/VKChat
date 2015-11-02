package ru.touchin.vkchat.requests;

import android.content.Context;

import com.google.api.client.http.GenericUrl;

import ru.touchin.vkchat.Settings;


public class FriendsRequest extends BaseVkRequest {
    private String mOffset;

    public FriendsRequest(Context context, int limit) {
        super(context, limit);
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        super.setupUrlParameters(url);
        url.put("user_id", Settings.VK_USER_ID.get(context));
        url.put("order", "name");
        url.put("fields", "photo_max");
        if (mOffset != null) {
            url.put("offset", mOffset);
        }
    }
}
