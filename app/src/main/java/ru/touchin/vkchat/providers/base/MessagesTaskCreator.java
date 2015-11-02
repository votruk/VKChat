package ru.touchin.vkchat.providers.base;


import android.content.Context;

import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;

import ru.touchin.vkchat.providers.RequestFailListener;

public class MessagesTaskCreator extends RemoteAggregationPagingTaskCreator {
    private long userId;

    public MessagesTaskCreator(RequestFailListener requestFailListener, long userId) {
        super(requestFailListener);
        this.userId = userId;
    }

    @Override
    public AggregationPagingTask createPagingTask(int offset, int limit) {
        return new MessagesTask(getRequestFailListener(), offset, limit, userId);
    }

}
