package ru.touchin.vkchat.providers;


import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;

import ru.touchin.vkchat.providers.base.RemoteAggregationPagingTaskCreator;

public class FriendsTaskCreator extends RemoteAggregationPagingTaskCreator {

    public FriendsTaskCreator(RequestFailListener requestFailListener) {
        super(requestFailListener);
    }

    @Override
    public AggregationPagingTask createPagingTask(int offset, int limit) {
        return new FriendsTask(getRequestFailListener(), offset, limit);
    }

}
