package ru.touchin.vkchat.views;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.ui.views.ReenterableSimpleDraweeView;
import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.Settings;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.activities.MainActivity;
import ru.touchin.vkchat.models.Attachment;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.models.attachments.AttachmentsType;
import ru.touchin.vkchat.models.attachments.PhotoAttachment;

public class OutcomeMessage implements MessageItem {

	private final Message mMessage;

	public OutcomeMessage(final Message message) {
		this.mMessage = message;
	}

	@Override
	public void drawItem(View view) {

		view.findViewById(R.id.message_layout_left).setVisibility(View.GONE);
		view.findViewById(R.id.message_layout_right).setVisibility(View.VISIBLE);
		view.findViewById(R.id.message_layout_photos_left).setVisibility(View.GONE);
		LinearLayout rightLayout = (LinearLayout) view.findViewById(R.id.message_layout_photos_right);
		rightLayout.setVisibility(View.GONE);
		((TextView) view.findViewById(R.id.message_body_right)).setText(mMessage.getBody());

		Date date = new Date(mMessage.getDate() * 1000);
		String dateString = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);

		((TextView) view.findViewById(R.id.message_date_right)).setText(dateString);

		if (!ObjectFromJson.isNull(mMessage.getAttachments()) && mMessage.getAttachments().size() != 0) {
			List<PhotoAttachment> photoAttachments = new ArrayList<>();

			for (Attachment attachment : mMessage.getAttachments()) {
				if (attachment.getType().equals(AttachmentsType.PHOTO)) {
					photoAttachments.add(attachment.getPhoto());
				}
			}
			if (photoAttachments.size() != 0) {
				rightLayout.setVisibility(View.VISIBLE);
				if (rightLayout.getChildCount() == 0) {
					final int width = Settings.PHOTO_WIDTH.get(VKChatApp.getInstance());
					for (PhotoAttachment photoAttachment : photoAttachments) {
						ReenterableSimpleDraweeView draweeView = new ReenterableSimpleDraweeView(VKChatApp.getInstance());
						draweeView.setUrl(photoAttachment.getPhotoUrl());

						LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
						draweeView.setLayoutParams(params);
						draweeView.requestLayout();
						rightLayout.addView(draweeView);
						rightLayout.requestLayout();
					}
				}
			} else {
				if (StringUtils.isNotEmpty(mMessage.getBody())) {
					view.findViewById(R.id.message_layout_right).setVisibility(View.GONE);

				}
			}
		}
	}
}
