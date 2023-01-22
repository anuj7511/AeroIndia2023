package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import android.view.WindowManager
import android.widget.*

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.android.synthetic.main.feedback_alertbox.*
import kotlinx.android.synthetic.main.user_card.view.*
import kotlinx.android.synthetic.main.zonal_manager_user_card.*
import kotlinx.coroutines.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.databinding.ActivityFeedbackBinding

class Feedback : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    private var qrScanIntegrator: IntentIntegrator? = null
    var washroom_Id: Int = 0
    lateinit var view: View
    var user_id: Int=0 // Previously late init washroomId: String
    private var checkedFeedbacks: String = ""
    private val feedbackTexts = arrayOf("Washroom is nice and clean, great job!", "Washroom needs cleaning", "Washroom drain is clogged", "Washroom has foul smell", "Refill paper towels", "Water is leaking", "Door lock is broken", "Washroom light does not work", "Refill hand soap", "Washroom is locked")
    private val checkedItems = booleanArrayOf(true, false, false, false, false, false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        view = binding.root
        val bundle = intent.extras
        if(bundle!=null)
        {
            user_id= bundle.getString("userId")!!.toInt()
        }

        setContentView(view)

        val history= findViewById<TextView>(R.id.History)
        setOnClickListener()
        setupScanner()

        history.setOnClickListener {
            val intent = Intent(this, UserHistoryActivity::class.java)
            intent.putExtra("Name", user_id)
            startActivity(intent)
        }
    }

    private fun setupScanner() {
        qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator?.setOrientationLocked(false)
        qrScanIntegrator?.setBeepEnabled(false)
    }

    private fun setOnClickListener() {
        binding.scanQrButton.setOnClickListener { performAction() }
    }

    private fun performAction() {
        // Code to perform action when button is clicked.
        qrScanIntegrator?.initiateScan()
    }

    fun checkFeedbacks(view: View = View(this@Feedback)){

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val customLayout: View = layoutInflater.inflate(R.layout.feedback_alertbox, null)
        builder.setView(customLayout)

        val inputFeedback = customLayout.findViewById<EditText>(R.id.alertBoxFeedback)

        builder.setPositiveButton("SUBMIT") { dialog, which ->

            for(i in 1..10) {
                val checkbox = customLayout.findViewById<CheckBox>(resources.getIdentifier("checkbox_$i", "id", packageName))
                if (checkbox.isChecked) {
                    checkedFeedbacks += ", " + checkbox.text
                }
            }

            checkedFeedbacks += ", " + inputFeedback.text
            submitFeedback(checkedFeedbacks, washroom_Id, user_id)
        }

        builder.setNegativeButton("CANCEL", null)
        builder.create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            // If QRCode has no data.
            if (result.contents == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show()
            } else {
                   washroom_Id=result.contents.toInt()

                    // Data not in the expected format. So, whole object as toast message.
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun submitFeedback(feedback:String, washroomId:Int,userId:Int) {
        val requestModel = RequestModel(washroomId,userId, feedback)

        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            response.sendReq(requestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                object : Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        val responseMsg = response.message().toString()
                        Toast.makeText(this@Feedback, responseMsg, Toast.LENGTH_LONG).show()
                        Log.i("Feedback Activity response", "Feedback sent successfully, Response msg: $responseMsg variables are   remark: $feedback washroom_id:$washroomId user_id:$userId")

                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable ) {
                        Toast.makeText(this@Feedback, t.toString(), Toast.LENGTH_LONG).show()
                        Log.i("Submit error","t.toString()")
                        print("error are $t.toString()")
                    }

                }
            )
        }
    }

    private fun spToPx(sp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, applicationContext.resources.displayMetrics)
            .toInt()
    }
}


