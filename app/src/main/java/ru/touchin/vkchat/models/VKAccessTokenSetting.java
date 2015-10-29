package ru.touchin.vkchat.models;

import com.google.api.client.json.Json;
import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class VKAccessTokenSetting extends ObjectFromJson {

    @Key("access_key")
    private String mAccessKey;

    public String getAccessKey() {
        return mAccessKey;
    }

}
