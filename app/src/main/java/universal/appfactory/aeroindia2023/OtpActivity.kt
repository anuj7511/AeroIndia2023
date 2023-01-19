package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_profile_info.*
import kotlinx.android.synthetic.main.activity_selected_exhibitor.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("LocalVariableName", "UNUSED_ANONYMOUS_PARAMETER", "UNUSED_VARIABLE")
class OtpActivity : AppCompatActivity() {

    var otpFlag: Boolean = true
    var navigableBundle = Bundle()
    private var otpAttempts: Int = 3

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
        findViewById<TextView>(R.id.otpAttempts).text = "Remaining attempts: 3"

        val otpButton = findViewById<Button>(R.id.otpButton)

        // Homepage activity is popped after OTP validation
            otpButton.setOnClickListener {
                if(otpAttempts > 0) {
                    findViewById<TextView>(R.id.otpAttempts).text = "Remaining attempts: $otpAttempts"
                    val OTP = findViewById<EditText>(R.id.otp).text.toString()
                    if (OTP.length == 4)
                        submitOTP(OTP, userId)
                     else
                        Toast.makeText(this, "Enter 4 digit correct OTP", Toast.LENGTH_LONG).show()
                }
                else{
                    MaterialAlertDialogBuilder(this@OtpActivity)
                        .setTitle("WARNING !")
                        .setMessage("You have entered incorrect OTPs beyond the given limit, which is three. Kindly try after sometime.")
                        .setNeutralButton("OK") { dialog, which ->
                            this@OtpActivity.finishAffinity()
                        }
                        .show()
                }
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
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<UserVerifyResponseModel>,
                        response: Response<UserVerifyResponseModel>
                    ) {
                        val status = response.body()?.status.toString()
                        val verificationMessage = response.body()?.message?.verification_code.toString()

                        if(status == "success") {
                            otpFlag = true
                            val name = response.body()?.data?.name.toString()
                            val email = response.body()?.data?.email_id.toString()
                            val phoneNo = response.body()?.data?.phone_no.toString()
                            val userType = response.body()?.data?.user_type.toString()
                            val foreignKeyId = response.body()?.data?.foreign_key_id.toString()
                            val verifiedToken = response.body()?.data?.verified_token.toString()
                            val designation = response.body()?.data?.designation.toString()
                            val organisation = response.body()?.data?.organisation.toString()
                            val profileImage = response.body()?.data?.profile_image.toString()
                            val address = response.body()?.data?.address.toString()

                            //TODO: Updated for other parameters as well
                            navigableBundle.putString("name", name)
                            navigableBundle.putString("email", email)
                            navigableBundle.putString("phoneNo", phoneNo)
                            navigableBundle.putString("designation", checkDesignation(userType.toInt()))
                            navigableBundle.putString("userId", userId)
                            navigableBundle.putString("userType", userType)
                            navigableBundle.putString("address", address)
                            navigableBundle.putString("organisation", organisation)
                            navigableBundle.putString("designation", designation)
                            navigableBundle.putString("profileImage", profileImage)
                            navigableBundle.putString("foreignKeyId", foreignKeyId)
                            navigableBundle.putBoolean("loginStatus", true)

                            val sharedPreferences: SharedPreferences = getSharedPreferences("LocalUserData", MODE_PRIVATE)
                            val editPreferences: SharedPreferences.Editor = sharedPreferences.edit()

                            //TODO: Updated for other parameters as well
                            editPreferences.putString("name", name)
                            editPreferences.putString("email", email)
                            editPreferences.putString("phoneNo", phoneNo)
                            editPreferences.putString("designation", checkDesignation(userType.toInt()))
                            editPreferences.putString("userId", userId)
                            editPreferences.putString("userType", userType)
                            editPreferences.putString("foreignKeyId", foreignKeyId)
                            editPreferences.putString("address", address)
                            editPreferences.putString("organisation", organisation)
                            editPreferences.putString("designation", designation)
                            editPreferences.putString("profileImage", profileImage)
                            editPreferences.putBoolean("loginStatus", true)

                            editPreferences.apply()

                            intent = Intent(this@OtpActivity, HomepageActivity::class.java)
                            intent.putExtras(navigableBundle)
                            Toast.makeText(this@OtpActivity, "Logged in successfully", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            this@OtpActivity.finishAffinity()
                        }
                        else{
                            otpFlag = false
                            otpAttempts -= 1
                            Toast.makeText(this@OtpActivity, "Incorrect OTP", Toast.LENGTH_SHORT).show()
                            val pinError = response.body()?.errors?.pin.toString()
                            val idError = response.body()?.errors?.id.toString()
                            Log.i("OTP Verification errors", "Pin error: $pinError[0]\nID error: $idError")
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

    // Should not be changed
    private fun checkDesignation(userId: Int): String{
        return when(userId){
            1 -> "Attendee"
            2 -> "Delegate"
            3 -> "Liaison officer"
            4 -> "Washroom zonal manager"
            5 -> "Washroom super manager"
            6 -> "Exhibitor"
            7 -> "Media"
            else -> "Unknown role"
        }
    }
}