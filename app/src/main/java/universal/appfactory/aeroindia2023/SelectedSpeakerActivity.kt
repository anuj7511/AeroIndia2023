package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SelectedSpeakerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_speaker)

        val set = ConstraintSet()
        val parent = findViewById<ConstraintLayout>(R.id.parent)
        val information = findViewById<TextView>(R.id.information)
        val agenda = findViewById<TextView>(R.id.agenda)
        val underLine = findViewById<ImageView>(R.id.under_line)
        val scrollView = findViewById<ScrollView>(R.id.scrollView)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        set.clone(parent)

        val nameText = findViewById<TextView>(R.id.name)
        val descriptionText = findViewById<TextView>(R.id.biography)
        val speakerImage = findViewById<ImageView>(R.id.speaker_img)

        val name = intent.getStringExtra("Name")
        val image = intent.getStringExtra("Image")
        val description = intent.getStringExtra("Biography")

        nameText.text = name
        descriptionText.text = description

        Glide.with(this@SelectedSpeakerActivity).load(image).into(speakerImage)

        agenda.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.START)
            set.connect(underLine.id, ConstraintSet.END, parent.id, ConstraintSet.END)
            set.applyTo(parent)
            scrollView.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        }

        information.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.END)
            set.connect(underLine.id, ConstraintSet.START, parent.id, ConstraintSet.START)
            set.applyTo(parent)
            scrollView.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        }
    }
}