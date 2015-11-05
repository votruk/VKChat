package ru.touchin.vkchat.models.attachments;


import com.google.api.client.util.Key;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class PhotoAttachment extends ObjectFromJson {
	@Key("id")
	private Integer photoId;

	@Key("album_id")
	private Integer albumId;

	@Key("owner_id")
	private Long ownerId;

	@Key("text")
	private String text;

	@Key("width")
	private Integer width;

	@Key("height")
	private Integer height;

	@Key("date")
	private Long date;

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

	public Integer getPhotoId() {
		return photoId;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public String getText() {
		return text;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public Long getDate() {
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

	public String getPhotoUrl() {
		if (StringUtils.isNotBlank(photo807)) {
			return photo807;
		} else if (StringUtils.isNotBlank(photo604)) {
			return photo604;
		}else if (StringUtils.isNotBlank(photo130)) {
			return photo130;
		} else {
			return photo75;
		}
	}
}
