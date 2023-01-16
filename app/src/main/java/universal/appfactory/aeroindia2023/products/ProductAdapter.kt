package universal.appfactory.aeroindia2023.products

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import universal.appfactory.aeroindia2023.R


class ProductAdapter(private val mList: ArrayList<ProductModel>, private val mContext: Context, private val category: String) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var productModelArrayList: ArrayList<ProductModel>

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<ProductModel>) {
        // below line is to add our filtered
        // list in our course array list.
        productModelArrayList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = productModelArrayList[position]

        // sets the text to the textview from our itemHolder class
        holder.nameText.text = when (category) {
            "second" -> itemsViewModel.getSecond_categoy()
            "third" -> itemsViewModel.getThird_categoy()
            "last" -> itemsViewModel.getProduct_title()
            else -> itemsViewModel.getFirst_category()
        }
        holder.numberText.text = when (category) {
            "second" -> mList.count { it.getSecond_categoy() == itemsViewModel.getSecond_categoy() }.toString()
            "third" -> mList.count { it.getThird_categoy() == itemsViewModel.getThird_categoy() }.toString()
            "last" -> ""
            else -> mList.count { it.getFirst_category() == itemsViewModel.getFirst_category() }.toString()
        }
        if(category=="last")
            holder.numberText.visibility = View.INVISIBLE

        holder.nextCard.setOnClickListener{
            when (category) {
                "second" -> {
                    val intent = Intent(mContext, ProductsActivity::class.java)
                    if(itemsViewModel.getThird_categoy().isNullOrEmpty())
                    {
                        intent.putExtra("Category","last")
                        intent.putExtra("Last Category", "second")
                        intent.putExtra("Name",itemsViewModel.getSecond_categoy())
                    }
                    else
                    {
                        intent.putExtra("Category","third")
                        intent.putExtra("Name",itemsViewModel.getSecond_categoy())
                    }
                    mContext.startActivity(intent)
                }
                "third" -> {
                    val intent = Intent(mContext, ProductsActivity::class.java)
                    intent.putExtra("Category","last")
                    intent.putExtra("Last Category", "third")
                    intent.putExtra("Name",itemsViewModel.getThird_categoy())
                    mContext.startActivity(intent)
                }
                "last" -> intentToSelectedProduct(itemsViewModel)
                else -> {
                    val intent = Intent(mContext, ProductsActivity::class.java)
                    if(itemsViewModel.getSecond_categoy().isNullOrEmpty())
                    {
                        intent.putExtra("Category","last")
                        intent.putExtra("Last Category", "first")
                        intent.putExtra("Name",itemsViewModel.getFirst_category())
                    }
                    else
                    {
                        intent.putExtra("Category","second")
                        intent.putExtra("Name",itemsViewModel.getFirst_category())
                    }
                    mContext.startActivity(intent)
                }
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return productModelArrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var nameText: TextView = itemView.findViewById(R.id.product_name)
        var numberText: TextView = itemView.findViewById(R.id.number)
        val nextCard: CardView = itemView.findViewById(R.id.product_card)

    }

    // creating a constructor for our variables.
    init {
        this.productModelArrayList = when (category) {
            "second" -> mList.distinctBy { it.getSecond_categoy() } as ArrayList<ProductModel>
            "third" -> mList.distinctBy { it.getThird_categoy() } as ArrayList<ProductModel>
            "last" -> mList
            else -> mList.distinctBy { it.getFirst_category() } as ArrayList<ProductModel>
        }
    }

    private fun intentToSelectedProduct(itemsViewModel: ProductModel) {
        val intent = Intent(mContext, SelectedProductActivity::class.java)
        intent.putExtra("Name",itemsViewModel.getProduct_title())
        intent.putExtra("Category",itemsViewModel.getFirst_category())
        intent.putExtra("Exhibitor",itemsViewModel.getExhibitor_id())
        intent.putExtra("Description",itemsViewModel.getDescription())
        intent.putExtra("Image",itemsViewModel.getProduct_image())
        mContext.startActivity(intent)
    }

}