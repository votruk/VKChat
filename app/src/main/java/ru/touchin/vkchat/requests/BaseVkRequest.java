package ru.touchin.vkchat.requests;

import org.zuzuk.tasks.remote.AbstractGetJsonRequest;

import ru.touchin.vkchat.models.Friend;

public class BaseVkRequest extends AbstractGetJsonRequest<Friend> {
    public static final String ADDRESS = "https://api.twitter.com/1.1/search/tweets.json";
    private Context context;
    private String hashTag;
    private int limit;

    public BaseVkRequest(Context context, String hashTag, int limit) {
        super(Tweets.class);
        this.context = context;
        this.hashTag = hashTag;
        this.limit = limit;
    }

    @Override
    protected String getUrl() {
        return ADDRESS;
    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        url.put("q", "%23" + hashTag);
        url.put("count", limit);
    }

    @Override
    protected Request.Builder createHttpRequest() throws IOException {
        String auth = "Bearer " + context.getString(R.string.access_token);
        return super.createHttpRequest().header("Authorization", auth);
    }
}