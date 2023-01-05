package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_profile_info.*
import kotlinx.android.synthetic.main.zonal_manager_user_card.*
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
    var navigableBundle = Bundle()

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

        val sharedPreferences: SharedPreferences = getSharedPreferences("LocalUserData", MODE_PRIVATE)

        Log.i("Status of cache", sharedPreferences.getBoolean("loginStatus", false).toString())
        Log.i("Username", sharedPreferences.getString("name", "N/A").toString())
        Log.i("Designation", sharedPreferences.getString("designation", "N/A").toString())
        Log.i("Phone number", sharedPreferences.getString("phoneNo", "N/A").toString())

        if(sharedPreferences.getBoolean("loginStatus", false)) {

            navigableBundle.putString("name", sharedPreferences.getString("name", "-"))
            navigableBundle.putString("email", sharedPreferences.getString("email", "-"))
            navigableBundle.putString("phoneNo", sharedPreferences.getString("phoneNo", "-"))
            navigableBundle.putString("designation", sharedPreferences.getString("designation", "-"))
            navigableBundle.putString("userId", sharedPreferences.getString("userId", "-"))
            navigableBundle.putString("foreignKeyId", sharedPreferences.getString("foreignKeyId", "-"))
            navigableBundle.putString("userType", sharedPreferences.getString("userType", "-"))

            val userType: String = sharedPreferences.getString("userType", "-").toString()

            backpress=0
            intent = when(userType){
                "4" -> Intent(this@UserLoginActivity, ManagerHomepageActivity::class.java)
                "5" -> Intent(this@UserLoginActivity, ZonalManagerHomepageActivity::class.java)
                else -> Intent(this@UserLoginActivity, HomepageActivity::class.java)
            }

            intent.putExtras(navigableBundle)
            startActivity(intent)
            this@UserLoginActivity.finishAffinity()
        }

        else {

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

            signUpButtonId.setOnClickListener {
                backpress=0
                intent = Intent(this@UserLoginActivity, UserRegistrationActivity::class.java)
                navigableBundle.putString("type", "2")
                intent.putExtras(navigableBundle)
                startActivity(intent)
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
                        val userId = response.body()?.message?.user_id.toString()
                        val verificationCode = response.body()?.message?.verification_code.toString()

                        // Login errors
                        val emailError = response.body()?.errors?.email_id.toString()

                        Log.i("Verification code", verificationCode)

                        if(status == "fail"){
                            Toast.makeText(this@UserLoginActivity, "Status: $status", Toast.LENGTH_SHORT).show()
                            Log.i("Login Activity email error", emailError)
                        }
                        else{
                            navigableBundle.putString("email", email)
                            navigableBundle.putString("userId", userId)

                            backpress=0
                            val navigateIntent = Intent(this@UserLoginActivity, OtpActivity::class.java)
                            navigateIntent.putExtras(navigableBundle)
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
