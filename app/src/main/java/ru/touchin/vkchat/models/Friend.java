package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class Friend extends ObjectFromJson {

    @Key("id")
    private Long mUserId;

    @Key("first_name")
    private String mFirstName;

    @Key("last_name")
    private String mLastName;

    @Key("photo_max")
    private String mPhotoUrl;

    @Key("online")
    private Integer mIsOnline;

    public Long getUserId() {
        return mUserId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public Integer getIsOnline() {
        return mIsOnline;
    }

    public String getFullName() {
        return mFirstName + " " + mLastName;
    }
}
