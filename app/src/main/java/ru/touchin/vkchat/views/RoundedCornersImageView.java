package ru.touchin.vkchat.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;

public class RoundedCornersImageView extends SimpleDraweeView {

    private String url;

    public RoundedCornersImageView(Context context) {
        super(context);
    }

    public RoundedCornersImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedCornersImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setUrl(String url) {
        if(url == null){
            url = "";    
        }
        if(!url.equals(this.url)){
            this.url = url;
            setImageURI(Uri.parse(url));
        }
    }
}
