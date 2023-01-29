package universal.appfactory.aeroindia2023.Notifications

data class NotificationContentLanguages(
    var en: String = "Default message"
){
    fun setMessage(message: String){
        en = message
    }
}
