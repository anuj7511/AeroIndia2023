package universal.appfactory.aeroindia2023.faqs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_faqs.*
import kotlinx.android.synthetic.main.activity_main.*
import universal.appfactory.aeroindia2023.R

class FAQsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        Event_attendees_visitors.setOnClickListener {
            startCategory(Event_attendees_visitors.text.toString())
        }

        Exhibitors.setOnClickListener {
            startCategory(Exhibitors.text.toString())
        }

        Media.setOnClickListener {
            startCategory(Media.text.toString())
        }

        reach_out.setOnClickListener {
            var intent = Intent(this, ReachOutActivity::class.java)
            startActivity(intent)
        }
    }

    fun startCategory(category: String){
        var intent = Intent(this, QuestionsActivity::class.java)
        intent.putExtra("Category",category.toString())
        startActivity(intent)
    }

}