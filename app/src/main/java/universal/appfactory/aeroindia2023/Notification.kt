package universal.appfactory.aeroindia2023

import android.app.Application
import android.util.Log
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "1c53bac3-8c5c-4c3a-9cd2-38558dced0cf"

class Notification: Application() {
    override fun onCreate() {
        super.onCreate()

        Log.i("One Signal Activity", "One Signal notification class activated")
        
        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}