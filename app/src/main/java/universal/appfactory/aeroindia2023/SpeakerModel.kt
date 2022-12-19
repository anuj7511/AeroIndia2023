package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class SpeakerModel(
                        @SerializedName("id")
                        private var id: Int,
                        @SerializedName("agenda_id")
                        private var agenda_id: Int,
                        @SerializedName("salutation")
                        private var salutation: String,
                        @SerializedName("first_name")
                        private var first_name: String,
                        @SerializedName("last_name")
                        private var last_name: String,
                        @SerializedName("title")
                        private var title: String,
                        @SerializedName("company")
                        private var company: String,
                        @SerializedName("biography")
                        private var biography: String,
                        @SerializedName("connected_session_name")
                        private var connected_session_name: String,
                        @SerializedName("profile_picture_link")
                        private var profile_picture_link: String) {

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getAgendaId(): Int {
        return agenda_id
    }

    fun setAgendaId(agendaId: Int) {
        this.agenda_id = agendaId
    }

    fun getSalutation(): String {
        return salutation
    }

    fun setSalutation(salutation: String) {
        this.salutation = salutation
    }

    fun getFirstName(): String {
        return first_name
    }

    fun setFirstName(firstName: String) {
        this.first_name = firstName
    }

    fun getLastName(): String {
        return last_name
    }

    fun setLastName(lastName: String) {
        this.last_name = lastName
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getCompany(): String {
        return company
    }

    fun setCompany(company: String) {
        this.company = company
    }

    fun getBiography(): String {
        return biography
    }

    fun setBiography(biography: String) {
        this.biography = biography
    }

    fun getConnectedSessionName(): String {
        return connected_session_name
    }

    fun setConnectedSessionName(connectedSessionName: String) {
        this.connected_session_name = connectedSessionName
    }

    fun getProfilePictureLink(): String {
        return profile_picture_link
    }

    fun setProfilePictureLink(profilePictureLink: String) {
        this.profile_picture_link = profilePictureLink
    }


}