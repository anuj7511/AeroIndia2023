package universal.appfactory.aeroindia2023.Notifications

data class NotificationRequestModel (
    val included_segments: String = "Subscribed Users",
    val app_id: String = "1c53bac3-8c5c-4c3a-9cd2-38558dced0cf",
    val headings: NotificationHeaderLanguages = NotificationHeaderLanguages(),
    val contents: NotificationContentLanguages = NotificationContentLanguages()
)