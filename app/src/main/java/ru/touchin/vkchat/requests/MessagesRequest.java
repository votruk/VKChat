package ru.touchin.vkchat.requests;

import com.google.api.client.http.GenericUrl;

import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.models.Friends;
import ru.touchin.vkchat.models.Messages;
import ru.touchin.vkchat.models.MessagesObject;
import ru.touchin.vkchat.models.MessagesResponse;


public class MessagesRequest extends BaseVkRequest {
    private int offset;
    private int limit;
    private long userId;

    public MessagesRequest(int limit, int offset, long userId) {
        super(MessagesResponse.class);
        this.offset = offset;
        this.userId = userId;
        this.limit = limit;
    }

    @Override
    protected String getMethod() {
        return "/messages.getHistory";
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        super.setupUrlParameters(url);
        url.put("user_id", userId);
        url.put("count", limit);
        if (offset != 0) {
            url.put("offset", offset);
        }
    }
}
