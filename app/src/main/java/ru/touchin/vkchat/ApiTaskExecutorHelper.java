package ru.touchin.vkchat;

import com.squawksurveys.surveys.api.ApiExecutor;

import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.tasks.aggregationtask.TaskExecutorHelper;

public class ApiTaskExecutorHelper extends TaskExecutorHelper {

    @Override
    protected RequestAndTaskExecutor createRequestAndTaskExecutor() {
        return new ApiExecutor();
    }

}
