package ru.touchin.vkchat.adapters;

import android.graphics.Color;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.ui.adapters.AbstractPagerAdapterWithProgressBar;
import org.zuzuk.ui.views.ReenterableSimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.touchin.vkchat.R;
import ru.touchin.vkchat.VKChatApp;
import ru.touchin.vkchat.models.Friend;
import ru.touchin.vkchat.models.Message;
import ru.touchin.vkchat.providers.InverseRequestPagingProvider;

public class MessagesAdapter extends AbstractPagerAdapterWithProgressBar<Message, InverseRequestPagingProvider<Message>> {
    private int screenWidth;
    private float screenDencity;
    private int bigPadding;
    private int smallPadding = 8;


    public MessagesAdapter(int screenWidth, float screenDensity) {
        this.screenWidth = screenWidth;
        this.screenDencity = screenDensity;

        bigPadding = (int) (screenWidth * 0.4);
    }


    @Override
    public View newRealView(int position, LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.messages_list, container, false);
    }

    @Override
    public void bindRealView(View view, Message message, int position) {
        TextView body = (TextView) view.findViewById(R.id.message_body);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.one_message_layout);

        Date date = new Date(message.getDate() * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String dateString = sdf.format(date);
        TextView dateTV = (TextView) view.findViewById(R.id.message_date);
        dateTV.setText(dateString);

        body.setText(Html.fromHtml(message.getBody()));
        if (message.getIsMessageMine() == 1) {


            body.setBackgroundResource(R.drawable.my_message2);
            layout.setPadding((int) (bigPadding * screenDencity), (int) (smallPadding * screenDencity),
                    (int) (smallPadding * screenDencity), (int) (smallPadding * screenDencity));
            body.setTextColor(Color.WHITE);
        } else {
            body.setBackgroundResource(R.drawable.not_my);
            layout.setPadding((int) (smallPadding * screenDencity), (int) (smallPadding * screenDencity),
                    (int) (bigPadding * screenDencity), (int) (smallPadding * screenDencity));
            dateTV.setGravity(Gravity.RIGHT);
            body.setTextColor(Color.BLACK);
        }


    }


}