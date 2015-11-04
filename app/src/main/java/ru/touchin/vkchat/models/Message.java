package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;

public class Message extends ObjectFromJson{
    @Key("id")
    private Long mId;

    @Key("date")
    private Long mDate;

    @Key("out")
    private Integer isMessageMine;

    @Key("user_id")
    private Long mUserId;

    @Key("from_id")
    private Long mFromUserId;

    @Key("read_state")
    private Integer mReadState;

    @Key("body")
    private String mBody;

    @Key("attachments")
    private ArrayList<Attachment> mAttachments;


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

    public ArrayList<Attachment> getAttachments() {
        return mAttachments;
    }
}
