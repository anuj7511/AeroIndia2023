package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// API_1: http://aeroindia.gov.in/api/register-user
// API_2: http://aeroindia.gov.in/api/register-verify
// Bearer token: 61b25a411a2dad66bb7b6ff145db3c2f

class UserLoginActivity : AppCompatActivity() {

    var backpress: Int = 0
    private var navigableBundle = Bundle()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        val loginButtonId = findViewById<Button>(R.id.loginButton)
        val signUpButtonId = findViewById<Button>(R.id.signUpButton)
        val emailEditViewId = findViewById<EditText>(R.id.emailAddress)

        var intent = Intent(this@UserLoginActivity, DummyActivity::class.java)

        val sharedPreferences: SharedPreferences = getSharedPreferences("LocalUserData", MODE_APPEND)

        Log.i("Login Activity Msg", "Name: "+sharedPreferences.getString("name", "Data Not Stored").toString())

        if(sharedPreferences.getBoolean("loginStatus", false)) {
            intent = Intent(this@UserLoginActivity, HomepageActivity::class.java)

            navigableBundle.putString(sharedPreferences.getString("name", "NA").toString(), "NA")
            navigableBundle.putString(sharedPreferences.getString("email", "NA").toString(), "NA")
            navigableBundle.putString(sharedPreferences.getString("phoneNo", "NA").toString(), "NA")
            navigableBundle.putString(sharedPreferences.getString("designation", "NA").toString(), "NA")
            navigableBundle.putString(sharedPreferences.getString("userId", "NA").toString(), "NA")
            navigableBundle.putString(sharedPreferences.getString("userType", "NA").toString(), "NA")
            navigableBundle.putString(sharedPreferences.getString("type", "-1").toString(), "NA")

            intent.putExtras(navigableBundle)
            backpress=0
            startActivity(intent)
        }

        else {
            signUpButtonId.setOnClickListener {
                intent = Intent(this@UserLoginActivity, UserRegistrationActivity::class.java)
                navigableBundle.putString("type", "2")
                intent.putExtras(navigableBundle)
                backpress=0
                startActivity(intent)
            }

            loginButtonId.setOnClickListener {
                val email = emailEditViewId.text.toString()
                if(!email.equals("")) {
                    Log.i("Email", email)
                    navigableBundle.putString("type", "1")

                    submitUserLoginData(email)
                }
                else{
                    Toast.makeText(this@UserLoginActivity, "Fill all columns", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun submitUserLoginData(email: String){
        val userLoginDataRequestModel = UserLoginDataRequestModel(email)

        //Accessing API Interface for verifying user email ID
        val response = ServiceBuilder.buildService(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            response.verifyUserLogin(userLoginDataRequestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                object : Callback<UserLoginDataResponseModel> {
                    override fun onResponse(
                        call: Call<UserLoginDataResponseModel>,
                        response: Response<UserLoginDataResponseModel>
                    ) {
                        // User data
                        val status = response.body()?.status.toString()
                        val verificationCode = response.body()?.message?.verification_code.toString()
                        val userId = response.body()?.message?.user_id.toString()
                        val name = response.body()?.message?.data?.name.toString()
                        val email = response.body()?.message?.data?.email_id.toString()
                        val phoneNo = response.body()?.message?.data?.phone_no.toString()
                        val id = response.body()?.message?.data?.id.toString()
                        val pin = response.body()?.message?.data?.pin.toString()
                        val userType = response.body()?.message?.data?.user_type.toString()

                        // Login errors
                        val emailError = response.body()?.errors?.email_id.toString()

                        Log.i("Verification code", verificationCode)

                        if(status == "fail"){
                            Toast.makeText(this@UserLoginActivity, "Status: $status", Toast.LENGTH_SHORT).show()
                            Log.i("Login Activity email error", emailError)
                        }
                        else{
                            navigableBundle.putString("userId", userId)
                            navigableBundle.putString("name", name)
                            navigableBundle.putString("email", email)
                            navigableBundle.putString("phoneNo", phoneNo)
                            navigableBundle.putString("id", id)
                            navigableBundle.putString("pin", pin)
                            navigableBundle.putString("userType", userType)

                            Log.i("Generated OTP", "PIN: $pin")

                            val navigateIntent = Intent(this@UserLoginActivity, OtpActivity::class.java)
                            navigateIntent.putExtras(navigableBundle)
                            backpress=0
                            startActivity(navigateIntent)
                            this@UserLoginActivity.finish()
                        }

                    }

                    override fun onFailure(call: Call<UserLoginDataResponseModel>, text: Throwable) {
                        Toast.makeText(this@UserLoginActivity, "Error", Toast.LENGTH_LONG)
                            .show()
                        Log.i("User Login failure", text.toString())
                    }
                }
            )
        }

    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    override fun onBackPressed(){
        backpress = backpress + 1
        if(backpress > 1){
            finishAffinity()
        }
        else{
            Toast.makeText(this, "Press back once again to exit", Toast.LENGTH_SHORT).show()
        }
    }

}
