package ru.touchin.vkchat.providers;

import java.util.List;

public interface RequestFailListener {

    void onRequestFailure(List<Exception> exceptionList);

}
