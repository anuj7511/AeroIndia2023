package universal.appfactory.aeroindia2023.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private var id: Int,
    @ColumnInfo(name = "event_id")
    @SerializedName("event_id")
    private var event_id: String,
    @ColumnInfo(name = "product_title")
    @SerializedName("product_title")
    private var product_title: String,
    @ColumnInfo(name = "exhibitor_id")
    @SerializedName("exhibitor_id")
    private var exhibitor_id: String,
    @ColumnInfo(name = "product_image")
    @SerializedName("product_image")
    private var product_image: String,
    @ColumnInfo(name = "hall_stall")
    @SerializedName("hall_stall")
    private var hall_stall: String,
    @ColumnInfo(name = "weight")
    @SerializedName("weight")
    private var weight: String,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    private var description: String,
    @ColumnInfo(name = "created")
    @SerializedName("created")
    private var created: String,
    @ColumnInfo(name = "first_category")
    @SerializedName("first_category")
    private var first_category: String,
    @ColumnInfo(name = "second_categoy")
    @SerializedName("second_categoy")
    private var second_categoy: String,
    @ColumnInfo(name = "third_categoy")
    @SerializedName("third_categoy")
    private var third_categoy: String,
    @ColumnInfo(name = "updated")
    @SerializedName("updated")
    private var updated: String,
)
{
    fun getId(): Int{
        return id
    }

    fun getEvent_id(): String{
        return event_id
    }

    fun getProduct_title(): String {
        return product_title
    }

    fun getExhibitor_id(): String {
        return exhibitor_id
    }

    fun getProduct_image(): String {
        return product_image
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
        return first_category
    }

    fun getSecond_categoy(): String {
        return second_categoy
    }

    fun getThird_categoy(): String {
        return third_categoy
    }

    fun getUpdated(): String {
        return updated
    }
}
