package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class RespondentLoginData extends ObjectFromJson {

    @Key("session_id")
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }
}
