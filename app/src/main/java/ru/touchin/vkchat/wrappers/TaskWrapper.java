package ru.touchin.vkchat.wrappers;


import org.zuzuk.tasks.aggregationtask.AggregationTaskStage;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.tasks.base.AbstractTask;
import org.zuzuk.tasks.realloading.BaseTaskWrapper;
import org.zuzuk.tasks.realloading.ChainedRequestListener;
import org.zuzuk.tasks.realloading.RealLoadingAggregationTaskListener;

public class TaskWrapper extends BaseTaskWrapper{
	public <TResult> TaskWrapper(final AbstractTask<TResult> task,
								 final ChainedRequestListener<TResult> chainedRequestListener,
								 final RealLoadingAggregationTaskListener realLoadingAggregationTaskListener) {
		super(task, chainedRequestListener, realLoadingAggregationTaskListener);
	}

	public <TResult> TaskWrapper(final AbstractTask<TResult> task) {
		super(task, null, null);
	}

	public TaskWrapper(final RealLoadingAggregationTaskListener realLoadingAggregationTaskListener) {
		this(null, null, realLoadingAggregationTaskListener);
	}

	@Override
	public boolean isLoaded(final AggregationTaskStageState currentTaskStageState) {
		return super.isLoaded(currentTaskStageState) && !currentTaskStageState.hasExceptions();
	}

	@Override
	public void load(final RequestAndTaskExecutor executor, final AggregationTaskStageState currentTaskStageState) {
		if (currentTaskStageState.getTaskStage() == AggregationTaskStage.REAL_LOADING || currentTaskStageState.isTaskWrapped()) {
			realLoad(executor, currentTaskStageState);
		}
	}
}
