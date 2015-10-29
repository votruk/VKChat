package ru.touchin.vkchat;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.tasks.remote.base.AbstractRemoteRequest;

import ru.touchin.vkchat.models.VkResponse;

public class ApiExecutor extends RequestAndTaskExecutor {

    private static <TResult, TResponse extends VkResponse<TResult>> RequestListener<TResponse>
        wrapApiRequestListener(final RequestListener<TResult> requestListener) {
        return new RequestListener<TResponse>() {
            @Override
            public void onRequestSuccess(final TResponse response) {
                if (requestListener != null) {
                    requestListener.onRequestSuccess(response.getResponse());
                }
            }

            @Override
            public void onRequestFailure(final SpiceException spiceException) {
                if (requestListener != null) {
                    requestListener.onRequestFailure(spiceException);
                }
            }
        };
    }

    public <TResult, TResponse extends VkResponse<TResult>> void executeApiRequest(final AbstractRemoteRequest<TResponse> request,
                                                                                       final RequestListener<TResult> requestListener) {
        executeRequest(request, ApiExecutor.<TResult, TResponse>wrapApiRequestListener(requestListener));
    }

}
