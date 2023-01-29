package universal.appfactory.aeroindia2023.Notifications

data class NotificationHeaderLanguages(
    var en: String = "Default header"
) {
    fun setHeader(header: String){
        en = header
    }
}
