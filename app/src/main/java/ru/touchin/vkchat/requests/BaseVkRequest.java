package ru.touchin.vkchat.requests;

import android.content.Context;

import com.google.api.client.http.GenericUrl;

import org.zuzuk.tasks.remote.AbstractGetJsonRequest;

import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.models.Friends;

public class BaseVkRequest extends AbstractGetJsonRequest<Friends> {
    public static final String ADDRESS = "https://api.vk.com/method/friends.get";
    protected Context context;
    private int limit;

    public BaseVkRequest(Context context, int limit) {
        super(Friends.class);
        this.context = context;
        this.limit = limit;
    }

    @Override
    protected String getUrl() {
        return ADDRESS;
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        url.put("access_token", Settings.VK_ACCESS_TOKEN.get(context));
        url.put("count", limit);
    }

}