package ru.touchin.vkchat.views;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.ui.views.ReenterableSimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.models.Attachment;
import ru.touchin.vkchat.models.Message;

public class MessageRight implements MessageItem {

	private final Message mMessage;

	public MessageRight(final Message message) {
		this.mMessage = message;
	}

	@Override
	public void drawItem(View view) {
		view.findViewById(R.id.message_layout_left).setVisibility(View.GONE);
		view.findViewById(R.id.message_layout_right).setVisibility(View.VISIBLE);
		view.findViewById(R.id.message_layout_photos_left).setVisibility(View.GONE);
		view.findViewById(R.id.message_layout_photos_right).setVisibility(View.GONE);
		((TextView) view.findViewById(R.id.message_body_right)).setText(mMessage.getBody());

		Date date = new Date(mMessage.getDate() * 1000);
		String dateString = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);

		((TextView) view.findViewById(R.id.message_date_right)).setText(dateString);

		if (mMessage.getAttachments() != null && mMessage.getAttachments().size() != 0) {
			for (Attachment attachment : mMessage.getAttachments()) {
				if (attachment.getType().equals("photo")) {
					view.findViewById(R.id.message_layout_photos_right).setVisibility(View.VISIBLE);

					ReenterableSimpleDraweeView draweeView = new ReenterableSimpleDraweeView(VKChatApp.getInstance());
					draweeView.setUrl(attachment.getPhoto().getPhoto604());
					draweeView.setMinimumHeight(240);
					draweeView.setMaxHeight(240);
					draweeView.setMinimumWidth(240);
					draweeView.setMaxWidth(240);

					((LinearLayout)view.findViewById(R.id.message_layout_photos_right)).addView(draweeView);
				}
			}
		}
	}
}
