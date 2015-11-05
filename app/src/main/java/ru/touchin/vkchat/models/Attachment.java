package ru.touchin.vkchat.models;


import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import ru.touchin.vkchat.models.attachments.AttachmentsType;
import ru.touchin.vkchat.models.attachments.PhotoAttachment;

public class Attachment extends ObjectFromJson {
	@Key("type")
	private AttachmentsType type;

	@Key("photo")
	private PhotoAttachment photo;

	public AttachmentsType getType() {
		return type;
	}

	public PhotoAttachment getPhoto() {
		return photo;
	}
}
