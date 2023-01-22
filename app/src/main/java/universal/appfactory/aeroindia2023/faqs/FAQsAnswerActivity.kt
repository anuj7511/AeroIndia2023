package universal.appfactory.aeroindia2023.faqs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_faqs_answer.*
import universal.appfactory.aeroindia2023.R

class FAQsAnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs_answer)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        var faqAnswer = intent.getStringExtra("answer")

        answer.text = faqAnswer.toString()

    }
}