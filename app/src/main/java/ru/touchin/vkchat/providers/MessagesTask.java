package ru.touchin.vkchat.providers;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.util.ArrayList;

import ru.touchin.vkchat.requests.AbstractRequestSuccessListener;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.models.MessagesResponse;
import ru.touchin.vkchat.providers.base.RemoteAggregationPagingTask;
import ru.touchin.vkchat.requests.MessagesRequest;
import ru.touchin.vkchat.views.MessageItem;
import ru.touchin.vkchat.views.MessageLeft;
import ru.touchin.vkchat.views.MessageRight;

public class MessagesTask extends RemoteAggregationPagingTask {
    private long userId;

    public MessagesTask(RequestFailListener requestFailListener, int offset, int limit, long userId) {
        super(requestFailListener, offset, limit);
        this.userId = userId;
    }

    @Override
    public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        executor.executeRequest(new MessagesRequest(getLimit(), getOffset(), userId),
                new AbstractRequestSuccessListener<MessagesResponse>() {
            @Override
            public void onRequestSuccess(MessagesResponse response) {

                ArrayList<MessageItem> messages = new ArrayList<>();
                for (Message m : response.getResponse().getMessages()) {
                    if (m.isMessageMine()) {
                        messages.add(new MessageRight(m));
                    } else {
                        messages.add(new MessageLeft(m));
                    }
                }

                setPageItems(messages);
            }

        });
    }
}