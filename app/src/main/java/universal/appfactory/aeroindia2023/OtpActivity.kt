package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import universal.appfactory.aeroindia2023.R

class OtpActivity : AppCompatActivity() {

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val emailIDShared1: String = intent?.getStringExtra("email").toString()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val sharedEmailID: String = intent?.getStringExtra("email").toString()
        findViewById<TextView>(R.id.userInfo).text = "Email: $sharedEmailID"

        // TODO: OTP validation is required using API
        Toast.makeText(this, "OTP Generated", Toast.LENGTH_LONG).show()
    }
}