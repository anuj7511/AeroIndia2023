package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResolvedRemarksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resolved_remarks)

        val submit = findViewById<Button>(R.id.submit)
        val remarks = findViewById<EditText>(R.id.resolvation)
        val complaintId = intent.getIntExtra("CompId", 1)
        val manager = intent.getIntExtra("manager",1)
        submit.setOnClickListener {
           val rR=remarks.text.toString()
            rRemarks(rR, manager, complaintId)
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
   private fun rRemarks(remarks: String, manager: Int,complaintId: Int) {
        val resolveRequestModel=ResolvedRequestModel(complaintId,manager,remarks)

        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            response.resolved(resolveRequestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
                object : Callback<ResolvedResponseModel> {
                    override fun onResponse(
                        call: Call<ResolvedResponseModel>,
                        response: Response<ResolvedResponseModel>
                    ) {

                        val responseMsg = response.message().toString()
                        Toast.makeText(

                            this@ResolvedRemarksActivity,
                            responseMsg,
                            Toast.LENGTH_LONG
                        ).show()
                        Log.i("Resolved response", "Resolved , Response msg: $responseMsg")
                    }

                    override fun onFailure(call: Call<ResolvedResponseModel>, t: Throwable ) {
                        Toast.makeText(this@ResolvedRemarksActivity, t.toString(), Toast.LENGTH_LONG).show()


                    }
                }
            )
        }
    }
}








