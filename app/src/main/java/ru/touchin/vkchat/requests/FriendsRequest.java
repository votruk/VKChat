package ru.touchin.vkchat.requests;

import com.google.api.client.http.GenericUrl;

import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;


public class FriendsRequest extends BaseVkRequest {
    private int mOffset;

    public FriendsRequest(int limit, int offset) {
        super(limit);
        mOffset = offset;
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        super.setupUrlParameters(url);
        url.put("user_id", Settings.VK_USER_ID.get(VKChatApp.getInstance()));
        url.put("order", "name");
        url.put("fields", "photo_max");
        if (mOffset != 0) {
            url.put("offset", mOffset);
        }
    }
}
