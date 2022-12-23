package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserVerifyResponseModel(

    @SerializedName("status")
    val status: String,
    @SerializedName("error")
    val errors: VerifyErrorResponse
)
