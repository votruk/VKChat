package ru.touchin.vkchat.providers.base;

import android.content.Context;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.util.ArrayList;

import ru.touchin.vkchat.AbstractRequestSuccessListener;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.models.Friends;
import ru.touchin.vkchat.models.FriendsResponse;
import ru.touchin.vkchat.providers.RequestFailListener;
import ru.touchin.vkchat.requests.FriendsRequest;

public class FriendsTask extends RemoteAggregationPagingTask {

    public FriendsTask(RequestFailListener requestFailListener, int offset, int limit) {
        super(requestFailListener, offset, limit);
    }

    @Override
    public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        executor.executeRequest(new FriendsRequest(getLimit(), getOffset()),
                new AbstractRequestSuccessListener<FriendsResponse>() {
            @Override
            public void onRequestSuccess(FriendsResponse response) {
                ArrayList<Friend> lastPageFriends = response.getResponse().getFriends();
                setPageItems(lastPageFriends);
            }

        });
    }
}