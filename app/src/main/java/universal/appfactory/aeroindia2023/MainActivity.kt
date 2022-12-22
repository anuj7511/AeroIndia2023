package universal.appfactory.aeroindia2023

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    var status: String = ""
    var flag: Boolean = false

    val bundle = Bundle()

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

                    bundle.putString("email", email)
                    bundle.putString("username", username)
                    bundle.putString("mobileNo", mobileNo)

                    submitUserData(email, username, mobileNo)

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
                        status = response.body()?.status.toString()
                        val msg = response.body()?.message.toString()

                        Toast.makeText(this@MainActivity, "Status: $status",
                            Toast.LENGTH_SHORT).show()

                        if(status=="fail") {
                            flag = true

                            var nameError: Array<String> = arrayOf<String>()
                            var phoneNoError: Array<String> = arrayOf<String>()
                            var emailError: Array<String> = arrayOf<String>()

                            nameError = response.body()?.errors?.name as Array<String>
                            phoneNoError = response.body()?.errors?.phone_no as Array<String>
                            emailError = response.body()?.errors?.email_id as Array<String>

                            Log.i("Errors", nameError[0]+" "+phoneNoError[0]+" "+emailError[0])
                        }

                        Log.i("Components", "User ID: $user_id, Verify: $verify, Status: $status, Msg: $msg, Flag: $flag")

                        val intent = Intent(this@MainActivity, OtpActivity::class.java)
                        intent.putExtras(bundle)

                        if(flag){
                            Log.i("Mismatched info", "Info not entered correctly")
                            Toast.makeText(this@MainActivity, "Enter details correctly", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            startActivity(intent)
                        }

                    }

                    override fun onFailure(call: Call<UserRegisterResponseModel>, text: Throwable ) {
                        Toast.makeText(this@MainActivity, "User exists", Toast.LENGTH_LONG)
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


