package ru.touchin.vkchat;

import java.util.List;

public interface RequestFailListener {

    void onRequestFailure(List<Exception> exceptionList);

}
