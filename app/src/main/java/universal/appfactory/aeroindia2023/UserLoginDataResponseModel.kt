package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserLoginDataResponseModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: LoginMessageResponse,
    @SerializedName("errors")
    val errors: LoginErrorResponse
)
