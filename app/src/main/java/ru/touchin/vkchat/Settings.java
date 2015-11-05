package ru.touchin.vkchat;

import android.util.DisplayMetrics;

import org.zuzuk.settings.Setting;

import java.util.Date;

public final class Settings {

    public static final String VK_ACCESS_TOKEN_NAME = "ACCESS_TOKEN";
    public static final String VK_USER_ID_NAME = "USER_ID";
    public static final String VK_TOKEN_EXPIRES_IN_NAME = "EXPIRES_IN";
    public static final String DEVICE_DISPLAY_METRICS_NAME = "DISPLAY_METRICS";
    public static final String PHOTO_WIDTH_NAME = "PHOTO_WIDTH";

    public static final Setting<String> VK_ACCESS_TOKEN = new Setting<>(VK_ACCESS_TOKEN_NAME, String.class);
    public static final Setting<String> VK_USER_ID = new Setting<>(VK_USER_ID_NAME, String.class);
    public static final Setting<Long> VK_TOKEN_EXPIRES_IN = new Setting<>(VK_TOKEN_EXPIRES_IN_NAME, Long.class);

    public static final Setting<DisplayMetrics> DEVICE_DISPLAY_METRICS = new Setting<>(DEVICE_DISPLAY_METRICS_NAME, DisplayMetrics.class);
    public static final Setting<Integer> PHOTO_WIDTH = new Setting<>(DEVICE_DISPLAY_METRICS_NAME, Integer.class);


    private Settings() {

    }
}
