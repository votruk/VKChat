package ru.touchin.vkchat.views;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;

import org.zuzuk.ui.views.ReenterableSimpleDraweeView;


public class PhotoDrawee extends ReenterableSimpleDraweeView {
	public PhotoDrawee(Context context, String url) {
		super(context);
		setUrl(url);

		GenericDraweeHierarchy hierarchy = this.getHierarchy();
		hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

//		RoundingParams roundingParams = hierarchy.getRoundingParams();
//		roundingParams.setCornersRadius(10);
//		hierarchy.setRoundingParams(roundingParams);

		this.setMaxWidth(120);
		this.setMaxHeight(120);
		this.setMinimumHeight(120);
		this.setMinimumWidth(120);

//		this.setAspectRatio(1.0f);
	}


}
