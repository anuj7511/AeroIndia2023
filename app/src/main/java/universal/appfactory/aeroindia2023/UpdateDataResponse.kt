package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UpdateDataResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone_no")
    val phone_no: String,
    @SerializedName("organisation")
    val organisation: String,
    @SerializedName("profile_image")
    val profile_image: String
)
