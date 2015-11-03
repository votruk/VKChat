package ru.touchin.vkchat.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.ui.adapters.AbstractPagerAdapterWithProgressBar;
import org.zuzuk.ui.views.ReenterableSimpleDraweeView;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.models.Message;

public class MessagesAdapter extends AbstractPagerAdapterWithProgressBar<Message, RequestPagingProvider<Message>> {


    @Override
    public View newRealView(int position, LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.messages_list, container, false);
    }

    @Override
    public void bindRealView(View view, Message message, int position) {

        ((TextView) view.findViewById(R.id.message_body)).setText(Html.fromHtml(message.getBody()));
    }

}