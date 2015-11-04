package ru.touchin.vkchat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.ui.adapters.AbstractPagerAdapterWithProgressBar;
import org.zuzuk.ui.views.ReenterableSimpleDraweeView;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.models.Friend;

public class FriendsAdapter extends AbstractPagerAdapterWithProgressBar<Friend, RequestPagingProvider<Friend>> {


    @Override
    public View newRealView(int position, LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.friends_list, container, false);
    }

    @Override
    public void bindRealView(View view, Friend friend, int position) {
        ReenterableSimpleDraweeView profileImage = (ReenterableSimpleDraweeView) view.findViewById(R.id.profile_image_normal);
        profileImage.setUrl(friend.getPhotoUrl());

        ((TextView) view.findViewById(R.id.friend_name)).setText(friend.getFullName());
    }
}