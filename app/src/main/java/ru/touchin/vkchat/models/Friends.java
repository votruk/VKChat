package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;
import java.util.List;

public class Friends extends VkResponse {

    @Key("response")
    private ArrayList<Friend> mFriends;

    public ArrayList<Friend> getFriends() {
        return mFriends;
    }
}
