package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserInfoUpdateResponseModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: UpdateDataResponse,
    @SerializedName("errors")
    val errors: UpdateErrorResponse
)
