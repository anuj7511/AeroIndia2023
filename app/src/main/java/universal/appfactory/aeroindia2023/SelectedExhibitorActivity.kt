package universal.appfactory.aeroindia2023

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectedExhibitorActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private lateinit var data: ArrayList<ProductModel>
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_exhibitor)

        val set = ConstraintSet()
        val parent = findViewById<ConstraintLayout>(R.id.parent)
        val information = findViewById<TextView>(R.id.information)
        val products = findViewById<TextView>(R.id.products)
        val underLine = findViewById<ImageView>(R.id.under_line)
        val cardView = findViewById<CardView>(R.id.scrollView)
        recyclerview = findViewById(R.id.recycler_view)

        set.clone(parent)

        val nameText = findViewById<TextView>(R.id.name)
        val locationText = findViewById<TextView>(R.id.location)
        val countryText = findViewById<TextView>(R.id.country)
        val descriptionText = findViewById<TextView>(R.id.description)
        val exhibitorImage = findViewById<ImageView>(R.id.exhibitor_img)
        val emailText = findViewById<TextView>(R.id.email)
        val addressText = findViewById<TextView>(R.id.address)
        val emailAddressText = findViewById<TextView>(R.id.emailAddress)
        val mobileText = findViewById<TextView>(R.id.mobile)
        val websiteText = findViewById<TextView>(R.id.website)
        val companyText = findViewById<TextView>(R.id.company)

        val name = intent.getStringExtra("Name")
        val image = intent.getStringExtra("Image")
        val country = intent.getStringExtra("Country")
        val email = intent.getStringExtra("Email")
        val address = intent.getStringExtra("Address")
        val companyEmail = intent.getStringExtra("Company Email")
        val mobile = intent.getStringExtra("Mobile")
        val website = intent.getStringExtra("Website")
        val company = intent.getStringExtra("Company")
        val location = intent.getStringExtra("Location")
        val description = intent.getStringExtra("Description")

        nameText.text = name
        locationText.text = location
        countryText.text = country
        descriptionText.text = description
        emailText.text = email
        addressText.text = address
        emailAddressText.text = companyEmail
        mobileText.text = mobile
        websiteText.text = website
        companyText.text = company

        Glide.with(this@SelectedExhibitorActivity).load(image).into(exhibitorImage)

        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()

        products.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.START)
            set.connect(underLine.id, ConstraintSet.END, parent.id, ConstraintSet.END)
            set.applyTo(parent)
            cardView.visibility = View.INVISIBLE
            recyclerview.visibility = View.VISIBLE
        }

        information.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.END)
            set.connect(underLine.id, ConstraintSet.START, parent.id, ConstraintSet.START)
            set.applyTo(parent)
            cardView.visibility = View.VISIBLE
            recyclerview.visibility = View.INVISIBLE
        }
    }
}