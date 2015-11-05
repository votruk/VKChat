package ru.touchin.vkchat.views;


import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.models.Message;

public class MessageLeft implements MessageItem {

	private final Message mMessage;

	public MessageLeft(final Message message) {
		this.mMessage = message;
	}

	@Override
	public void drawItem(View view) {
		view.findViewById(R.id.message_layout_left).setVisibility(View.VISIBLE);
		view.findViewById(R.id.message_layout_right).setVisibility(View.GONE);
		((TextView) view.findViewById(R.id.message_body_left)).setText(mMessage.getBody());

		Date date = new Date(mMessage.getDate() * 1000);
		String dateString = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);

		((TextView) view.findViewById(R.id.message_date_left)).setText(dateString);
	}
}
