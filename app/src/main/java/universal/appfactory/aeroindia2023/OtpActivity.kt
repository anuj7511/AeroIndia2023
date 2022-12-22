package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.databinding.ActivityFeedbackBinding
import universal.appfactory.aeroindia2023.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val bundle = intent.extras

        val sharedEmailID: String =  bundle?.getString("email", "Default email").toString()
        val sharedUsername: String = bundle?.getString("username", "Default Name").toString()
        val sharedMobileNo: String = bundle?.getString("mobileNo", "0000").toString()

        //TODO: API needs to be invoked for registering user data


        // Userinfo echoed in logcat for reference
        Log.i("Shared user information", "Name: $sharedUsername\n Mobile number: $sharedMobileNo\nEmail: $sharedEmailID")

        findViewById<TextView>(R.id.userInfo).text = " Name: $sharedUsername\n Email: $sharedEmailID"
        findViewById<TextView>(R.id.otpMessage1).text = "OTP VERIFICATION"
        findViewById<TextView>(R.id.otpMessage2).text = "Enter the 4 digit One Time Password (OTP) you have received in your registered email"

        val otpButton = findViewById<Button>(R.id.otpButton)

        // TODO: OTP generation & validation is required using API

        // Homepage activity is popped after OTP validation
        otpButton.setOnClickListener {
            val OTP = findViewById<EditText>(R.id.otp).text.toString()

            //TODO: OTP Validation using API
            if(OTP.length == 4) {

                //TODO: OTP Comparison
                val intent = Intent(this@OtpActivity, HomepageActivity::class.java)
                intent.putExtras(bundle!!)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Enter 4 digit correct OTP", Toast.LENGTH_LONG).show()
            }
        }

    }
}