package ru.touchin.vkchat.models;


import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.List;

public class VkError extends ObjectFromJson {
    @Key("error_code")
    private Integer mErrorCode;

    @Key("error_msg")
    private String mErrorMessage;

    @Key("request_params")
    private List<RequestParam> mRequestParamList;

    public Integer getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public List<RequestParam> getRequestParamList() {
        return mRequestParamList;
    }
}
