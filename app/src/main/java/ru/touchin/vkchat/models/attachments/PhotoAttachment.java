package ru.touchin.vkchat.models.attachments;


import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class PhotoAttachment extends ObjectFromJson {
	@Key("id")
	private int photoId;

	@Key("album_id")
	private int albumId;

	@Key("owner_id")
	private long ownerId;

	@Key("text")
	private String text;

	@Key("width")
	private int width;

	@Key("height")
	private int height;

	@Key("date")
	private long date;

	@Key("access_key")
	private String accessKey;

	@Key("photo_75")
	private String photo75;

	@Key("photo_130")
	private String photo130;

	@Key("photo_604")
	private String photo604;

	@Key("photo_807")
	private String photo807;

	@Key("photo_1280")
	private String photo1280;

	@Key("photo_2560")
	private String photo2560;

	public int getPhotoId() {
		return photoId;
	}

	public int getAlbumId() {
		return albumId;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public String getText() {
		return text;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public long getDate() {
		return date;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getPhoto75() {
		return photo75;
	}

	public String getPhoto130() {
		return photo130;
	}

	public String getPhoto604() {
		return photo604;
	}

	public String getPhoto807() {
		return photo807;
	}

	public String getPhoto1280() {
		return photo1280;
	}

	public String getPhoto2560() {
		return photo2560;
	}
}
