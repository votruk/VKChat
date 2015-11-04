package ru.touchin.vkchat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import com.octo.android.robospice.persistence.exception.SpiceException;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.tasks.realloading.ChainedRequestListener;

import java.util.Random;

import ru.touchin.vkchat.AbstractRequestSuccessListener;
import ru.touchin.vkchat.R;
import ru.touchin.vkchat.adapters.MessagesAdapter;
import ru.touchin.vkchat.fragments.base.AbstractInverseFragment;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.providers.InverseRequestPagingProvider;
import ru.touchin.vkchat.providers.MessagesTaskCreator;
import ru.touchin.vkchat.requests.SendMessageRequest;
import ru.touchin.vkchat.wrappers.ApiRequestWrapper;

public class MessagesFragment extends AbstractInverseFragment {
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	private long userId;
	private String userName;
	private EditText messageText;

	private InverseRequestPagingProvider<Message> messagesListProvider;
	private MessagesAdapter mAdapter;

	public static Bundle createArgs(Long userId, String userName) {
		Bundle args = new Bundle();
		args.putSerializable(USER_ID, userId);
		args.putSerializable(USER_NAME, userName);
		return args;
	}

	@Override
	protected void onCreateRenewable() {
		super.onCreateRenewable();
		userId = (long) getArguments().get(USER_ID);
		userName = (String) getArguments().get(USER_NAME);
		messagesListProvider = getMessageInverseRequestPagingProvider();
	}

	@NonNull
	private InverseRequestPagingProvider<Message> getMessageInverseRequestPagingProvider() {
		return new InverseRequestPagingProvider<>(this, new MessagesTaskCreator(this, userId));
	}

	@Override
	protected void onDestroyRenewable() {
		super.onDestroyRenewable();
		messagesListProvider = null;
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

		messageText = findViewById(R.id.new_message_text);

		findViewById(R.id.send_message).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sendMessage();
					}
				}
		);

//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_24dp);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setIcon(R.drawable.ic_chevron_left_white_24dp);
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

	@Override
	public CharSequence getTitle() {
		return userName;
	}

	@Override
	public boolean isHomeButtonVisible() {
		return true;
	}


	private void sendMessage() {
		if (StringUtils.isNotBlank(messageText.getText().toString())) {
			int randomId = new Random().nextInt(1000000);
			executeAggregationTask(
					new ApiRequestWrapper(new SendMessageRequest(userId, messageText.getText().toString(), randomId),
					new ChainedRequestListener<Integer>() {
						@Override
						public void onRequestSuccess(Integer integer, RequestAndTaskExecutor executor) {
//							messagesListProvider.
							if (integer != 0) {
								System.out.println("asd");
								setNeedInvalidating();
//								messagesListProvider= getMessageInverseRequestPagingProvider();
//								mAdapter
							}
						}

						@Override
						public void onRequestFailure(SpiceException spiceException, RequestAndTaskExecutor executor) {

						}
					},
					listenerWithProgressBar));
		}
	}

}