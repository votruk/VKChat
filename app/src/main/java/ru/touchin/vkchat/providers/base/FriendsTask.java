package ru.touchin.vkchat.providers.base;

import android.content.Context;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.util.ArrayList;

import ru.touchin.vkchat.AbstractRequestSuccessListener;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.models.Friends;
import ru.touchin.vkchat.models.VkResponse;
import ru.touchin.vkchat.providers.RequestFailListener;
import ru.touchin.vkchat.requests.FriendsRequest;

public class FriendsTask extends RemoteAggregationPagingTask {
    private Context context;

    public FriendsTask(RequestFailListener requestFailListener, int offset, int limit, Context context) {
        super(requestFailListener, offset, limit);
        this.context = context;
    }

    @Override
    public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        executor.executeRequest(new FriendsRequest(context, getLimit()) {
        }, new AbstractRequestSuccessListener<Friends>() {
            @Override
            public void onRequestSuccess(Friends response) {
                ArrayList<Friend> lastPageFriends = response.getFriends();
                setPageItems(lastPageFriends);
            }

        });
    }
}