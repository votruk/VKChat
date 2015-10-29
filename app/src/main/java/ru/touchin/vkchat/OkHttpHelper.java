package ru.touchin.vkchat;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

public final class OkHttpHelper {

    private OkHttpHelper(){

    }

    public static void setTimeouts(final OkHttpClient okHttpClient) {
        okHttpClient.setConnectTimeout(BuildConfig.API_TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(BuildConfig.API_TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(BuildConfig.API_TIME_OUT, TimeUnit.SECONDS);
    }

}
