package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class VkResponse extends ObjectFromJson {
//    @Key("response")
//    private T response;
    @Key("error")
    private VkError mError;

    public VkError getError() {
        return mError;
    }

//    public T getResponse() {
//        return response;
//    }


}