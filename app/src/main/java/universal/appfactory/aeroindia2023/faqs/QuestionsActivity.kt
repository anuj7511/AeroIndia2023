package universal.appfactory.aeroindia2023.faqs

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_agenda.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile_info.*
import kotlinx.android.synthetic.main.activity_questions.*
import kotlinx.android.synthetic.main.activity_questions.categoryTitle
import kotlinx.android.synthetic.main.activity_questions.refreshLayout
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.navigableBundle


class QuestionsActivity : AppCompatActivity() {

    private lateinit var adapter: QuestionsAdapter
    private lateinit var  data : ArrayList<FAQsModel>
    private lateinit var viewModel : FaqsViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        navigableBundle = intent.extras!!
        val userType = navigableBundle.getString("userType","0")
        reach_out_questions.setOnClickListener {
            val intent = Intent(this, ReachOutActivity::class.java)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this)[FaqsViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)
        recyclerView = questions_recycler_view
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        data = ArrayList()

        viewModel.allFaqs.observe(this){
            data = it as ArrayList<FAQsModel>
            adapter = QuestionsAdapter(data, this@QuestionsActivity)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        refreshLayout.setOnRefreshListener {
            viewModel.loadAllFaqs(true, userType)
            refreshLayout.isRefreshing = false
        }
    }
}