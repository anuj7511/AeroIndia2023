package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import universal.appfactory.aeroindia2023.R



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
                val buttonId = findViewById<Button>(R.id.emailButton)

                buttonId.setOnClickListener{
                    val email = findViewById<EditText>(R.id.emailAddress).text.toString()
                    val username = findViewById<EditText>(R.id.username).text.toString()
                    val mobileNo = findViewById<EditText>(R.id.mobileNumber).text.toString()

                    Log.i("Unshared user information", "Name: $username\nMobile number: $mobileNo\nEmail: $email")

                    var bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("username", username)
                    bundle.putString("mobileNo", mobileNo)

                    val intent = Intent(this@MainActivity, OtpActivity::class.java)

                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
        }
        timer.start()
    }
}