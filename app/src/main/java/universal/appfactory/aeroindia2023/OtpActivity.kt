package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.ID
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

    var otpFlag: Boolean = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val navigableBundle: Bundle = intent.extras!!

        val sharedEmailID: String =  navigableBundle.getString("email", "DEFAULT_EMAIL").toString()
        val sharedUsername: String = navigableBundle.getString("name", "DEFAULT_NAME").toString()
        val sharedMobileNo: String = navigableBundle.getString("phoneNo", "-1").toString()
        val designation: String = navigableBundle.getString("designation", "NA").toString()
        val userId: String = navigableBundle.getString("userId", "-1").toString()
        val type: String = navigableBundle.getString("type", "-1").toString()

        // Userinfo echoed in logcat for reference
        Log.i("Shared user information", "Name: $sharedUsername\nMobile number: $sharedMobileNo\nEmail: $sharedEmailID\nDesignation: $designation\nType: $type\nUser ID: $userId")

        findViewById<TextView>(R.id.otpMessage2).text = "Enter the 4 digit One Time Password (OTP) you have received in your registered email"

        val otpButton = findViewById<Button>(R.id.otpButton)

        // Homepage activity is popped after OTP validation
        otpButton.setOnClickListener {
            val OTP = findViewById<EditText>(R.id.otp).text.toString()


            //TODO: OTP Validation using API
            if(OTP.length == 4) {
                if (type == "2") {
                    submitRegisterOTP(OTP, userId)
                } else {
                    //TODO: Temporary setup. New function required for login OTP verification

                    val pin: String = navigableBundle.getString("pin", "0000")
                    if (pin.toInt() == OTP.toInt()) {
                        val intent = Intent(this@OtpActivity, HomepageActivity::class.java)
                        intent.putExtras(navigableBundle)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@OtpActivity, "Incorrect OTP", Toast.LENGTH_SHORT).show()
                    }
                }

                //TODO: OTP Comparison
                if (type == "2") {
                    if (otpFlag) {
                        Toast.makeText(
                            this@OtpActivity,
                            "Registration successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@OtpActivity, HomepageActivity::class.java)
                        intent.putExtras(navigableBundle)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@OtpActivity, "Enter correct OTP", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    Toast.makeText(this, "Enter 4 digit correct OTP", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun submitRegisterOTP(otp: String, userId: String){

        val userVerifyRequestModel = UserVerifyRequestModel(otp, userId)
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
                            val status = response.body()?.status.toString()
                            val verifyMsg = response.body()?.message.toString()

                            if(status=="fail") {
                                otpFlag = false
                                val pinError = response.body()?.errors?.pin.toString()
                                val idError = response.body()?.errors?.id.toString()
                                Log.i("OTP Verification errors", "Errors: $pinError, $idError")
                            }

                            Toast.makeText(this@OtpActivity, "status: $status", Toast.LENGTH_SHORT).show()
                            Log.i("OTP Verification Msg", verifyMsg)
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