package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ExhibitorModel(
                        @SerializedName("id")
                        private var id: Int,
                        @SerializedName("event_id")
                        private var event_id: String,
                        @SerializedName("hall_no")
                        private var hall_no: String,
                        @SerializedName("stall_no")
                        private var stall_no: Int,
                        @SerializedName("company_ref_no")
                        private var company_ref_no: String,
                        @SerializedName("name")
                        private var name: String,
                        @SerializedName("address")
                        private var address: String,
                        @SerializedName("Tel_country_code")
                        private var Tel_country_code: String,
                        @SerializedName("tel_phone_no")
                        private var tel_phone_no: String,
                        @SerializedName("fax_xountry_code")
                        private var fax_xountry_code: String,
                        @SerializedName("fax_no")
                        private var fax_no: String,
                        @SerializedName("company_email")
                        private var company_email: String,
                        @SerializedName("website")
                        private var website: String,
                        @SerializedName("company_head_name")
                        private var company_head_name: String,
                        @SerializedName("company_head_designation")
                        private var company_head_designation: String,
                        @SerializedName("Company_head_email")
                        private var Company_head_email: String,
                        @SerializedName("Contact_person_name (Nodal Officer) ")
                        private var Contact_person_name: String,
                        @SerializedName("contact_person")
                        private var contact_person: String,
                        @SerializedName("company_profile")
                        private var company_profile: String,
                        @SerializedName("extended_profile")
                        private var extended_profile: String,
                        @SerializedName("payment_type")
                        private var payment_type: String,
                        @SerializedName("exhibitor_type")
                        private var exhibitor_type: String,
                        @SerializedName("logo")
                        private var logo: String,
                        @SerializedName("catalogu_filled")
                        private var catalogu_filled: String,
                        ) {
    fun getHallNo(): String {
        return hall_no
    }

    fun getStallNo(): Int {
        return stall_no
    }

    fun getName(): String {
        return name
    }

    fun getAddress(): String {
        return address
    }

}