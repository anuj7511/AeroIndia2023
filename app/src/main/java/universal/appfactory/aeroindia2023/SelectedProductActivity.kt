package universal.appfactory.aeroindia2023

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class SelectedProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_product)

        val categoryText = findViewById<TextView>(R.id.category)
        val exhibitorText = findViewById<TextView>(R.id.exhibitor)
        val descriptionText = findViewById<TextView>(R.id.description)
        val productImage = findViewById<ImageView>(R.id.product_img)

        val category = intent.getStringExtra("Category")
        val exhibitor = intent.getStringExtra("Exhibitor")
        val description = intent.getStringExtra("Description")
        val image = intent.getStringExtra("Image")

        categoryText.text = category
        exhibitorText.text = exhibitor
        descriptionText.text = description

        Glide.with(this@SelectedProductActivity).load(image).into(productImage)
    }


}