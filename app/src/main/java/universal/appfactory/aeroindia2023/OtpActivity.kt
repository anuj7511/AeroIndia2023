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

class OtpActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val navigationBundle = intent.extras

        val sharedEmailID: String =  navigationBundle?.getString("email", "DEFAULT_EMAIL").toString()
        val sharedUsername: String = navigationBundle?.getString("username", "DEFAULT_NAME").toString()
        val sharedMobileNo: String = navigationBundle?.getString("mobileNo", "-1").toString()
        val userId: String = navigationBundle?.getString("userId", "-1").toString()
        val verify: String = navigationBundle?.getString("verify", "-1").toString()
        val type: String = navigationBundle?.getString("type", "-1").toString()

        // Userinfo echoed in logcat for reference
        Log.i("Shared user information", "Name: $sharedUsername\nMobile number: $sharedMobileNo\nEmail: $sharedEmailID")

        findViewById<TextView>(R.id.otpMessage2).text = "Enter the 4 digit One Time Password (OTP) you have received in your registered email"

        val otpButton = findViewById<Button>(R.id.otpButton)

        // Homepage activity is popped after OTP validation
        otpButton.setOnClickListener {
            val OTP = findViewById<EditText>(R.id.otp).text.toString()

            //TODO: OTP Validation using API
            if(OTP.length == 4) {

                if(type == "2") {
                    submitRegisterOTP(OTP, userId)
                }
                else{
                    //TODO: Temporary setup. New function required for login OTP verification
                    val intent = Intent(this@OtpActivity, HomepageActivity::class.java)
                    intent.putExtras(navigationBundle!!)
                    startActivity(intent)
                }

                //TODO: OTP Comparison
                val intent = Intent(this@OtpActivity, HomepageActivity::class.java)
                intent.putExtras(navigationBundle!!)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Enter 4 digit correct OTP", Toast.LENGTH_LONG).show()
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun submitRegisterOTP(otp: String, userId: String){

        val userVerifyRequestModel = UserVerifyRequestModel(otp)
        Log.i("OTP Activity msg", "User ID obtained: $userId")

        //Accessing API Interface for pushing user data
        val response = ServiceBuilder.buildService(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            response.verifyUserData(userVerifyRequestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                object : Callback<UserVerifyResponseModel> {
                    override fun onResponse(
                        call: Call<UserVerifyResponseModel>,
                        response: Response<UserVerifyResponseModel>
                    ) {
                            var errorList = arrayOf<String>("success")

                            val status = response.body()?.status.toString()

                            if(status=="fail") {
                                errorList = response.body()?.errors?.pin as Array<String>
                            }

                            Toast.makeText(this@OtpActivity, "status: $status", Toast.LENGTH_SHORT).show()
                            Log.i("OTP Verification msg", "Error: "+errorList[0])
                    }

                    override fun onFailure(call: Call<UserVerifyResponseModel>, text: Throwable ) {
                        Toast.makeText(this@OtpActivity, "Error", Toast.LENGTH_LONG)
                            .show()
                        Log.i("User registration failure", text.toString())
                    }
                }
            )
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

}