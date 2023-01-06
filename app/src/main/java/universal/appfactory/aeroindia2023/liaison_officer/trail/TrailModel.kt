package universal.appfactory.aeroindia2023.liaison_officer.trail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trail")
class TrailModel(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private var id: Int,

    @ColumnInfo(name = "status")
    @SerializedName("status")
    private var status: String,

    @ColumnInfo(name = "sort_order")
    @SerializedName("sort_order")
    private var sort_order: Int,

    @ColumnInfo(name = "show_in_organiser_dashboard")
    @SerializedName("show_in_organiser_dashboard")
    private var show_in_organiser_dashboard: Int,

    @ColumnInfo(name = "createdAt")
    @SerializedName("createdAt")
    private var createdAt: String,

    @ColumnInfo(name = "updatedAt")
    @SerializedName("updatedAt")
    private var updatedAt: String,
){
    fun getId(): Int{
        return id
    }

    fun getStatus() : String{
        return status
    }

    fun getSort_order() : Int{
        return sort_order
    }

    fun getShow_in_organiser_dashboard() : Int{
        return show_in_organiser_dashboard
    }

    fun getCreatedAt() : String{
        return createdAt
    }

    fun getUpdatedAt(): String{
        return updatedAt
    }
}
