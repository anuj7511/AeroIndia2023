package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileInfoActivity : AppCompatActivity() {

    private lateinit var navigableBundle: Bundle

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
            if(showAlertBox()){
                val hashMap: HashMap<String, String> = HashMap()

                for(i in 1..8){
                    hashMap.put(decode(i), findViewById<EditText>(resources.getIdentifier(
                        "userinfo_$i",
                        "id",
                        packageName
                    )).text.toString())
                    updateInfo(hashMap)
                }
            }
            else
                assignOriginalValues()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun updateInfo(hashMap: HashMap<String, String>){

        //TODO: Enter required variables for updation
        val userInfoUpdateRequestModel =
            hashMap["id"]?.let { UserInfoUpdateRequestModel(id = it.toInt(), name = hashMap["name"].toString()) }

        //Accessing API Interface for updating user data
        val response = ServiceBuilder.buildService(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            response.updateUserInfo(userInfoUpdateRequestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                object : Callback<UserInfoUpdateResponseModel> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<UserInfoUpdateResponseModel>,
                        response: Response<UserInfoUpdateResponseModel>
                    ) {
                        val status = response.body()?.status.toString()
                        val message = response.body()?.message.toString()
                    }

                    override fun onFailure(call: Call<UserInfoUpdateResponseModel>, text: Throwable) {
                        Log.i("ProfileInfo Activity msg", "Failure!!")
                        Toast.makeText(this@ProfileInfoActivity, "failure", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    private fun showAlertBox(): Boolean {
        var flag: Boolean = false
        MaterialAlertDialogBuilder(this)
            .setTitle("WARNING !")
            .setMessage("Do you want the update the details for sure ?")
            .setPositiveButton("YES") { dialog, which ->
                flag = true
            }
            .setNegativeButton("NO") { dialog, which ->
                Log.i("ProfileInfo Activity msg", "Info update aborted")
            }
            .show()

        return flag
    }

    private fun assignOriginalValues(){

        findViewById<EditText>(R.id.userinfo_1).setText(navigableBundle.getString("name", "NA"))
        findViewById<EditText>(R.id.userinfo_2).setText(navigableBundle.getString("phoneNo", "NA"))
        findViewById<EditText>(R.id.userinfo_3).setText(navigableBundle.getString("designation", "NA"))
        findViewById<TextView>(R.id.userinfo_4).setText(navigableBundle.getString("userId", "NA"))
        findViewById<EditText>(R.id.userinfo_5).setText(navigableBundle.getString("userType", "NA"))
        findViewById<EditText>(R.id.userinfo_6).setText(navigableBundle.getString("email", "NA"))
        findViewById<EditText>(R.id.userinfo_7).setText(navigableBundle.getString("address", "NA"))
        findViewById<EditText>(R.id.userinfo_8).setText(navigableBundle.getString("organisation", "NA"))

    }

    //TODO: decode function incomplete for certain values
    private fun decode(i: Int): String{
        return when(i){
            1 -> "name"
            2 -> "phone_no"
            3 -> "designation"
            4 -> "id" // Should not be changed
            5 -> "usertype" // Not used
            6 -> "email" // Should not be changed
            7 -> "address"
            8 -> "organisation"
            else -> "Unknown"
        }
    }
}