package ru.touchin.vkchat.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.zuzuk.providers.ListProvider;
import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.ui.adapters.AbstractPagerAdapterWithProgressBar;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.views.RoundedCornersImageView;

public class FriendsAdapter extends AbstractPagerAdapterWithProgressBar<Friend, RequestPagingProvider<Friend>> {


    @Override
    public View newRealView(int position, LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_friends_list, container, false);
    }

    @Override
    public void bindRealView(View view, Friend friend, int position) {
        RoundedCornersImageView profileImage = (RoundedCornersImageView) view.findViewById(R.id.profile_image_normal);
        Uri uri = Uri.parse(friend.getPhotoUrl());
        profileImage.setImageURI(uri);

        ((TextView) view.findViewById(R.id.friend_name)).setText(friend.getFullName());
    }
}