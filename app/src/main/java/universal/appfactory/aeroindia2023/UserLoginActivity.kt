package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val bundle = Bundle()
        var intent = Intent(this@UserLoginActivity, OtpActivity::class.java)

        val loginButtonId = findViewById<Button>(R.id.loginButton)
        val signUpButtonId = findViewById<Button>(R.id.signUpButton)

        // Required for .setSelection()
        val emailRefId = findViewById<EditText>(R.id.emailAddress)

        loginButtonId.setOnClickListener {
            val email = emailRefId.text.toString()
            bundle.putString("email", email)
            intent.putExtras(bundle)

            //TODO: Check for existing user and OTP generation
            startActivity(intent)
        }

        signUpButtonId.setOnClickListener{
            intent = Intent(this@UserLoginActivity, UserRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}