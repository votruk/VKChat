package ru.touchin.vkchat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.util.List;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.adapters.FriendsAdapter;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.providers.base.FriendsTaskCreator;


public class FriendsListFragment extends AbstractListViewFragment {
    private RequestPagingProvider<Friend> dialogListProvider;
    private FriendsAdapter mAdapter;
    private List<Friend> mFriendList;


    @Override

    protected void onCreateRenewable() {
        super.onCreateRenewable();
        dialogListProvider = new RequestPagingProvider<>(this, new FriendsTaskCreator(this, getActivity()));
    }

    @Override
    protected void onDestroyRenewable() {
        super.onDestroyRenewable();
        dialogListProvider = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new FriendsAdapter();
        mAdapter.setProvider(dialogListProvider);
        ((ListView) findViewById(R.id.fragmentList)).setAdapter(mAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter = null;
    }

    @Override
    protected void loadFragmentData(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        dialogListProvider.initialize(getListPosition(), executor);
    }


    @Override
    protected View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends_list, container, false);
    }

    @Override
    protected void onItemClick(int position) {
        super.onItemClick(position);
//        pushFragment(TweetFragment.class, TweetFragment.createArgs(mAdapter.get(position)));
    }

}
