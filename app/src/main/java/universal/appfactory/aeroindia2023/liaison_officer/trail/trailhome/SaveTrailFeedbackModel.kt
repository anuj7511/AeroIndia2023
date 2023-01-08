package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome

data class SaveTrailFeedbackModel(
    var liaisonId :Int,
    var DelegateId: Int,
    var status: String,
    var remarks: String,
)