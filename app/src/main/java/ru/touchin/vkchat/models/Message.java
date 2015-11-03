package ru.touchin.vkchat.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class Message extends ObjectFromJson{
    @Key("uid")
    @JsonProperty("uid")
    private Long mId;

    @Key("mid")
    @JsonProperty("mid")
    private Long mMineId;

    @Key("date")
    @JsonProperty("date")
    private Long mDate;

    @Key("out")
    @JsonProperty("out")
    private Integer isMessageMine;

    @Key("user_id")
    @JsonProperty("user_id")
    private Long mUserId;

    @Key("from_id")
    @JsonProperty("from_id")
    private Long mFromUserId;

    @Key("read_state")
    @JsonProperty("read_state")
    private Integer mReadState;

    @Key("body")
    @JsonProperty("body")
    private String mBody;


    public Long getId() {
        return mId;
    }

    public Long getDate() {
        return mDate;
    }

    public Integer getIsMessageMine() {
        return isMessageMine;
    }

    public Long getUserId() {
        return mUserId;
    }

    public Long getFromUserId() {
        return mFromUserId;
    }

    public Integer getReadState() {
        return mReadState;
    }

    public String getBody() {
        return mBody;
    }
}
