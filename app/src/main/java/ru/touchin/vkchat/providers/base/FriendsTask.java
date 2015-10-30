package ru.touchin.vkchat.providers.base;

public class FriendsTask extends RemoteAggregationPagingTask {
    private Context context;
    private String hashTag;
    private String lastTweetId;

    public FriendsTask(RequestFailListener requestFailListener, int offset, int limit, Context context, String hashTag) {
        super(requestFailListener, offset, limit);
        this.context = context;
        this.hashTag = hashTag;
    }

    @Override
    public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        executor.executeRequest(new ElderTweetsRequest(context, hashTag, getLimit(), lastTweetId) {
        }, new RequestSuccessListener<Tweets>() {
            @Override
            public void onRequestSuccess(Tweets tweets) {
                ArrayList<Tweet> lastPageTweets = tweets.getTweets();
                setPageItems(lastPageTweets);
                lastTweetId = lastPageTweets.get(lastPageTweets.size() - 1).getId();
            }
        });
    }
}