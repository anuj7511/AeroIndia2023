package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ExhibitorModel(
                        @SerializedName("id")
                        private var id: Int,
                        @SerializedName("role_id")
                        private var role_id: Int,
                        @SerializedName("usertype")
                        private var usertype: String,
                        @SerializedName("firstname")
                        private var firstname: String,
                        @SerializedName("lastname")
                        private var lastname: String,
                        @SerializedName("companyname")
                        private var companyname: String,
                        @SerializedName("email")
                        private var email: String,
                        @SerializedName("email_verified_at")
                        private var email_verified_at: String,
                        @SerializedName("password")
                        private var password: String,
                        @SerializedName("mobile")
                        private var mobile: String,
                        @SerializedName("fax")
                        private var fax: String,
                        @SerializedName("companywebsite")
                        private var companywebsite: String,
                        @SerializedName("gender")
                        private var gender: String,
                        @SerializedName("address")
                        private var address: String,
                        @SerializedName("dob")
                        private var dob: String,
                        @SerializedName("image")
                        private var image: String,
                        @SerializedName("city")
                        private var city: String,
                        @SerializedName("pincode")
                        private var pincode: String,
                        @SerializedName("country_id")
                        private var country_id: String,
                        @SerializedName("country")
                        private var country: String,
                        @SerializedName("state_id")
                        private var state_id: String,
                        @SerializedName("state")
                        private var state: String,
                        @SerializedName("mobileverify")
                        private var mobileverify: String,
                        @SerializedName("mobileauthcode")
                        private var mobileauthcode: String,
                        @SerializedName("remember_token")
                        private var remember_token: String,
                        @SerializedName("status")
                        private var status: String,
                        @SerializedName("msme")
                        private var msme: String,
                        @SerializedName("msme_type")
                        private var msme_type: String,
                        @SerializedName("msme_approved")
                        private var msme_approved: String,
                        @SerializedName("adhaar")
                        private var adhaar: String,
                        @SerializedName("msmecertificate")
                        private var msmecertificate: String,
                        @SerializedName("adhaarfile")
                        private var adhaarfile: String,
                        @SerializedName("gst_no")
                        private var gst_no: String,
                        @SerializedName("pan_no")
                        private var pan_no: String,
                        @SerializedName("exhibitor_type")
                        private var exhibitor_type: String,
                        @SerializedName("paid_reg_fee")
                        private var paid_reg_fee: String,
                        @SerializedName("created_at")
                        private var created_at: String,
                        @SerializedName("updated_at")
                        private var updated_at: String,
                        @SerializedName("register_type")
                        private var register_type: String,
                        @SerializedName("media_partner")
                        private var media_partner: String,
                        @SerializedName("media_partner_reg_reason")
                        private var media_partner_reg_reason: String,
                        @SerializedName("refrenceno")
                        private var refrenceno: String,
                        @SerializedName("year")
                        private var year: String,
                        @SerializedName("possession_status")
                        private var possession_status: Int,
                        @SerializedName("verification_code")
                        private var verification_code: String,
                        @SerializedName("updated_ip")
                        private var updated_ip: String,
                        @SerializedName("otp_count")
                        private var otp_count: Int,
                        @SerializedName("stand_no")
                        private var stand_no: String,
                        @SerializedName("participating_company")
                        private var participating_company: String,
                        @SerializedName("exhibitor_logo")
                        private var exhibitor_logo: String,
                        @SerializedName("address_one")
                        private var address_one: String,
                        @SerializedName("msme_appreg_by")
                        private var msme_appreg_by: String,
                        @SerializedName("msme_appreg_at")
                        private var msme_appreg_at: Int,
                        @SerializedName("msme_appreg_ip")
                        private var msme_appreg_ip: String,
                        @SerializedName("address_two")
                        private var address_two: String,
                        @SerializedName("designation")
                        private var designation: String,
                        @SerializedName("contact_person")
                        private var contact_person: String,
                        @SerializedName("razorpay_order_id")
                        private var razorpay_order_id: String,
                        @SerializedName("contact_person_designation")
                        private var contact_person_designation: String,
                        @SerializedName("company_profile")
                        private var company_profile: String,
                        @SerializedName("account_lock")
                        private var account_lock: String,
                        @SerializedName("user_token")
                        private var user_token: String,

                        ) {

    fun getFirstName(): String {
        return firstname
    }
    fun getLastName(): String {
        return lastname
    }

    fun getAddress(): String {
        return address
    }

    fun getCountry(): String {
        return country
    }

    fun getEmail(): String {
        return email
    }

    fun getMobile(): String {
        return mobile
    }

    fun getCompanyWebsite(): String {
        return companywebsite
    }

    fun getCompanyName(): String {
        return companyname
    }

    fun getExhibitorLogo(): String {
        return exhibitor_logo
    }

}