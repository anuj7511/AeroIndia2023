package universal.appfactory.aeroindia2023.faqs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_faqs_answer.*
import universal.appfactory.aeroindia2023.R

class FAQsAnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs_answer)

        var faqAnswer = intent.getStringExtra("answer")

        answer.text = faqAnswer.toString()

    }
}