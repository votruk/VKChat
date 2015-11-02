package ru.touchin.vkchat.requests;

import com.google.api.client.http.GenericUrl;

import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.models.Friends;
import ru.touchin.vkchat.models.Messages;


public class MessagesRequest extends BaseVkRequest {
    private int offset;
    private long userId;

    public MessagesRequest(int limit, int offset, long userId) {
        super(Messages.class, limit);
        this.offset = offset;
        this.userId = userId;
    }

    @Override
    protected String getMethod() {
        return "/messages.getHistory";
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        super.setupUrlParameters(url);
        url.put("user_id", userId);
        if (offset != 0) {
            url.put("offset", offset);
        }
    }
}
