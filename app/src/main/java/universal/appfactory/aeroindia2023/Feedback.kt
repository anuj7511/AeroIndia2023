package universal.appfactory.aeroindia2023
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.databinding.ActivityFeedbackBinding


class Feedback : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val btn = findViewById<Button>(R.id.submit)
        val feedback = findViewById<EditText>(R.id.writeText)
        btn.setOnClickListener { submitFeedback(feedback.text.toString()) }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun submitFeedback(feedback:String) {
        val requestModel = RequestModel("123", "4586", "dfj", feedback)

        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            response.sendReq(requestModel,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
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