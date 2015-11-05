package ru.touchin.vkchat.views;


import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.zuzuk.ui.views.ReenterableSimpleDraweeView;
import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.models.Attachment;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.models.attachments.AttachmentsType;
import ru.touchin.vkchat.models.attachments.PhotoAttachment;

public class MessageLeft implements MessageItem {

	private final Message mMessage;

	public MessageLeft(final Message message) {
		this.mMessage = message;
	}

	@Override
	public void drawItem(View view) {
		view.findViewById(R.id.message_layout_left).setVisibility(View.VISIBLE);
		view.findViewById(R.id.message_layout_right).setVisibility(View.GONE);
		LinearLayout leftLayout = (LinearLayout) view.findViewById(R.id.message_layout_photos_left);
		leftLayout.setVisibility(View.GONE);
		view.findViewById(R.id.message_layout_photos_right).setVisibility(View.GONE);
		((TextView) view.findViewById(R.id.message_body_left)).setText(mMessage.getBody());

		Date date = new Date(mMessage.getDate() * 1000);
		String dateString = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);

		((TextView) view.findViewById(R.id.message_date_left)).setText(dateString);

		if (!ObjectFromJson.isNull(mMessage.getAttachments()) && mMessage.getAttachments().size() != 0) {
			List<PhotoAttachment> photoAttachments = new ArrayList<>();

			for (Attachment attachment : mMessage.getAttachments()) {
				if (attachment.getType().equals(AttachmentsType.PHOTO)) {
					photoAttachments.add(attachment.getPhoto());
				}
			}
			if (photoAttachments.size() != 0) {
				leftLayout.setVisibility(View.VISIBLE);
				if (leftLayout.getChildCount() != photoAttachments.size()) {
					for (PhotoAttachment photoAttachment : photoAttachments) {
						ReenterableSimpleDraweeView draweeView = new ReenterableSimpleDraweeView(VKChatApp.getInstance());
						draweeView.setUrl(photoAttachment.getPhotoUrl());
						LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(480, 480);
						draweeView.setLayoutParams(params);
						leftLayout.addView(draweeView);
					}
				}
			}
		}
	}
}
