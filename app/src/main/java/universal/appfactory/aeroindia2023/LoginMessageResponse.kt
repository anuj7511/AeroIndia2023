package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class LoginMessageResponse(
    @SerializedName("verification_code")
    val verification_code: String,
    @SerializedName("user_id")
    val user_id: Int,
)
