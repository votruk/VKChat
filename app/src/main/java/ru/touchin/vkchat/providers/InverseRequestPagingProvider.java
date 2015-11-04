package ru.touchin.vkchat.providers;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.providers.base.PagingTaskCreator;
import org.zuzuk.tasks.aggregationtask.AggregationTaskExecutor;

import java.util.List;

import ru.touchin.vkchat.fragments.MessagesFragment;

public class InverseRequestPagingProvider<T> extends RequestPagingProvider<T> {


    public InverseRequestPagingProvider(AggregationTaskExecutor aggregationTaskExecutor, PagingTaskCreator requestCreator) {
        super(aggregationTaskExecutor, requestCreator);
    }

    @Override
    protected void onPageLoaded(int pageIndex, List<T> ts) {
        super.onPageLoaded(pageIndex, ts);
    }

    @Override
    public T getItem(int position) {
        return super.getItem(getTotalCount() - position - 1);
    }

    @Override
    public void onDataSetChanged() {
        super.onDataSetChanged();
    }
}
