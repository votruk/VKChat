package ru.touchin.vkchat.adapters;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.models.DialogItem;
import ru.touchin.vkchat.views.RoundedCornersImageView;

public class FriendsAdapter extends BaseAdapter<DialogItem> {

    private static final int EMPTY_ITEM = 0;
    private static final int ITEM = 1;

    @Override
    public boolean isEnabled(int position) {
        return get(position) != null;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return get(position) == null ? EMPTY_ITEM : ITEM;
    }

    @Override
    public int getResourceId() {
        return R.layout.chats_list;
    }

    @Override
    public void fillItem(View view, DialogItem dialogItem) {
        RoundedCornersImageView profileImage = (RoundedCornersImageView) view.findViewById(R.id.profile_image_normal);
//        Uri uri = Uri.parse(dialogItem.getUser().getNormalSizeImageURL());
//        profileImage.setImageURI(uri);

//        ((TextView) view.findViewById(R.id.user_name_item)).setText(dialogItem.getUser().getName());
//        ((TextView) view.findViewById(R.id.tweet_item)).setText(dialogItem.toString());
    }
}