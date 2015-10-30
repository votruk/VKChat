package ru.touchin.vkchat.providers.base;

public abstract class RemoteAggregationPagingTaskCreator<TItem> implements PagingTaskCreator<TItem> {

    private final RequestFailListener requestFailListener;

    protected RemoteAggregationPagingTaskCreator(RequestFailListener requestFailListener) {
        this.requestFailListener = requestFailListener;
    }

    public RequestFailListener getRequestFailListener() {
        return requestFailListener;
    }

}