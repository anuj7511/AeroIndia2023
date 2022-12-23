package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// API_1: http://aeroindia.gov.in/api/register-user
// API_2: http://aeroindia.gov.in/api/register-verify
// Bearer token: 61b25a411a2dad66bb7b6ff145db3c2f

class UserRegistrationActivity : AppCompatActivity() {

    var flag: Boolean = false
    private lateinit var status: String
    private var navigableBundle: Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        navigableBundle = intent.extras!!

        supportActionBar?.hide()

        // Displays the Register activity
        setContentView(R.layout.activity_user_registration)
        val buttonId: Button = findViewById(R.id.registerButton)

        // ArrayAdapter for user types
        val spinner = findViewById<Spinner>(R.id.spinner)

        val userTypes = resources.getStringArray(R.array.user_types)
        val arrayAdapter = ArrayAdapter(this@UserRegistrationActivity, android.R.layout.simple_list_item_1, userTypes)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        val designation = spinner.selectedItem.toString()

        buttonId.setOnClickListener{
            val email = findViewById<EditText>(R.id.emailAddress).text.toString()
            val username = findViewById<EditText>(R.id.userName).text.toString()
            val mobileNo = findViewById<EditText>(R.id.mobileNumber).text.toString()

            Log.i("Unshared user information", "Name: $username\nMobile number: $mobileNo\nEmail: $email")

            navigableBundle.putString("email", email)
            navigableBundle.putString("username", username)
            navigableBundle.putString("mobileNo", mobileNo)
            navigableBundle.putString("designation", designation)

            if((email == "")||(username == "")||(mobileNo == "")){
                Toast.makeText(this@UserRegistrationActivity, "Fill all the columns", Toast.LENGTH_SHORT).show()
            }
            else {
                submitUserData(email, username, mobileNo)
            }
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
                        status = response.body()?.status.toString()
                        val userId = response.body()?.user_id.toString()
                        val verify = response.body()?.verify.toString()
                        val msg = response.body()?.message.toString()

                        //TODO: Error in API response due to changing datatypes of errors
                        val nameError = response.body()?.errors?.name.toString()
                        val phoneNoError = response.body()?.errors?.phone_no.toString()
                        val emailError  = response.body()?.errors?.email_id.toString()


                        if(status == "fail") {
                            flag = true
                            Log.i("Errors", "Name error: "+nameError+"\nPhone Number error: "+phoneNoError+"\nEmail error: "+emailError)
                            Log.i("Data error!", "Info not entered correctly")
                            Log.i("Components", "User ID: $userId, Verify: $verify, Status: $status, Msg: $msg")
                            Toast.makeText(this@UserRegistrationActivity, "Enter details correctly", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            // User registered successfully and pending for OTP verification
                            val intent = Intent(this@UserRegistrationActivity, OtpActivity::class.java)
                            navigableBundle.putString("userId", userId)
                            navigableBundle.putString("verify", verify)
                            intent.putExtras(navigableBundle)
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