package ru.touchin.vkchat.providers.base;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.io.IOException;
import java.util.ArrayList;

import ru.touchin.vkchat.AbstractRequestSuccessListener;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.models.Friends;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.models.Messages;
import ru.touchin.vkchat.models.MessagesObject;
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
                ArrayList<Message> messages = new ArrayList<>();
                ObjectMapper mapper = new ObjectMapper();
                for (Object o : response.getMessages()) {
                    try {
                        Message m = mapper.readValue(mapper.writeValueAsString(o), Message.class);
                        messages.add(m);
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                setPageItems(messages);
            }

        });
    }
}