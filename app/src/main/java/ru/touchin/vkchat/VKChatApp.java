package ru.touchin.vkchat;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;

import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.tasks.aggregationtask.TaskExecutorHelper;
import org.zuzuk.tasks.remote.base.TaskExecutorHelperCreator;
import org.zuzuk.utils.log.LcHelper;

import java.util.Locale;

public class VKChatApp extends Application implements TaskExecutorHelperCreator {

    private static VKChatApp instance;
    private static Locale currentLocale = Locale.getDefault();

    @Override
    public void onCreate() {
        super.onCreate();

        setLogging();

        initializeSingletons(this);

        final OkHttpClient httpClient = new OkHttpClient();
        OkHttpHelper.setTimeouts(httpClient);
        final ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(getApplicationContext(), httpClient).build();
        Fresco.initialize(getApplicationContext(), config);

//        if (Settings.PHOTO_WIDTH.get(this) == null || Settings.PHOTO_WIDTH.get(this) == 0) {
            WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels/3;
            Settings.PHOTO_WIDTH.set(this, width);
//        }
    }

    @Override
    public TaskExecutorHelper createTaskExecutorHelper() {
        return new TaskExecutorHelper() {
            @Override
            protected RequestAndTaskExecutor createRequestAndTaskExecutor() {
                return new RequestAndTaskExecutor();
            }
        };
    }

    private static void initializeSingletons(final VKChatApp instance) {
        VKChatApp.instance = instance;
    }

    public static VKChatApp getInstance() {
        return instance;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    private void setLogging() {
        if (BuildConfig.DEBUG) {
            LcHelper.initialize(Log.DEBUG);
            LcHelper.setRobospiceLogLevel(Log.ERROR);
        } else {
            LcHelper.initialize(Log.ERROR);
        }
    }
}