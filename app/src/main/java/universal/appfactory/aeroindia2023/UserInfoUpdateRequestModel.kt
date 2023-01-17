package universal.appfactory.aeroindia2023

data class UserInfoUpdateRequestModel(
    val name: String,
    val id: Int,
    val designation: String? = "",
    val address: String? = "",
    val phone_no: String? = "",
    val organisation: String? = "",
    val profile_image: String? = ""
)
