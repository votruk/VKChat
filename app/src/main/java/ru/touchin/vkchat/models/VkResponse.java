package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class VkResponse<T> extends ObjectFromJson {
    @Key("response")
    private T response;
    @Key("error_code")
    private int errorCode;
    @Key("error_message")
    private String errorMessage;

    public T getResponse() {
        return response;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}