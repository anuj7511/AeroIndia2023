package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_selected_exhibitor.*
import kotlinx.android.synthetic.main.activity_user_notification.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.Notifications.NotificationContentLanguages
import universal.appfactory.aeroindia2023.Notifications.NotificationHeaderLanguages
import universal.appfactory.aeroindia2023.Notifications.NotificationRequestModel
import universal.appfactory.aeroindia2023.Notifications.NotificationResponseModel

class UserNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notification)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        findViewById<Button>(R.id.sendNotification).setOnClickListener {
            val header = NotificationHeaderLanguages()
            val body = NotificationContentLanguages()

            header.en = findViewById<EditText>(R.id.notificationHeading).text.toString()
            body.en = findViewById<EditText>(R.id.notificationBody).text.toString()

            sendNotification(header, body)
        }

    }

    private fun sendNotification(header: NotificationHeaderLanguages, body: NotificationContentLanguages){

        val notificationRequestModel = NotificationRequestModel(headings = header, contents = body)

        //Accessing API Interface for sending notification
        val response = ServiceBuilder.buildNotificationService(ApiInterface::class.java)

        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch(Dispatchers.IO) {
            response.sendNotification(notificationRequestModel,"Basic YTU1MjA3MjgtMTYxZi00MTFjLThhNzQtYmI4ZDEzOTEyMDgx", "application/json").enqueue(
                object : Callback<NotificationResponseModel> {
                    override fun onResponse(
                        call: Call<NotificationResponseModel>,
                        response: Response<NotificationResponseModel>
                    ) {
                        val id = response.body()?.id.toString()
                        val recipient = response.body()?.recipients.toString()

                        Toast.makeText(this@UserNotificationActivity, "Message sent successfully", Toast.LENGTH_SHORT).show()

                        Log.i("Notification response", "ID: $id, Recipients: $recipient")
                    }

                    override fun onFailure(call: Call<NotificationResponseModel>, text: Throwable) {
                        Toast.makeText(this@UserNotificationActivity, "Error", Toast.LENGTH_LONG)
                            .show()
                        Log.i("Notification failure", text.toString())
                    }
                }
            )
        }

    }

}