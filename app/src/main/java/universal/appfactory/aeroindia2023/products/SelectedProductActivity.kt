package universal.appfactory.aeroindia2023.products

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
import universal.appfactory.aeroindia2023.exhibitors.*
import java.util.*
import kotlin.collections.ArrayList


class SelectedProductActivity : AppCompatActivity() {

    private lateinit var adapter: ExhibitorAdapter2
    private lateinit var data: ArrayList<ExhibitorModel2>
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_product)

        val set = ConstraintSet()
        val parent = findViewById<ConstraintLayout>(R.id.parent)
        val information = findViewById<TextView>(R.id.information)
        val exhibitors = findViewById<TextView>(R.id.exhibitors)
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

        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()
        if (exhibitor != null) {
            fetchExhibitorData(exhibitor)
        }

        exhibitors.setOnClickListener {
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

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchExhibitorData (id: String) {
        val exhibitorApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO) {

            exhibitorApi.getProductExhibitor("Bearer 61b25a411a2dad66bb7b6ff145db3c2f", id)?.enqueue(object :
                Callback<ExhibitorResponse2?> {
                override fun onResponse(
                    call: Call<ExhibitorResponse2?>,
                    response: Response<ExhibitorResponse2?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as java.util.ArrayList<ExhibitorModel2>
                    Collections.sort(data, SortByName())
                    // This will pass the ArrayList to our Adapter
                    adapter = ExhibitorAdapter2(data, this@SelectedProductActivity)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter

                }

                override fun onFailure(call: Call<ExhibitorResponse2?>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message,
                        Toast.LENGTH_SHORT).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            })

        }
    }

    private class SortByName : Comparator<ExhibitorModel2> {
        override fun compare(
            object1: ExhibitorModel2,
            object2: ExhibitorModel2
        ): Int {
            var name1 = ""
            var name2 = ""
            if (!object1.getComp_Name().isNullOrEmpty())
                name1 = object1.getComp_Name().lowercase(Locale.ROOT).trim()
            if (!object2.getComp_Name().isNullOrEmpty())
                name2 = object2.getComp_Name().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }
}