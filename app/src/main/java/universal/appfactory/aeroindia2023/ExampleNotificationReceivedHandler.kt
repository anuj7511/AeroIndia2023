import android.app.Application
import android.util.Log
import com.onesignal.OSNotification
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal


internal class OSNotificationReceivedHandler(

    private val application: Application

) : OneSignal.OSNotificationWillShowInForegroundHandler {


    companion object {
        private const val TAG = "OneSignalReceivedHandler"
    }


    fun notificationReceived(notification: OSNotification) {

        val data = notification.additionalData

        val notificationID = notification.notificationId
        val title = notification.title
        val body = notification.body
        // ...

        Log.i(TAG, "NotificationID received: $notificationID")
        Log.i("Message title", title.toString())

    }

    override fun notificationWillShowInForeground(event: OSNotificationReceivedEvent?) {
        OneSignal.onesignalLog(
            OneSignal.LOG_LEVEL.VERBOSE, "NotificationWillShowInForegroundHandler fired!" +
                    " with notification event: " + event.toString()
        )

        val notification: OSNotification = event!!.notification
        val data = notification.additionalData

        Log.i("JSON data", data.toString())

        // Complete with null means don't show a notification.
        event.complete(notification)

    }

}