package ru.touchin.vkchat.providers.base;


import android.content.Context;

import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;

import ru.touchin.vkchat.providers.RequestFailListener;

public class FriendsTaskCreator extends RemoteAggregationPagingTaskCreator {
    private Context context;

    public FriendsTaskCreator(RequestFailListener requestFailListener, Context context) {
        super(requestFailListener);
        this.context = context;
    }

    @Override
    public AggregationPagingTask createPagingTask(int offset, int limit) {
        return new FriendsTask(getRequestFailListener(), offset, limit);
    }

}
