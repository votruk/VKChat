package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import java.util.List;

public class Friends {

    @Key("items")
    private List<DialogItem> mDialogItems;

}
