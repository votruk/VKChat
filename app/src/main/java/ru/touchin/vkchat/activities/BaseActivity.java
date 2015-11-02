package ru.touchin.vkchat.activities;

import android.widget.Toast;

import org.zuzuk.ui.activities.AbstractExecutorActivity;
import org.zuzuk.utils.log.Lc;

import java.util.List;

import ru.touchin.vkchat.providers.RequestFailListener;

//@BroadcastEvents({@EventAnnotation(BeaconLogicHandler.ON_ENTRY),
//        @EventAnnotation(BeaconLogicHandler.ON_EXIT),
//        @EventAnnotation(BeaconLogicHandler.DID_ENTER),
//        @EventAnnotation(BeaconLogicHandler.DID_EXIT),
//        @EventAnnotation(GcmNotificationService.CONTENT_AVAILABLE),
//        @EventAnnotation(BeaconService.EVENT_NOTIFICATION_ACTION)})
public class BaseActivity extends AbstractExecutorActivity implements RequestFailListener {

    @Override
    public void onRequestFailure(final List<Exception> exceptionList) {
        Exception error = exceptionList.get(0);

        if (error.getCause() instanceof Exception) {
            error = (Exception) error.getCause();
        }
        Lc.e(error.getMessage());

        final String msg = error.getMessage();

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}