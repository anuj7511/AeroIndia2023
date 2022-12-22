package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRegistrationActivity : AppCompatActivity() {

    var status: String = ""
    var flag: Boolean = false

    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        supportActionBar?.hide()

        // Displays the Register activity
        setContentView(R.layout.activity_user_registration)
        val buttonId: Button = findViewById(R.id.registerButton)

        buttonId.setOnClickListener{
            val email = findViewById<EditText>(R.id.emailAddress).text.toString()
            val username = findViewById<EditText>(R.id.username).text.toString()
            val mobileNo = findViewById<EditText>(R.id.mobileNumber).text.toString()

            Log.i("Unshared user information", "Name: $username\nMobile number: $mobileNo\nEmail: $email")

            bundle.putString("email", email)
            bundle.putString("username", username)
            bundle.putString("mobileNo", mobileNo)

            submitUserData(email, username, mobileNo)
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun submitUserData(email: String, username: String, mobileNo: String) {
        val userDataRequestModel = UserDataRequestModel(username, mobileNo, email)

        //Accessing API Interface for pushing user data
        val response = ServiceBuilder.buildService(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            response.sendUserData(userDataRequestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                object : Callback<UserRegisterResponseModel> {
                    override fun onResponse(
                        call: Call<UserRegisterResponseModel>,
                        response: Response<UserRegisterResponseModel>
                    ) {
                        val user_id = response.body()?.user_id.toString()
                        val verify = response.body()?.verify.toString()
                        status = response.body()?.status.toString()
                        val msg = response.body()?.message.toString()

                        Toast.makeText(this@UserRegistrationActivity, "Status: $status",
                            Toast.LENGTH_SHORT).show()

                        if(status=="fail") {
                            flag = true

                            val nameError: Array<String> = response.body()?.errors?.name as Array<String>
                            val phoneNoError: Array<String> = response.body()?.errors?.phone_no as Array<String>
                            val emailError: Array<String> = response.body()?.errors?.email_id as Array<String>

                            Log.i("Errors", nameError[0]+" "+phoneNoError[0]+" "+emailError[0])
                        }

                        Log.i("Components", "User ID: $user_id, Verify: $verify, Status: $status, Msg: $msg, Flag: $flag")

                        val intent = Intent(this@UserRegistrationActivity, OtpActivity::class.java)
                        intent.putExtras(bundle)

                        if(flag){
                            Log.i("Mismatched info", "Info not entered correctly")
                            Toast.makeText(this@UserRegistrationActivity, "Enter details correctly", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            startActivity(intent)
                        }

                    }

                    override fun onFailure(call: Call<UserRegisterResponseModel>, text: Throwable ) {
                        Toast.makeText(this@UserRegistrationActivity, "Error", Toast.LENGTH_LONG)
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