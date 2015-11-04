package ru.touchin.vkchat.requests;

import com.google.api.client.http.GenericUrl;

import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.models.Friends;
import ru.touchin.vkchat.models.FriendsResponse;
import ru.touchin.vkchat.models.VkResponse;


public class FriendsRequest extends BaseVkRequest<FriendsResponse> {
    private int mOffset;
    private int limit;

    public FriendsRequest(int limit, int offset) {
        super(FriendsResponse.class);
        mOffset = offset;
        this.limit = limit;
    }

    @Override
    protected String getMethod() {
        return "/friends.get";
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        super.setupUrlParameters(url);
        url.put("user_id", Settings.VK_USER_ID.get(VKChatApp.getInstance()));
        url.put("order", "name");
        url.put("fields", "photo_max");
        url.put("count", limit);
        if (mOffset != 0) {
            url.put("offset", mOffset);
        }
    }

}
