package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ExhibitorModel(
    @SerializedName("Type")
    private var Type: String,
    @SerializedName("Firstname")
    private var Firstname: String,
    @SerializedName("Lastname")
    private var Lastname: String,
    @SerializedName("Comp_Name")
    private var Comp_Name: String,
    @SerializedName("Country")
    private var Country: String,
    @SerializedName("Email")
    private var Email: String,
    @SerializedName("Password")
    private var Password: String,
    @SerializedName("Mobile")
    private var Mobile: String,
    @SerializedName("Comp_ReferenceNo")
    private var Comp_ReferenceNo: String,
    @SerializedName("GST")
    private var GST: String,
    @SerializedName("PAN")
    private var PAN: String,
    @SerializedName("Website")
    private var Website: String,
    @SerializedName("Address")
    private var Address: String,
    @SerializedName("Token_Key")
    private var Token_Key: String,
    @SerializedName("ExhibitorType")
    private var ExhibitorType: String,
    @SerializedName("Badge_Quota")
    private var Badge_Quota: Int,
    @SerializedName("Area")
    private var Area: String,
    @SerializedName("HallNo")
    private var HallNo: String,
    @SerializedName("StallNo")
    private var StallNo: String,
    @SerializedName("CatalogueFlag")
    private var CatalogueFlag: Int,
    @SerializedName("short_company_name")
    private var short_company_name: String,
    @SerializedName("fax_country_code")
    private var fax_country_code: String,
    @SerializedName("exhibitor_fax")
    private var exhibitor_fax: String,
    @SerializedName("Nodal_Officer_Name")
    private var Nodal_Officer_Name: String,
    @SerializedName("Nodal_Officer_Email")
    private var Nodal_Officer_Email: String,
    @SerializedName("Nodal_Designation")
    private var Nodal_Designation: String,
    @SerializedName("Nodal_Country_Code")
    private var Nodal_Country_Code: String,
    @SerializedName("Nodal_Phone")
    private var Nodal_Phone: String,
    @SerializedName("Company_Head_Name")
    private var Company_Head_Name: String,
    @SerializedName("Company_Head_Email")
    private var Company_Head_Email: String,
    @SerializedName("Company_Head_Designation")
    private var Company_Head_Designation: String,
    @SerializedName("Company_Head_Country_Code")
    private var Company_Head_Country_Code: String,
    @SerializedName("Company_Head_Phone")
    private var Company_Head_Phone: String,
    @SerializedName("Login_Email")
    private var Login_Email: String,
    @SerializedName("Address_Line_1")
    private var Address_Line_1: String,
    @SerializedName("Address_Line_2")
    private var Address_Line_2: String,
    @SerializedName("City")
    private var City: String,
    @SerializedName("State")
    private var State: String,
    @SerializedName("Pin_Zip_Code")
    private var Pin_Zip_Code: String,
    @SerializedName("Category")
    private var Category: String,
    @SerializedName("Sub_Category")
    private var Sub_Category: String,
    @SerializedName("Sub_Sub_Category")
    private var Sub_Sub_Category: String,
    @SerializedName("Logo")
    private var Logo: String,
    @SerializedName("Hall_and_Stall_Number")
    private var Hall_and_Stall_Number: String,
    @SerializedName("Twitter")
    private var Twitter: String,
    @SerializedName("LinkedIn")
    private var LinkedIn: String,
    @SerializedName("Instagram")
    private var Instagram: String,
    @SerializedName("Facebook")
    private var Facebook: String,
    @SerializedName("Company_Brief")
    private var Company_Brief: String,
    @SerializedName("Submit_additional_150_words")
    private var Submit_additional_150_words: String,
    ) {

//    fun getId() : Int {
//        return id
//    }

    fun getFirstName(): String {
        return Firstname
    }
    fun getLastName(): String {
        return Lastname
    }

    fun getAddress(): String {
        return Address
    }

    fun getCountry(): String {
        return Country
    }

    fun getEmail(): String {
        return Email
    }

    fun getMobile(): String {
        return Mobile
    }

    fun getCompanyWebsite(): String {
        return Website
    }

    fun getCompanyName(): String {
        return Comp_Name
    }

    fun getExhibitorLogo(): String {
        return Logo
    }

    fun getHallStallNo() : String {
        return Hall_and_Stall_Number
    }

    fun getDescription(): String {
        return Company_Brief
    }

    fun getCompanyEmail(): String {
        return Company_Head_Email
    }

}