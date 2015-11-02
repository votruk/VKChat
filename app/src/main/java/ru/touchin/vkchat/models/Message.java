package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class Message extends ObjectFromJson{
    @Key("id")
    private Long mId;

    @Key("date")
    private Long mDate;

    @Key("out")
    private Integer isLastOut;

    @Key("user_id")
    private Integer mUserId;

    @Key("read_state")
    private Integer mReadState;

    @Key("title")
    private String mTitle;

    @Key("body")
    private String mBody;


    public Long getId() {
        return mId;
    }

    public Long getDate() {
        return mDate;
    }

    public Integer getIsLastOut() {
        return isLastOut;
    }

    public Integer getUserId() {
        return mUserId;
    }

    public Integer getReadState() {
        return mReadState;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

}
