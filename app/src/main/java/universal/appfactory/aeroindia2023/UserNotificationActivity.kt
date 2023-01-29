package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_selected_exhibitor.*
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

        val notificationHeading = findViewById<EditText>(R.id.notificationHeading).text.toString()
        val notificationBody = findViewById<EditText>(R.id.notificationBody).text.toString()

        findViewById<Button>(R.id.sendNotification).setOnClickListener {
            sendNotification(notificationHeading, notificationBody)
        }

    }

    fun sendNotification(heading: String, body: String){

        NotificationHeaderLanguages().setHeader(heading)
        NotificationContentLanguages().setMessage(body)

        val notificationRequestModel = NotificationRequestModel()

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