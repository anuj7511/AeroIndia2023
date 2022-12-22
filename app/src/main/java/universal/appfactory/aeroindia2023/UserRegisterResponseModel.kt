package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserRegisterResponseModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("verify")
    val verify: Int,
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("errors")
    val errors: RegisterErrorResponse
)
