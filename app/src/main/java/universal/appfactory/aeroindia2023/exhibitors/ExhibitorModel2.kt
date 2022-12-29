package universal.appfactory.aeroindia2023.exhibitors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "exhibitors")
data class ExhibitorModel2(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var id: Int,
    @ColumnInfo(name = "role_id")
    private var role_id: Int,
    @ColumnInfo(name = "usertype")
    @SerializedName("usertype")
    private var usertype: String,
    @ColumnInfo(name = "firstname")
    @SerializedName("firstname")
    private var firstname: String,
    @ColumnInfo(name = "lastname")
    @SerializedName("lastname")
    private var lastname: String,
    @ColumnInfo(name = "companyname")
    @SerializedName("companyname")
    private var companyname: String,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    private var email: String,
    @ColumnInfo(name = "mobile")
    @SerializedName("mobile")
    private var mobile: String,
    @ColumnInfo(name = "companywebsite")
    @SerializedName("companywebsite")
    private var companywebsite: String,
    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    private var gender: String,
    @ColumnInfo(name = "address")
    @SerializedName("address")
    private var address: String,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    private var image: String,
    @ColumnInfo(name = "city")
    @SerializedName("city")
    private var city: String,
    @ColumnInfo(name = "country_id")
    @SerializedName("country_id")
    private var country_id: Int,
    @ColumnInfo(name = "country")
    @SerializedName("country")
    private var country: String,
    @ColumnInfo(name = "state_id")
    @SerializedName("state_id")
    private var state_id: Int,
    @ColumnInfo(name = "state")
    @SerializedName("state")
    private var state: String,
    @ColumnInfo(name = "exhibitor_type")
    @SerializedName("exhibitor_type")
    private var exhibitor_type: String,
    @ColumnInfo(name = "year")
    @SerializedName("year")
    private var year: Int,
    @ColumnInfo(name = "stand_no")
    @SerializedName("stand_no")
    private var stand_no: String,
    @ColumnInfo(name = "exhibitor_logo")
    @SerializedName("exhibitor_logo")
    private var exhibitor_logo: String,
    @ColumnInfo(name = "address_one")
    @SerializedName("address_one")
    private var address_one: String,
    @ColumnInfo(name = "address_two")
    @SerializedName("address_two")
    private var address_two: String,
    @ColumnInfo(name = "designation")
    @SerializedName("designation")
    private var designation: String,
    @ColumnInfo(name = "company_profile")
    @SerializedName("company_profile")
    private var company_profile: String,
    ) {

    fun getId() : Int {
        return id
    }

    fun getUserType() : String {
        return usertype
    }

    fun getFirstname(): String {
        return firstname
    }
    fun getLastname(): String {
        return lastname
    }

    fun getAddress(): String {
        return address
    }

    fun getCountry(): String {
        return country
    }

    fun getEmail(): String {
        return email
    }

    fun getMobile(): String {
        return mobile
    }

    fun getWebsite(): String {
        return companywebsite
    }

    fun getComp_Name(): String {
        return companyname
    }

    fun getLogo(): String {
        return exhibitor_logo
    }

    fun getHall_and_Stall_Number() : String {
        return stand_no
    }

    fun getCompany_Brief(): String {
        return company_profile
    }

    fun getExhibitorType(): String {
        return exhibitor_type
    }


    fun getAddress_Line_1(): String {
        return address_one
    }

    fun getAddress_Line_2(): String {
        return address_two
    }

    fun getCity(): String {
        return city
    }

    fun getState(): String {
        return state
    }
}