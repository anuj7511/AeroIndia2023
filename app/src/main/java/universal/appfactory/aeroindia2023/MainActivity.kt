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
import universal.appfactory.aeroindia2023.databinding.ActivityMainBinding

// API_1: http://aeroindia.gov.in/api/register-user
// API_2: http://aeroindia.gov.in/api/register-verify
// Bearer token: 61b25a411a2dad66bb7b6ff145db3c2f

// Main Activity
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        // Displays intro layout or image
        setContentView(R.layout.intro_layout)

        // Timer to display the intro image
        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            // Displays the Register activity (Main activity)
            override fun onFinish() {
                setContentView(R.layout.activity_main)
                val buttonId = findViewById<Button>(R.id.emailButton)

                buttonId.setOnClickListener{
                    val email = findViewById<EditText>(R.id.emailAddress).text.toString()
                    val username = findViewById<EditText>(R.id.username).text.toString()
                    val mobileNo = findViewById<EditText>(R.id.mobileNumber).text.toString()

                    Log.i("Unshared user information", "Name: $username\nMobile number: $mobileNo\nEmail: $email")

                    val bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("username", username)
                    bundle.putString("mobileNo", mobileNo)

                    val intent = Intent(this@MainActivity, OtpActivity::class.java)

                    submitUserData(email, username, mobileNo)

                    // OTP activity is initialized only after user registration using API
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
        }
        timer.start()
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
                        val status = response.body()?.status.toString()
                        val msg = response.body()?.message.toString()


                        Toast.makeText(this@MainActivity, status,
                            Toast.LENGTH_SHORT).show()

                        //TODO: Error part using extension class for response model
                        val nameError = response.body()?.errors?.name
                        val phoneNoError = response.body()?.errors?.phone_no
                        val emailError = response.body()?.errors?.email_id

                        Log.i("Components", "User ID: $user_id, Verify: $verify, Status: $status, Msg: $msg")
                        Log.i("Errors", "$nameError, $phoneNoError, $emailError")

                    }

                    override fun onFailure(call: Call<UserRegisterResponseModel>, text: Throwable ) {
                        Toast.makeText(this@MainActivity, text.toString(), Toast.LENGTH_LONG)
                            .show()
                        Log.i("User registration failure", "Activity failed to engage")
                    }
                }
            )
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }
}

