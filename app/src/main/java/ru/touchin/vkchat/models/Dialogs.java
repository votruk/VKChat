package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import java.util.List;

public class Dialogs {

    @Key("items")
    private List<DialogItem> mDialogItems;

}
