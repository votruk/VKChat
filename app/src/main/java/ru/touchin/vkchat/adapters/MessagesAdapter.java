package ru.touchin.vkchat.adapters;

import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.zuzuk.ui.adapters.AbstractPagerAdapterWithProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.providers.InverseRequestPagingProvider;
import ru.touchin.vkchat.views.MessageItem;

public class MessagesAdapter extends AbstractPagerAdapterWithProgressBar<MessageItem, InverseRequestPagingProvider<MessageItem>> {

    @Override
    public View newRealView(int position, LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.message_item, container, false);
    }

    @Override
    public void bindRealView(View view, MessageItem messageItem, int position) {
        messageItem.drawItem(view);
    }

}