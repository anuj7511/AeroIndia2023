package universal.appfactory.aeroindia2023.faqs

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.question_view.view.*
import universal.appfactory.aeroindia2023.R

class QuestionsAdapter( list_of_questions: ArrayList<FAQsModel>, private val context : Context) : RecyclerView.Adapter<QuestionsAdapter.myViewHolder>() {

    private var listOfQuestions : ArrayList<FAQsModel>


    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var question: TextView = itemView.question
        var pid = -1;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.question_view,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        Log.i("list_position",position.toString())
        val itemsViewModel = listOfQuestions[position]
        holder.question.text=  itemsViewModel.getFaq_question()


        holder.itemView.setOnClickListener{
            var intent = Intent(context,FAQsAnswerActivity::class.java)

            intent.putExtra("answer",itemsViewModel.getFaq_answer())

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        Log.i("Total_list_count",listOfQuestions.size.toString())
        return listOfQuestions.size
    }

    init {
        this.listOfQuestions= list_of_questions
    }


}