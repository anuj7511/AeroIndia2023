package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_selected_exhibitor.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileInfoActivity : AppCompatActivity() {

    private lateinit var navigableBundle: Bundle
    private var checkFlag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        navigableBundle = intent.extras!!
        assignOriginalValues()

        findViewById<ImageView>(R.id.updateProfileIcon).setOnClickListener{

            MaterialAlertDialogBuilder(this)
                .setTitle("WARNING !")
                .setMessage("Do you want the update your details for sure ?")
                .setPositiveButton("YES") { dialog, which ->
                    val hashMap: HashMap<String, String> = HashMap()
                    for(i in 1..6) {
                        hashMap[decode(i)] = findViewById<EditText>(resources.getIdentifier("userinfo_$i", "id", packageName)).text.toString()
                        updateInfo(hashMap)
                    }
                }
                .setNegativeButton("NO") { dialog, which ->
                    assignOriginalValues()
                    Toast.makeText(this@ProfileInfoActivity, "aborted", Toast.LENGTH_SHORT).show()
                    Log.i("ProfileInfo Activity msg", "Info update aborted")
                }
                .show()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun updateInfo(hashMap: HashMap<String, String>){

        val userInfoUpdateRequestModel =
            hashMap["userId"]?.let { UserInfoUpdateRequestModel(id = it.toInt(),
                                                            name = hashMap["name"].toString(),
                                                            phone_no = hashMap["phoneNo"],
                                                            address = hashMap["address"],
                                                            designation = hashMap["designation"],
                                                            organisation = hashMap["organisation"])}

        //Accessing API Interface for updating user data
        val response = ServiceBuilder.buildService(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (userInfoUpdateRequestModel != null) {
                response.updateUserInfo(userInfoUpdateRequestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                    object : Callback<UserInfoUpdateResponseModel> {
                        @SuppressLint("SetTextI18n")
                        override fun onResponse(
                            call: Call<UserInfoUpdateResponseModel>,
                            response: Response<UserInfoUpdateResponseModel>
                        ) {
                            val status = response.body()?.status.toString()
                            val message = response.body()?.message.toString()

                            if(status == "success"){

                                val sharedPreferences: SharedPreferences = getSharedPreferences("LocalUserData", MODE_PRIVATE)
                                val editPreferences: SharedPreferences.Editor = sharedPreferences.edit()

                                for(key in hashMap.keys){
                                    editPreferences.putString(key, hashMap[key])
                                }
                                editPreferences.apply()

                                Toast.makeText(this@ProfileInfoActivity, "Updated successfully", Toast.LENGTH_SHORT).show()
                                Log.i("Status message", message)
                            }
                            else{
                                Toast.makeText(this@ProfileInfoActivity, "Status: $status", Toast.LENGTH_SHORT).show()
                                Log.i("Status message", message)
                            }
                        }

                        override fun onFailure(call: Call<UserInfoUpdateResponseModel>, text: Throwable) {
                            Log.i("ProfileInfo Error", text.toString())
                            Toast.makeText(this@ProfileInfoActivity, "Failure", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    private fun assignOriginalValues(){

        findViewById<EditText>(R.id.userinfo_1).setText(navigableBundle.getString("name", "NA"))
        findViewById<EditText>(R.id.userinfo_2).setText(navigableBundle.getString("phoneNo", "NA"))
        findViewById<EditText>(R.id.userinfo_3).setText(navigableBundle.getString("address", "NA"))
        findViewById<EditText>(R.id.userinfo_4).setText(navigableBundle.getString("organisation", "NA"))
        findViewById<EditText>(R.id.userinfo_5).setText(navigableBundle.getString("designation", "NA"))
        findViewById<TextView>(R.id.userinfo_6).setText(navigableBundle.getString("userId", "NA"))
        findViewById<EditText>(R.id.userinfo_7).setText(navigableBundle.getString("userType", "NA"))
        findViewById<EditText>(R.id.userinfo_8).setText(navigableBundle.getString("email", "NA"))

    }

    private fun decode(i: Int): String{

        return when(i){
            1 -> "name"
            2 -> "phoneNo"
            3 -> "address"
            4 -> "organisation"
            5 -> "designation" // Unchangeable
            6 -> "userId" // Unchangeable
            7 -> "userType" // Unchangeable
            8 -> "email" // Unchangeable
            9 -> "profile_image"
            else -> "Unknown"
        }
    }
}