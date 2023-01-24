package universal.appfactory.aeroindia2023;

import android.content.Context;
import android.util.Log;
import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OneSignal;

import org.json.JSONObject;

class ExampleNotificationReceivedHandler implements OneSignal.OSRemoteNotificationReceivedHandler {

    @Override
    public void remoteNotificationReceived(Context context, OSNotificationReceivedEvent osNotificationReceivedEvent) {
        JSONObject data = osNotificationReceivedEvent.getNotification().getAdditionalData();
        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            Log.i("JSON String", data.toString());
            Log.i("OneSignalExample", "custom key set with value: " + customKey);
        }
        else{
            Log.i("OneSignalExample", "No message passed");
        }
    }
}
