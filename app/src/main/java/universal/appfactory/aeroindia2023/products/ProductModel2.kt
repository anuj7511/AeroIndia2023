package universal.appfactory.aeroindia2023.products

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class ProductModel2(
    @PrimaryKey
    @SerializedName("product_id")
    private var product_id: Int,
    @SerializedName("fk_event_id")
    private var fk_event_id: Int,
    @SerializedName("fk_exhibitor_id")
    private var fk_exhibitor_id: Int,
    @SerializedName("product_title")
    private var product_title: String,
    @SerializedName("static_display")
    private var static_display: String,
    @SerializedName("img")
    private var img: String,
    @SerializedName("sort_order")
    private var sort_order: Int,
    @SerializedName("lat_long")
    private var lat_long: String,
    @SerializedName("hall_stall")
    private var hall_stall: String,
    @SerializedName("weight")
    private var weight: String,
    @SerializedName("dimensions")
    private var dimensions: String,
    @SerializedName("description")
    private var description: String,
    @SerializedName("category_id")
    private var category_id: Int,
    @SerializedName("sub_category_id")
    private var sub_category_id: Int,
    @SerializedName("subsub_category_id")
    private var subsub_category_id: Int,
    @SerializedName("created")
    private var created: String,
    @SerializedName("modified")
    private var modified: String,
    @SerializedName("status")
    private var status: String,
    @SerializedName("category")
    private var category: String,
    @SerializedName("subcategory")
    private var subcategory: String,
    @SerializedName("subsubcategory")
    private var subsubcategory: String,
    @SerializedName("user_firstname")
    private var user_firstname: String,
    @SerializedName("user_lastname")
    private var user_lastname: String,
)
{
    fun getId(): Int{
        return product_id
    }

    fun getEvent_id(): Int{
        return fk_event_id
    }

    fun getProduct_title(): String {
        return product_title
    }

    fun getExhibitor_id(): Int {
        return fk_exhibitor_id
    }

    fun getProduct_image(): String {
        return img
    }

    fun getHall_stall(): String {
        return hall_stall
    }

    fun getWeight(): String {
        return weight
    }

    fun getDescription(): String {
        return description
    }

    fun getCreated(): String {
        return created
    }

    fun getFirst_category(): String {
        return category
    }

    fun getSecond_categoy(): String {
        return subcategory
    }

    fun getThird_categoy(): String {
        return subsubcategory
    }

}
