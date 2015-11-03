package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import java.util.ArrayList;

public class Messages extends VkResponse {

    @Key("response")
    private ArrayList<Object> messages;

    public ArrayList<Object> getMessages() {
        return messages;
    }
}
