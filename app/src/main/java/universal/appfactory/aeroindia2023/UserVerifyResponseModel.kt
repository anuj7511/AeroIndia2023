package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserVerifyResponseModel(

    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: UserVerificationMessage,
    @SerializedName("data")
    val data: LoginDataResponse,
    @SerializedName("error")
    val errors: VerifyErrorResponse
)
