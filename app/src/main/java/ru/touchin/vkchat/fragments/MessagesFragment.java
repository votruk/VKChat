package ru.touchin.vkchat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.adapters.MessagesAdapter;
import ru.touchin.vkchat.fragments.base.AbstractInverseFragment;
import ru.touchin.vkchat.fragments.base.AbstractListViewFragment;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.providers.InverseRequestPagingProvider;
import ru.touchin.vkchat.providers.MessagesTaskCreator;

public class MessagesFragment extends AbstractInverseFragment {
    public static final String USER_ID = "userId";
    private long userId;

    private InverseRequestPagingProvider<Message> messagesListProvider;
    private MessagesAdapter mAdapter;


    @Override
    protected void onCreateRenewable() {
        super.onCreateRenewable();
        userId = (long) getArguments().get(USER_ID);
        messagesListProvider = new InverseRequestPagingProvider<>(this, new MessagesTaskCreator(this, userId));
    }

    @Override
    protected void onDestroyRenewable() {
        super.onDestroyRenewable();
        messagesListProvider = null;
    }

    public static Bundle createArgs(Long userId) {
        Bundle args = new Bundle();
        args.putSerializable(USER_ID, userId);
        return args;
    }

    @Override
    protected View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        float dp = displayMetrics.density;
        mAdapter = new MessagesAdapter(width, dp);
        mAdapter.setProvider(messagesListProvider);
        ((ListView) findViewById(R.id.messages_list)).setAdapter(mAdapter);
    }

    @Override
    protected void loadFragmentData(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        messagesListProvider.initialize(getListPosition(), executor);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter = null;
    }

}