package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_profile_info.*
import kotlinx.android.synthetic.main.activity_selected_exhibitor.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpActivity : AppCompatActivity() {

    var otpFlag: Boolean = true
    var navigableBundle = Bundle()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        navigableBundle = intent.extras!!

        val sharedEmailID: String =  navigableBundle.getString("email", "DEFAULT_EMAIL").toString()
        val userId: String = navigableBundle.getString("userId", "-1").toString()
        val type: String = navigableBundle.getString("type", "-1").toString()

        // Userinfo echoed in logcat for reference
        Log.i("Shared user information", "Email: $sharedEmailID\nType: $type\nUser ID: $userId")

        findViewById<TextView>(R.id.otpMessage2).text = "Enter the 4 digit One Time Password (OTP) you have received in your registered email\n\nEmail: $sharedEmailID"

        val otpButton = findViewById<Button>(R.id.otpButton)

        // Homepage activity is popped after OTP validation
        otpButton.setOnClickListener {
            val OTP = findViewById<EditText>(R.id.otp).text.toString()
            if(OTP.length == 4)
                submitOTP(OTP, userId)
            else
                Toast.makeText(this, "Enter 4 digit correct OTP", Toast.LENGTH_LONG).show()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    // register-verify API
    fun submitOTP(otp: String, userId: String){

        val userVerifyRequestModel = UserVerifyRequestModel(otp, userId)
        Log.i("OTP Activity msg", "User ID obtained: $userId\nOTP obtained: $otp")

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
                            val verificationMessage = response.body()?.message?.verification_code.toString()

                            if(status == "fail") {
                                otpFlag = false
                                Toast.makeText(this@OtpActivity, "Incorrect OTP", Toast.LENGTH_SHORT).show()
                                val pinError = response.body()?.errors?.pin.toString()
                                val idError = response.body()?.errors?.id.toString()
                                Log.i("OTP Verification errors", "Pin error: $pinError[0]\nID error: $idError")
                            }
                            else{
                                otpFlag = true
                                val name = response.body()?.data?.name.toString()
                                val email = response.body()?.data?.email_id.toString()
                                val phoneNo = response.body()?.data?.phone_no.toString()
                                val userType = response.body()?.data?.user_type.toString()
                                val foreignKeyId = response.body()?.data?.foreign_key_id.toString()

                                navigableBundle.putString("name", name)
                                navigableBundle.putString("email", email)
                                navigableBundle.putString("phoneNo", phoneNo)
                                navigableBundle.putString("designation", checkDesignation(userType.toInt()))
                                navigableBundle.putString("userId", userId)
                                navigableBundle.putString("userType", userType)
                                navigableBundle.putString("foreignKeyId", foreignKeyId)
                                navigableBundle.putBoolean("loginStatus", true)

                                val sharedPreferences: SharedPreferences = getSharedPreferences("LocalUserData", MODE_PRIVATE)
                                val editPreferences: SharedPreferences.Editor = sharedPreferences.edit()

                                editPreferences.putString("name", name)
                                editPreferences.putString("email", email)
                                editPreferences.putString("phoneNo", phoneNo)
                                editPreferences.putString("designation", checkDesignation(userType.toInt()))
                                editPreferences.putString("userId", userId)
                                editPreferences.putString("userType", userType)
                                editPreferences.putString("foreignKeyId", foreignKeyId)
                                editPreferences.putBoolean("loginStatus", true)

                                editPreferences.apply()

//                                intent = when(userType){
//                                    "4" -> Intent(this@OtpActivity, ManagerHomepageActivity::class.java)
//                                    "5" -> Intent(this@OtpActivity, ZonalManagerHomepageActivity::class.java)
//                                    else -> Intent(this@OtpActivity, HomepageActivity::class.java)
//                                }

                                intent = Intent(this@OtpActivity, HomepageActivity::class.java)
                                intent.putExtras(navigableBundle)
                                Toast.makeText(this@OtpActivity, "Logged in successfully", Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                                this@OtpActivity.finishAffinity()
                            }

                            Log.i("Status", status)
                            Log.i("OTP Verification Msg", verificationMessage)
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

    private fun checkDesignation(userId: Int): String{
        return when(userId){
            0 -> "Unknown role"
            1 -> "Attendee"
            2 -> "Delegates"
            3 -> "Liaison officer"
            4 -> "Washroom zonal manager"
            5 -> "Washroom super manager"
            6 -> "Exhibitor"
            7 -> "Media"
            else -> "Unknown"
        }
    }
}