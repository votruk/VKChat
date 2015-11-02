package ru.touchin.vkchat;

import org.zuzuk.settings.Setting;

public final class Settings {

//    public static final String RESPONDENT_PARAMETERS_NAME = "RESPONDENT_PARAMETERS";
    public static final String RESPONDENT_PROFILE_NAME = "RESPONDENT_PROFILE_NAME";
//    public static final String LOGIN_DATA_NAME = "LOGIN_DATA";
    public static final String VK_ACCESS_TOKEN_NAME = "ACCESS_TOKEN";
    public static final String VK_USER_ID_NAME = "USER_ID";
    public static final String VK_TOKEN_EXPIRES_IN_NAME = "EXPIRES_IN";
//    public static final String VK_ACCESS_DATA_NAME = "ACCESS_DATA";

//    public static final Setting<RespondentLoginData> LOGIN_DATA = new Setting<>(LOGIN_DATA_NAME, RespondentLoginData.class);
//    public static final Setting<RespondentProfile> RESPONDENT_PROFILE = new Setting<>(RESPONDENT_PROFILE_NAME, RespondentProfile.class);

    public static final Setting<String> PUSH_REGISTRATION_ID = new Setting<>("PUSH_REGISTRATION_ID", String.class);
    public static final Setting<Integer> APP_VERSION_CODE = new Setting<>("APP_VERSION_CODE", Integer.class);

//    public static final Setting<RespondentParameters> RESPONDENT_PARAMETERS = new Setting<>(RESPONDENT_PARAMETERS_NAME, RespondentParameters.class);
//    public static final Setting<VKAccessData> VK_ACCESS_TOKEN = new Setting<>(VK_ACCESS_TOKEN_NAME, VKAccessData.class);
    public static final Setting<String> VK_ACCESS_TOKEN = new Setting<>(VK_ACCESS_TOKEN_NAME, String.class);
    public static final Setting<String> VK_USER_ID = new Setting<>(VK_USER_ID_NAME, String.class);
//    public static final Setting<VKAccessData> VK_ACCESS_DATA = new Setting<>(VK_ACCESS_DATA_NAME, VKAccessData.class);

    public static final Setting<String> VK_TOKEN_EXPIRES_IN = new Setting<>(VK_TOKEN_EXPIRES_IN_NAME, String.class);

//    public static final Setting<LastWifiConnection> LAST_WIFI_CONNECTION = new Setting<>("LAST_WIFI_CONNECTION", LastWifiConnection.class);

    private Settings() {

    }
}
