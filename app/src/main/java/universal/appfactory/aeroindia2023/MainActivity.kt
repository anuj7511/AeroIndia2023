package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import universal.appfactory.aeroindia2023.R
import kotlin.concurrent.timerTask

// Main Activity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.intro_layout)
        val timer = object: CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() { setContentView(R.layout.activity_main) }
        }
        timer.start()
    }

    fun submitEmail(view: View){
        val email = findViewById<EditText>(R.id.emailAddress).text
        val username = findViewById<EditText>(R.id.username).text
        val mobileNo = findViewById<EditText>(R.id.mobileNumber).text



        // TODO: OTP activity and API will be invoked to validate the given user ID using OTP
        val intent = Intent(applicationContext, OtpActivity::class.java)
        intent.putExtra("email", email)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        this.startActivity(intent)

        // TODO: New Activity invoked after validation

        // Sample code-chunk to test for input
        Log.i("User Info", "Name: $username\nMobile number: $mobileNo\nUsername: $username")

    }
}