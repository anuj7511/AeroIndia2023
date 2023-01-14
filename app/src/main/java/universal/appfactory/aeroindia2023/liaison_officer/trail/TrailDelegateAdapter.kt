package universal.appfactory.aeroindia2023.liaison_officer.trail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.delegation_view.view.*
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonModel

class TrailDelegateAdapter(list_of_delegates : ArrayList<LiaisonModel>,private var liaisonId : String, private var userName : String):RecyclerView.Adapter<TrailDelegateAdapter.MyViewHolder>() {
    private var listOfDelegates : ArrayList<LiaisonModel>
    private lateinit var context : Context
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var delegation_name = itemView.delegation_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.delegation_view,parent,false)
        context = parent.context
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var itemViewHolder = listOfDelegates[position]
        holder.delegation_name.text = itemViewHolder.getDelegate_first_name()+" "+itemViewHolder.getDelegate_last_name()

        holder.itemView.setOnClickListener {
            var intent = Intent(context, TrailActivity::class.java)
            intent.putExtra("userName",userName)
            intent.putExtra("liaisonId",liaisonId)
            intent.putExtra("delegateId",itemViewHolder.getDelegate_id())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listOfDelegates.size
    }

    init {
        this.listOfDelegates= list_of_delegates
    }
}