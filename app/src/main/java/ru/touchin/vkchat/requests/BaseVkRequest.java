package ru.touchin.vkchat.requests;

import com.google.api.client.http.GenericUrl;

import org.zuzuk.tasks.remote.AbstractGetJsonRequest;

import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.models.VkResponse;

public abstract class  BaseVkRequest<T extends VkResponse> extends AbstractGetJsonRequest<T> {
    public static final String ADDRESS = "https://api.vk.com/method";
    public static final String API_VERSION = "5.37";

    public BaseVkRequest(final Class<T> responseResultType) {
        super(responseResultType);
    }

    protected abstract String getMethod();

    @Override
    protected String getUrl() {
        return ADDRESS + getMethod();
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        url.put("access_token", Settings.VK_ACCESS_TOKEN.get(VKChatApp.getInstance()));

        url.put("v", API_VERSION);
    }


}