package universal.appfactory.aeroindia2023

data class RegisterErrorResponse(
    // Was previously Array<String>

    val name: String = "0",
    val phone_no: String = "0",
    val email_id: String = "0",
)

// 0 -> Zero denotes no error