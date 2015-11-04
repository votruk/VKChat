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
    private List<ErrorRequestParam> mErrorRequestParamList;

    public Integer getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public List<ErrorRequestParam> getErrorRequestParamList() {
        return mErrorRequestParamList;
    }
}
