package ru.touchin.vkchat.providers.base;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.util.ArrayList;

import ru.touchin.vkchat.AbstractRequestSuccessListener;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.models.Friends;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.models.Messages;
import ru.touchin.vkchat.providers.RequestFailListener;
import ru.touchin.vkchat.requests.FriendsRequest;
import ru.touchin.vkchat.requests.MessagesRequest;

public class MessagesTask extends RemoteAggregationPagingTask {
    private long userId;

    public MessagesTask(RequestFailListener requestFailListener, int offset, int limit, long userId) {
        super(requestFailListener, offset, limit);
        this.userId = userId;
    }

    @Override
    public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        executor.executeRequest(new MessagesRequest(getLimit(), getOffset(), userId),
                new AbstractRequestSuccessListener<Messages>() {
            @Override
            public void onRequestSuccess(Messages response) {
                ArrayList<Message> lastPageFriends = response.getMessages();
                setPageItems(lastPageFriends);
            }

        });
    }
}