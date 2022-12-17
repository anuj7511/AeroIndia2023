package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import universal.appfactory.aeroindia2023.R

class OtpActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val sharedEmailID: String =  intent.getStringExtra("email").toString()
        val sharedUsername: String = intent.getStringExtra("username").toString()
        val sharedMobileNo: String = intent.getStringExtra("mobileNo").toString()

        // Userinfo echoed in logcat for reference
        Log.i("Shared user information", "Name: $sharedUsername\nMobile number: $sharedMobileNo\nEmail: $sharedEmailID")

        findViewById<TextView>(R.id.userInfo).text = " Name: $sharedUsername\nEmail: $sharedEmailID"

        // TODO: OTP generation & validation is required using API
        Toast.makeText(this, "OTP Generated", Toast.LENGTH_LONG).show()
    }
}