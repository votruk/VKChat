package ru.touchin.vkchat;

import org.zuzuk.settings.Setting;

import java.util.Date;

public final class Settings {

    public static final String VK_ACCESS_TOKEN_NAME = "ACCESS_TOKEN";
    public static final String VK_USER_ID_NAME = "USER_ID";
    public static final String VK_TOKEN_EXPIRES_IN_NAME = "EXPIRES_IN";

    public static final Setting<String> VK_ACCESS_TOKEN = new Setting<>(VK_ACCESS_TOKEN_NAME, String.class);
    public static final Setting<String> VK_USER_ID = new Setting<>(VK_USER_ID_NAME, String.class);
    public static final Setting<Long> VK_TOKEN_EXPIRES_IN = new Setting<>(VK_TOKEN_EXPIRES_IN_NAME, Long.class);


    private Settings() {

    }
}
