package universal.appfactory.aeroindia2023.products

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import universal.appfactory.aeroindia2023.R


class SelectedProductActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_product)

        val set = ConstraintSet()
        val parent = findViewById<ConstraintLayout>(R.id.parent)
        val information = findViewById<TextView>(R.id.information)
        val event = findViewById<TextView>(R.id.event)
        val underLine = findViewById<ImageView>(R.id.under_line)
        val scrollView = findViewById<CardView>(R.id.scrollView)
        val nameText = findViewById<TextView>(R.id.name)
        val categoryText = findViewById<TextView>(R.id.category)
        val exhibitorText = findViewById<TextView>(R.id.exhibitor)
        val descriptionText = findViewById<TextView>(R.id.description)
        val productImage = findViewById<ImageView>(R.id.product_img)
        recyclerview = findViewById(R.id.recycler_view)

        set.clone(parent)

        val name = intent.getStringExtra("Name")
        val category = intent.getStringExtra("Category")
        val exhibitor = intent.getStringExtra("Exhibitor")
        val description = intent.getStringExtra("Description")
        val image = intent.getStringExtra("Image")

        nameText.text = name
        categoryText.text = category
        exhibitorText.text = exhibitor
        descriptionText.text = description

        Glide.with(this@SelectedProductActivity).load(image).into(productImage)

        event.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.START)
            set.connect(underLine.id, ConstraintSet.END, parent.id, ConstraintSet.END)
            set.applyTo(parent)
            scrollView.visibility = View.INVISIBLE
            recyclerview.visibility = View.VISIBLE
        }

        information.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.END)
            set.connect(underLine.id, ConstraintSet.START, parent.id, ConstraintSet.START)
            set.applyTo(parent)
            scrollView.visibility = View.VISIBLE
            recyclerview.visibility = View.INVISIBLE
        }
    }


}