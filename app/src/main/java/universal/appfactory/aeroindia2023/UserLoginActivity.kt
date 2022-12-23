package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.coroutines.currentCoroutineContext
import universal.appfactory.aeroindia2023.databinding.ActivityUserLoginBinding

// API_1: http://aeroindia.gov.in/api/register-user
// API_2: http://aeroindia.gov.in/api/register-verify
// Bearer token: 61b25a411a2dad66bb7b6ff145db3c2f

class UserLoginActivity : AppCompatActivity() {
    private var binding: ActivityUserLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        // ArrayAdapter for user types
        val user_types = resources.getStringArray(R.array.user_types)
        val arrayAdapter = ArrayAdapter(this@UserLoginActivity, R.layout.user_type_dropdown_menu, user_types)

        findViewById<AutoCompleteTextView>(R.id.userTypeTextView).setAdapter(arrayAdapter)

        val navigateBundle = Bundle()
        var intent = Intent(this@UserLoginActivity, OtpActivity::class.java)

        val loginButtonId = findViewById<Button>(R.id.loginButton)
        val signUpButtonId = findViewById<Button>(R.id.signUpButton)

        // Required for .setSelection()
        val emailRefId = findViewById<EditText>(R.id.emailAddress)

        loginButtonId.setOnClickListener {
            val email = emailRefId.text.toString()
            navigateBundle.putString("email", email)
            navigateBundle.putString("type", "1")

            intent.putExtras(navigateBundle)

            //TODO: Check for existing user and OTP generation
            startActivity(intent)
        }

        signUpButtonId.setOnClickListener{
            intent = Intent(this@UserLoginActivity, UserRegistrationActivity::class.java)
            navigateBundle.putString("type", "2")
            startActivity(intent)
        }

    }
}