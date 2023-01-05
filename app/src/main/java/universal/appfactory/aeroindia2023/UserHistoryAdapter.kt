package universal.appfactory.aeroindia2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserHistoryAdapter(private val UList: ArrayList<UserHistoryModel> ) : RecyclerView.Adapter<UserHistoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = UList[position]
        // sets the text to the textview from our itemHolder class

        holder.manager.text= itemsViewModel.getmanager().trim()
        var x:Int=itemsViewModel.getStatus()

        val DT:String= itemsViewModel.getupdateDate().trim()
        val date:CharSequence=DT.subSequence(0,11)
        val time:String=DT.substring(11)
        holder.Updateddate.text=date
        holder.Updatedtime.text=time
        val CDT:String= itemsViewModel.getcreateDate().trim()
        val Cdate:CharSequence=CDT.subSequence(0,11)
        val Ctime:String=DT.substring(11)
        holder.Createddate.text=Cdate
        holder.Createdtime.text=Ctime
        holder.resolvedRemarks.text = itemsViewModel.getresolvedremarks().trim()



    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return UList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var manager:TextView = itemView.findViewById(R.id.Mgr)
        var Createddate:TextView = itemView.findViewById(R.id.Cdate)
        var Createdtime:TextView = itemView.findViewById(R.id.Ctime)
        var Updateddate:TextView = itemView.findViewById(R.id.Udate)
        var Updatedtime: TextView = itemView.findViewById(R.id.Utime)
        var resolvedRemarks: TextView = itemView.findViewById(R.id.ResolvedRemarks)



    }
}