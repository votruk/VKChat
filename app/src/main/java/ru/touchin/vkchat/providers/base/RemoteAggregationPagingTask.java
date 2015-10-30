package ru.touchin.vkchat.providers.base;

import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStage;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;

import java.util.ArrayList;

public abstract class RemoteAggregationPagingTask<TItem> implements AggregationPagingTask<TItem> {

    private RequestFailListener requestFailListener;

    private ArrayList<TItem> pageItems;
    private final int offset;
    private final int limit;

    protected RemoteAggregationPagingTask(RequestFailListener requestFailListener, int offset, int limit) {
        this.requestFailListener = requestFailListener;
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public boolean isLoadingNeeded(AggregationTaskStageState currentTaskStageState) {
        return true;
    }

    @Override
    public boolean isLoaded(AggregationTaskStageState currentTaskStageState) {
        return currentTaskStageState.getTaskStage() != AggregationTaskStage.PRE_LOADING && !currentTaskStageState.hasExceptions();
    }

    @Override
    public void onLoadingStarted(AggregationTaskStageState currentTaskStageState) {
    }

    @Override
    public void onLoaded(AggregationTaskStageState currentTaskStageState) {
    }

    @Override
    public void onFailed(AggregationTaskStageState currentTaskStageState) {
        if (currentTaskStageState.getTaskStage() == AggregationTaskStage.REAL_LOADING) {
            boolean noDataToShow = !currentTaskStageState.isLoaded() &&
                    !currentTaskStageState.getPreviousStageState().isLoaded();
            if (noDataToShow) {
                pageItems = null;
            }
            if (currentTaskStageState.hasExceptions()) {
                requestFailListener.onRequestFailure(currentTaskStageState.getExceptions());
            }
        }
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public List<TItem> getPageItems() {
        return pageItems;
    }

    public void setPageItems(ArrayList<TItem> pageItems) {
        this.pageItems = pageItems;
    }

}