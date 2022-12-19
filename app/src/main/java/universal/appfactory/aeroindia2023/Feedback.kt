package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Feedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        val btn = findViewById<Button>(R.id.submit)
        val feedback = findViewById<EditText>(R.id.writeText)
        btn.setOnClickListener { submitFeedback(feedback.text.toString()) }
    }
    fun submitFeedback(feedback:String) {
        val requestModel = RequestModel("123", "4586", "dfj", feedback)

        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        response.sendReq(requestModel).enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    Toast.makeText(
                        this@Feedback,
                        response.message().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(this@Feedback, t.toString(), Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}