package universal.appfactory.aeroindia2023.faqs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_reach_out.*
import universal.appfactory.aeroindia2023.R

class ReachOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reach_out)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        send_reach_out.setOnClickListener {
            sendMail()
        }
    }

    private fun sendMail(){
        var recipients = arrayOf<String>("sarikimohan.krishna.chy20@itbhu.ac.in")

        var content = reach_out_content.text.toString()

        var intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL,recipients)
        intent.putExtra(Intent.EXTRA_SUBJECT,"Reach Out Query")
        intent.putExtra(Intent.EXTRA_TEXT, "Dear Helpdesk \n     $content\n Regards")

        intent.type = "message/rfc822"
        startActivity(Intent.createChooser(intent, "choose an email client"))
    }
}