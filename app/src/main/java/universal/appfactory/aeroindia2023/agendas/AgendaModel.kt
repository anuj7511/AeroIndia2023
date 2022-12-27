package universal.appfactory.aeroindia2023.agendas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "agendas")
class AgendaModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private var id: Int,
    @ColumnInfo(name = "session_name")
    @SerializedName("session_name")
    private var session_name: String,
    @ColumnInfo(name = "start_date_time")
    @SerializedName("start_date_time")
    private var start_date_time: String,
    @ColumnInfo(name = "end_date_time")
    @SerializedName("end_date_time")
    private var end_date_time: String,
    @ColumnInfo(name = "location_name")
    @SerializedName("location_name")
    private var location_name: String,
    @ColumnInfo(name = "categories")
    @SerializedName("categories")
    private var categories: String,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    private var description: String,
    @ColumnInfo(name = "speakers")
    @SerializedName("speakers")
    private var speakers: String,
    @ColumnInfo(name = "organiser")
    @SerializedName("organiser")
    private var organiser: String,
) {

    fun getId(): Int {
        return id
    }

    fun getSession_name(): String {
        return session_name
    }

    fun getStart_date_time(): String {
        return start_date_time
    }

    fun getEnd_date_time(): String {
        return end_date_time
    }

    fun getLocation_name(): String {
        return location_name
    }

    fun getCategories(): String {
        return categories
    }

    fun getDescription(): String {
        return description
    }

    fun getSpeakers(): String {
        return speakers
    }

    fun getOrganiser(): String {
        return organiser
    }

}
