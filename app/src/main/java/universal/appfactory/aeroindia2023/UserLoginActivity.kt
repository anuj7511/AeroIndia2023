package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
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

    private var navigableBundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val loginButtonId = findViewById<Button>(R.id.loginButton)
        val signUpButtonId = findViewById<Button>(R.id.signUpButton)
        val emailEditViewId = findViewById<EditText>(R.id.emailAddress)

        signUpButtonId.setOnClickListener{
            val intent = Intent(this@UserLoginActivity, UserRegistrationActivity::class.java)
            navigableBundle.putString("type", "2")
            intent.putExtras(navigableBundle)
            startActivity(intent)
        }

        loginButtonId.setOnClickListener {
            val email = emailEditViewId.text.toString()
            Log.i("Email", email)
            navigableBundle.putString("type", "1")

            submitUserLoginData(email)
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
                        val status = response.body()?.status.toString()
                        val verificationCode = response.body()?.message?.verification_code.toString()
                        val userId = response.body()?.message?.user_id.toString()
                        val name = response.body()?.message?.data?.name.toString()
                        val email = response.body()?.message?.data?.email_id.toString()
                        val phoneNo = response.body()?.message?.data?.phone_no.toString()
                        val id = response.body()?.message?.data?.id.toString()
                        val pin = response.body()?.message?.data?.pin.toString()
                        val userType = response.body()?.message?.data?.user_type.toString()

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
                            startActivity(navigateIntent)
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

}
