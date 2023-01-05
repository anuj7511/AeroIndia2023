package universal.appfactory.aeroindia2023.agendas

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.R


class AgendaActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private var viewPager: ViewPager2? = null
    private var mTabLayout: TabLayout? = null
    private lateinit var dataCategory: ArrayList<Categories>
    private lateinit var dataTime: ArrayList<Time>
    private lateinit var dataLocation: ArrayList<Location>
    private lateinit var checkedItem: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        // initialise the layout
        viewPager = findViewById(R.id.viewpager)
        mTabLayout = findViewById(R.id.tabLayout)
        val bOpenAlertDialog = findViewById<ImageButton>(R.id.sort)
        checkedItem = intArrayOf(0)

        dataCategory = ArrayList()
        dataTime = ArrayList()
        dataLocation = ArrayList()

        fetchAgendaData()
        alertDialog()
        initViews()

        // handle the button to open the alert dialog with the single item selection when clicked
        bOpenAlertDialog.setOnClickListener {
            alertDialog()
        }

    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(this)

        // title of the alert dialog
        alertDialog.setTitle("Choose an Item")

        val listItems = arrayOf("Category", "Time", "Location")


        alertDialog.setSingleChoiceItems(listItems, checkedItem[0]) { dialog, which ->
            checkedItem[0] = which

            // when selected an item the dialog should be closed with the dismiss method
            dialog.dismiss()
            setDynamicFragmentToTabLayout()
        }

        // set the negative button if the user is not interested to select or change already selected item
        alertDialog.setNegativeButton("Cancel") { dialog, which -> setDynamicFragmentToTabLayout()}

        val customAlertDialog = alertDialog.create()
        customAlertDialog.show()
    }


    private fun initViews() {

        viewPager?.offscreenPageLimit = 5
        mTabLayout?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        setDynamicFragmentToTabLayout()
    }

    private fun setDynamicFragmentToTabLayout() {

        val size: Int = if(checkedItem[0]==1)
            dataTime.size
        else if(checkedItem[0]==2)
            dataLocation.size
        else
            dataCategory.size

        val classification: String = if(checkedItem[0]==1)
            "Time"
        else if(checkedItem[0]==2)
            "Location"
        else
            "Category"

        mTabLayout?.removeAllTabs()

        for (i in 1..size) {
            if(checkedItem[0]==1)
                mTabLayout!!.addTab(mTabLayout!!.newTab().setText(dataTime[i-1].getStart_date_time().trim()))
            else if(checkedItem[0]==2)
                mTabLayout!!.addTab(mTabLayout!!.newTab().setText(dataLocation[i-1].getLocation_name().trim()))
            else if(dataCategory.isNotEmpty())
                mTabLayout!!.addTab(mTabLayout!!.newTab().setText(dataCategory[i-1].getCategories().trim()))
            else
                mTabLayout!!.addTab(mTabLayout!!.newTab().setText("Page: $i"))

        }

        val name: List<String> = if(checkedItem[0]==1)
            dataTime.map { it.getStart_date_time() }
        else if(checkedItem[0]==2)
            dataLocation.map { it.getLocation_name() }
        else
            dataCategory.map { it.getCategories() }

        val mDynamicFragmentAdapter = mTabLayout?.let {
            DynamicFragmentAdapter(
                supportFragmentManager,
                lifecycle,
                this@AgendaActivity,
                it.tabCount,
                classification,
                name
            )
        }

        // set the adapter
        viewPager!!.adapter = mDynamicFragmentAdapter

        viewPager!!.currentItem = 0
        mTabLayout?.let {
            TabLayoutMediator(
                it, viewPager!!, true, true
            ) { tab, position ->
                if(checkedItem[0]==1)
                    tab.text = dataTime[position].getStart_date_time().trim()
                else if(checkedItem[0]==2)
                    tab.text = dataLocation[position].getLocation_name().trim()
                else if(dataCategory.isNotEmpty())
                    tab.text = dataCategory[position].getCategories().trim()
                else
                    tab.text = "Tab $position"
            }
        }?.attach()
    }

    private fun fetchAgendaData () {
        val agendaApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        lifecycleScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            agendaApi.getAgendaClassificationTime("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<TimeResponse?> {
                override fun onResponse(
                    call: Call<TimeResponse?>,
                    response: Response<TimeResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    dataTime = response.body()?.data as ArrayList<Time>

                }

                override fun onFailure(call: Call<TimeResponse?>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message,
                        Toast.LENGTH_SHORT).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            })
            agendaApi.getAgendaClassificationLocation("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<LocationResponse?> {
                override fun onResponse(
                    call: Call<LocationResponse?>,
                    response: Response<LocationResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    dataLocation = response.body()?.data as ArrayList<Location>
                }

                override fun onFailure(call: Call<LocationResponse?>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message,
                        Toast.LENGTH_SHORT).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            })
            agendaApi.getAgendaClassificationCategory("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<CategoryResponse?> {
                override fun onResponse(
                    call: Call<CategoryResponse?>,
                    response: Response<CategoryResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    dataCategory = response.body()?.data as ArrayList<Categories>

                }

                override fun onFailure(call: Call<CategoryResponse?>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message,
                        Toast.LENGTH_SHORT).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            })

        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }


}