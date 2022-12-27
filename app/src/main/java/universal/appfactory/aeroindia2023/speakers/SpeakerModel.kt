package universal.appfactory.aeroindia2023.speakers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "speakers")
data class SpeakerModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private var id: Int,
    @ColumnInfo(name = "agenda_id")
    @SerializedName("agenda_id")
    private var agenda_id: Int,
    @ColumnInfo(name = "salutation")
    @SerializedName("salutation")
    private var salutation: String,
    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    private var first_name: String,
    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    private var last_name: String,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private var title: String,
    @ColumnInfo(name = "company")
    @SerializedName("company")
    private var company: String,
    @ColumnInfo(name = "biography")
    @SerializedName("biography")
    private var biography: String,
    @ColumnInfo(name = "connected_session_name")
    @SerializedName("connected_session_name")
    private var connected_session_name: String,
    @ColumnInfo(name = "profile_picture_link")
    @SerializedName("profile_picture_link")
    private var profile_picture_link: String
) {

    fun getId(): Int {
        return id
    }

    fun getAgenda_id(): Int {
        return agenda_id
    }

    fun getSalutation(): String {
        return salutation
    }

    fun getFirst_name(): String {
        return first_name
    }

    fun getLast_name(): String {
        return last_name
    }

    fun getTitle(): String {
        return title
    }

    fun getCompany(): String {
        return company
    }

    fun getBiography(): String {
        return biography
    }

    fun getConnected_session_name(): String {
        return connected_session_name
    }

    fun getProfile_picture_link(): String {
        return profile_picture_link
    }

}