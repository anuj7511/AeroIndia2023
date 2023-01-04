package universal.appfactory.aeroindia2023.delegate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "delegate")
class DelegateModel(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private var id: Int,

    @ColumnInfo(name = "delegation_name")
    @SerializedName("delegation_name")
    private var delegation_name: String,

    @ColumnInfo(name = "delegate_first_name")
    @SerializedName("delegate_first_name")
    private var delegate_first_name: String,

    @ColumnInfo(name = "delegate_last_name")
    @SerializedName("delegate_last_name")
    private var delegate_last_name: String,

    @ColumnInfo(name = "ddelegate_email")
    @SerializedName("ddelegate_email")
    private var ddelegate_email: String,

    @ColumnInfo(name = "delegate_mobile")
    @SerializedName("delegate_mobile")
    private var delegate_mobile: String,

    @ColumnInfo(name = "hotel_name")
    @SerializedName("hotel_name")
    private var hotel_name: String,

    @ColumnInfo(name = "star_rating")
    @SerializedName("star_rating")
    private var star_rating: String,

    @ColumnInfo(name = "hotel_contact")
    @SerializedName("hotel_contact")
    private var hotel_contact: String,

    @ColumnInfo(name = "hotel_address")
    @SerializedName("hotel_address")
    private var hotel_address: String,

    @ColumnInfo(name = "hotel_city")
    @SerializedName("hotel_city")
    private var hotel_city: String,

    @ColumnInfo(name = "liason_ooficer")
    @SerializedName("liason_ooficer")
    private var liason_ooficer: String,

    @ColumnInfo(name = "LO_organisation")
    @SerializedName("LO_organisation")
    private var LO_organisation: String,

    @ColumnInfo(name = "LO_designation")
    @SerializedName("LO_designation")
    private var LO_designation: String,

    @ColumnInfo(name = "LO_mobile")
    @SerializedName("LO_mobile")
    private var LO_mobile: String,

    @ColumnInfo(name = "LO_gender")
    @SerializedName("LO_gender")
    private var LO_gender: String,

    @ColumnInfo(name = "LO_email")
    @SerializedName("LO_email")
    private var LO_email: String,

    @ColumnInfo(name = "vehicle_number")
    @SerializedName("vehicle_number")
    private var vehicle_number: String,

    @ColumnInfo(name = "vehicle_colour")
    @SerializedName("vehicle_colour")
    private var vehicle_colour: String,

    @ColumnInfo(name = "driver_name")
    @SerializedName("driver_name")
    private var driver_name: String,

    @ColumnInfo(name = "driver_mobile_number")
    @SerializedName("driver_mobile_number")
    private var driver_mobile_number: String,

    ) {

    fun getId(): Int {
        return id
    }

    fun getDelegation_name(): String {
        return delegation_name
    }

    fun getDelegate_first_name(): String {
        return delegate_first_name
    }

    fun getDelegate_last_name(): String {
        return delegate_last_name
    }

    fun getDdelegate_email(): String {
        return ddelegate_email
    }

    fun getDelegate_mobile(): String {
        return delegate_mobile
    }

    fun getHotel_name(): String {
        return hotel_name
    }

    fun getStar_rating(): String {
        return star_rating
    }

    fun getHotel_contact(): String {
        return hotel_contact
    }

    fun getHotel_address(): String {
        return hotel_address
    }

    fun getHotel_city(): String {
        return hotel_city
    }

    fun getLiason_ooficer(): String {
        return liason_ooficer
    }

    fun getLO_organisation(): String {
        return LO_organisation
    }

    fun getLO_designation(): String {
        return LO_designation
    }

    fun getLO_mobile(): String {
        return LO_mobile
    }

    fun getLO_gender(): String {
        return LO_gender
    }

    fun getLO_email(): String {
        return LO_email
    }

    fun getVehicle_number(): String {
        return vehicle_number
    }

    fun getVehicle_colour(): String {
        return vehicle_colour
    }

    fun getDriver_name(): String {
        return driver_name
    }

    fun getDiver_mobile_number(): String {
        return driver_mobile_number
    }

}