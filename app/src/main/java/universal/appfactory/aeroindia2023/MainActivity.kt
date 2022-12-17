package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import universal.appfactory.aeroindia2023.R
import kotlin.concurrent.timerTask


// Main Activity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        // Displays intro layout or image
        setContentView(R.layout.intro_layout)

        // Timer to display the intro image
        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            // Displays the Register activity (Main activity)
            override fun onFinish() {
                setContentView(R.layout.activity_main)
                val button_id = findViewById<Button>(R.id.emailButton)

                button_id.setOnClickListener{
                    val email = findViewById<EditText>(R.id.emailAddress).text.toString()
                    val username = findViewById<EditText>(R.id.username).text.toString()
                    val mobileNo = findViewById<EditText>(R.id.mobileNumber).text.toString()

                    Log.i("Unshared user information", "Name: $username\nMobile number: $mobileNo\nEmail: $email")

                    val intent = Intent(this@MainActivity, OtpActivity::class.java)

                    intent.putExtra("email", email)
                    intent.putExtra("username", username)
                    intent.putExtra("mobileNo", mobileNo)
                    startActivity(intent)
                }
            }
        }
        timer.start()
    }
}