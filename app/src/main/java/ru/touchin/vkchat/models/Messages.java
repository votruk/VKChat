package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;

public class Messages extends ObjectFromJson {

    @Key("items")
    private ArrayList<Message> messages;

    @Key("count")
    private Integer count;

    @Key("unread")
    private Integer unused;



    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getUnused() {
        return unused;
    }


}
