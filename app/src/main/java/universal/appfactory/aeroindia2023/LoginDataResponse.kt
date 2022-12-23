package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class LoginDataResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email_id")
    val email_id: String,
    @SerializedName("phone_no")
    val phone_no: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("pin")
    val pin: String,
    @SerializedName("user_type")
    val user_type: Int,
    )
