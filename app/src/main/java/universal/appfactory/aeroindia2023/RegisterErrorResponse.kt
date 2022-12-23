package universal.appfactory.aeroindia2023

data class RegisterErrorResponse(
    val name: Array<String>? = arrayOf("Success"),
    val phone_no: Array<String>? = arrayOf("Success"),
    val email_id: Array<String>? = arrayOf("Success"),
)
