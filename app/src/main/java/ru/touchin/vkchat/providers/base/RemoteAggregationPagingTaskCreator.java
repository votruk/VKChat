package ru.touchin.vkchat.providers.base;

import org.zuzuk.providers.base.PagingTaskCreator;

import ru.touchin.vkchat.providers.RequestFailListener;

public abstract class RemoteAggregationPagingTaskCreator<TItem> implements PagingTaskCreator<TItem> {

    private final RequestFailListener requestFailListener;

    protected RemoteAggregationPagingTaskCreator(RequestFailListener requestFailListener) {
        this.requestFailListener = requestFailListener;
    }

    public RequestFailListener getRequestFailListener() {
        return requestFailListener;
    }

}