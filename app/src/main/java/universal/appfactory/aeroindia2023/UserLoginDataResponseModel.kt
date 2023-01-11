package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserLoginDataResponseModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("auth_token")
    val authToken: String="default_token_as_string",
    @SerializedName("message")
    val message: LoginMessageResponse,
    @SerializedName("errors")
    val errors: LoginErrorResponse
)
