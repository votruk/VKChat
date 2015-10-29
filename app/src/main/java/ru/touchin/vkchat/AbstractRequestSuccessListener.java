package ru.touchin.vkchat;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public abstract class AbstractRequestSuccessListener<TResponse> implements RequestListener<TResponse> {

    @Override
    public void onRequestFailure(final SpiceException spiceException) {
        // Stub.
    }

}