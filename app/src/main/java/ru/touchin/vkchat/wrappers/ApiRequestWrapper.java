package ru.touchin.vkchat.wrappers;


import com.octo.android.robospice.persistence.exception.SpiceException;

import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.tasks.realloading.ChainedRequestListener;
import org.zuzuk.tasks.realloading.RealLoadingAggregationTaskListener;
import org.zuzuk.tasks.remote.base.AbstractRemoteRequest;

import ru.touchin.vkchat.models.VkResponse;

public class ApiRequestWrapper extends TaskWrapper {
	public <TResult, TResponse extends VkResponse<TResult>> ApiRequestWrapper(final AbstractRemoteRequest<TResponse> request,
																				  final ChainedRequestListener<TResult> reqListener,
																				  final RealLoadingAggregationTaskListener aggregationTaskListener) {
		super(request, ApiRequestWrapper.<TResult, TResponse>wrapApiRequestListener(reqListener), aggregationTaskListener);
	}

	@SuppressWarnings("PMD.UnusedPrivateMethod")
	private static <TResult, TResponse extends VkResponse<TResult>> ChainedRequestListener<TResponse> wrapApiRequestListener(
			final ChainedRequestListener<TResult> requestListener) {
		return new ChainedRequestListener<TResponse>() {
			@Override
			public void onRequestSuccess(final TResponse response, final RequestAndTaskExecutor executor) {
				if (requestListener != null) {
					requestListener.onRequestSuccess(response.getResponse(), executor);
				}
			}

			@Override
			public void onRequestFailure(final SpiceException spiceException, final RequestAndTaskExecutor executor) {
				if (requestListener != null) {
					requestListener.onRequestFailure(spiceException, executor);
				}
			}
		};
	}
}
