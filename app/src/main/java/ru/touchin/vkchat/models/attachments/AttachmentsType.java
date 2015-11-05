package ru.touchin.vkchat.models.attachments;


import com.google.api.client.util.Value;

import org.apache.commons.lang3.StringUtils;

public enum AttachmentsType {
    @Value("photo")
    PHOTO,

    @Value("audio")
    AUDIO,

    @Value("video")
    VIDEO,

    @Value("doc")
    DOC,

    @Value("wall")
    WALL,

    @Value("wall_reply")
    WALL_REPLY,

    @Value("sticker")
    STICKER;

}
