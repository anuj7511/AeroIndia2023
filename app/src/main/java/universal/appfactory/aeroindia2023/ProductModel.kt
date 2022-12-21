package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id")
    private var id: Int,
    @SerializedName("event_id")
    private var event_id: String,
    @SerializedName("product_title")
    private var product_title: String,
    @SerializedName("exhibitor_name")
    private var exhibitor_name: String,
    @SerializedName("product_image")
    private var product_image: String,
    @SerializedName("hall_stall")
    private var hall_stall: String,
    @SerializedName("weight")
    private var weight: String,
    @SerializedName("description")
    private var description: String,
    @SerializedName("created")
    private var created: String,
    @SerializedName("first_category")
    private var first_category: String,
    @SerializedName("second_categoy")
    private var second_categoy: String,
    @SerializedName("third_categoy")
    private var third_categoy: String,
    @SerializedName("updated")
    private var updated: String,
)
{

    fun getProductTitle(): String {
        return product_title
    }

    fun getExhibitorName(): String {
        return exhibitor_name
    }

    fun getProductImage(): String {
        return product_image
    }

    fun getDescription(): String {
        return description
    }

    fun getFirstCategory(): String {
        return first_category
    }

    fun getSecondCategory(): String {
        return second_categoy
    }

    fun getThirdCategory(): String {
        return third_categoy
    }
}
