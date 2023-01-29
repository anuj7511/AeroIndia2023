package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_faqs.*
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_selected_exhibitor.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class ProfileInfoActivity : AppCompatActivity() {

    private lateinit var navigableBundle: Bundle
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editPreferences: SharedPreferences.Editor

    companion object{
        private const val GALLERY_REQ_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        navigableBundle = intent.extras!!
        sharedPreferences = getSharedPreferences("LocalUserData", MODE_PRIVATE)
        editPreferences = sharedPreferences.edit()

        assignOriginalValues()

        findViewById<TextView>(R.id.setProfileImage).setOnClickListener{
            launchGallery()
        }

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

                                for(key in hashMap.keys){
                                    editPreferences.putString(key, hashMap[key])
                                    navigableBundle.putString(key, hashMap[key])
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
        findViewById<EditText>(R.id.userinfo_1).setText(sharedPreferences.getString("name", "NA"))
        findViewById<EditText>(R.id.userinfo_2).setText(sharedPreferences.getString("phoneNo", "NA"))
        findViewById<EditText>(R.id.userinfo_3).setText(sharedPreferences.getString("address", "NA"))
        findViewById<EditText>(R.id.userinfo_4).setText(sharedPreferences.getString("organisation", "NA"))
        findViewById<EditText>(R.id.userinfo_5).setText(sharedPreferences.getString("designation", "NA"))
        findViewById<EditText>(R.id.userinfo_6).setText(sharedPreferences.getString("userId", "NA"))
        findViewById<EditText>(R.id.userinfo_7).setText(sharedPreferences.getString("userType", "NA"))
        findViewById<EditText>(R.id.userinfo_8).setText(sharedPreferences.getString("email", "NA"))
        findViewById<EditText>(R.id.userinfo_9).setText(sharedPreferences.getString("foreignKeyId", "NA"))
    }

    // Codes should not be changed
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

    @SuppressLint("IntentReset")
    private fun launchGallery(){
        val imageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imageIntent.type = "image/*"
        startActivityForResult(imageIntent, GALLERY_REQ_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQ_CODE && resultCode == RESULT_OK){
            Log.i("Image data", data?.data.toString()+", "+data?.type.toString())
            findViewById<ImageView>(R.id.userProfileImage).setImageURI(data?.data)
        }
        else{
            Log.i("ProfileInfo Activity msg", "No image found")
        }
    }
}