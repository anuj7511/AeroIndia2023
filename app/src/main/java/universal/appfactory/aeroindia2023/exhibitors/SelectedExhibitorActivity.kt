package universal.appfactory.aeroindia2023.exhibitors

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
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.agendas.AgendaAdapter
import universal.appfactory.aeroindia2023.agendas.AgendaModel
import universal.appfactory.aeroindia2023.agendas.AgendaResponse
import universal.appfactory.aeroindia2023.products.ProductAdapter
import universal.appfactory.aeroindia2023.products.ProductModel
import universal.appfactory.aeroindia2023.products.ProductResponse


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

        val id = intent.getIntExtra("Id",53)
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

        nameText.text = company
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
        fetchProductData(id)

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

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchProductData (id: Int) {
        val productApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO) {

            productApi.getExhibitorProduct("Bearer 61b25a411a2dad66bb7b6ff145db3c2f", id)?.enqueue(object :
                Callback<ProductResponse?> {
                override fun onResponse(
                    call: Call<ProductResponse?>,
                    response: Response<ProductResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as java.util.ArrayList<ProductModel>
                    // This will pass the ArrayList to our Adapter
                    adapter = ProductAdapter(data, this@SelectedExhibitorActivity)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter

                }

                override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message,
                        Toast.LENGTH_SHORT).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            })

        }
    }
}