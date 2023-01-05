package universal.appfactory.aeroindia2023
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

import androidx.appcompat.app.ActionBar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.coroutines.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.databinding.ActivityFeedbackBinding
import kotlin.properties.Delegates


class Feedback : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    private var qrScanIntegrator: IntentIntegrator? = null
    var washroom_Id:Int =0
    lateinit var complaint_id:String// Previously lateinit washroomId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        val view = binding.root
        val bundle=intent.extras
        if(bundle!=null)
        {
            complaint_id= bundle.getString("userId")!!
        }
        setContentView(view)


        val btn = findViewById<TextView>(R.id.submit)
        val history= findViewById<TextView>(R.id.History)
        val feedback = findViewById<EditText>(R.id.writeText)

        setOnClickListener()

        setupScanner()

        history.setOnClickListener {
            val intent = Intent(this, UserHistoryActivity::class.java)
            intent.putExtra("Name", complaint_id)
            startActivity(intent) }
        btn.setOnClickListener { submitFeedback(feedback.text.toString(),washroom_Id,complaint_id) }

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

//    val feedbackTexts = arrayOf("Washroom is nice and clean, great job!",
//                                "Washroom needs cleaning",
//                                "Washroom drain is clogged",
//                                "Washroom has foul smell",
//                                "Refill paper towels",
//                                "Water is leaking",
//                                "Door lock is broken",
//                                "Washroom light does not work",
//                                "Refill hand soap",
//                                "Washroom is locked")
//    val checkedTexts = booleanArrayOf(false,false,false,false,false,false,false,false,false,false)

    // Dialog box for feedback
//    private fun submitFeedback(){
//        MaterialAlertDialogBuilder(this)
//            .setTitle("Washroom ID: $washroom_Id")
//            .setMessage("Please select action required for these restrooms")
//            .setMultiChoiceItems(feedbackTexts, checkedTexts){
//                dialog, which, isChecked ->
//                checkedTexts[which] = isChecked
//                Log.i("Feedback activity msg", "Current item checked: $feedbackTexts[which]")
//
//            }
//            .setPositiveButton("SUBMIT") { dialog, which ->
//                //TODO: Feedback needs to be submitted
//            }
//            .setNegativeButton("No") { dialog, which ->
//                dialog.cancel()
//            }
//            .show()
//    }

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
    fun submitFeedback(feedback:String, washroomId:Int,complaintId:String) {
        val requestModel = RequestModel(washroomId,complaintId, feedback)

        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            response.sendReq(requestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                object : Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        val responseMsg = response.message().toString()
                        Toast.makeText(this@Feedback, responseMsg, Toast.LENGTH_LONG).show()
                        Log.i("Feedback Activity response", "Feedback sent successfully, Response msg: $responseMsg")
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable ) {
                        Toast.makeText(this@Feedback, t.toString(), Toast.LENGTH_LONG).show()

                    }
                }
            )
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }
}