package universal.appfactory.aeroindia2023

data class VerifyErrorResponse(
    val pin: String = "No PIN error",
    val id: String = "No user ID error"
)
