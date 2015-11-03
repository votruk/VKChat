package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;
import java.util.List;

public class MessagesObject extends ObjectFromJson {
    @Key("")
    private Integer count;

    @Key("")
    private ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        return messages;
    }


}
