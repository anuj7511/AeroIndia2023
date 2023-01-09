package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome

data class SaveTrailFeedbackModel(
    var liaison_officer_id :Int,
    var delegate_id: Int,
    var trail_status: String,
    var remarks: String,
    var entered_by :String
)