package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class ErrorRequestParam extends ObjectFromJson {

    @Key("key")
    private String mKey;

    @Key("value")
    private String mValue;

    public String getKey() {
        return mKey;
    }

    public String getValue() {
        return mValue;
    }
}
