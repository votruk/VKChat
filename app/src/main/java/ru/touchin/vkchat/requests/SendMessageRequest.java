package ru.touchin.vkchat.requests;

import com.google.api.client.http.GenericUrl;

import ru.touchin.vkchat.models.MessagesResponse;
import ru.touchin.vkchat.models.SendMessageResponse;


public class SendMessageRequest extends BaseVkRequest {
    private int uniqueId;
    private String message;
    private long userId;

    public SendMessageRequest(long userId, String message, int uniqueId) {
        super(SendMessageResponse.class);
        this.message = message;
        this.userId = userId;
        this.uniqueId = uniqueId;
    }

    @Override
    protected String getMethod() {
        return "/messages.send";
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        super.setupUrlParameters(url);
        url.put("user_id", userId);
        url.put("message", message);
        url.put("guid", uniqueId);

    }
}
