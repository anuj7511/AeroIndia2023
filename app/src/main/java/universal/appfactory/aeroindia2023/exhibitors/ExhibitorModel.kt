package universal.appfactory.aeroindia2023.exhibitors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "exhibitors")
data class ExhibitorModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    private var id: Int,
    @ColumnInfo(name = "Type")
    @SerializedName("Type")
    private var Type: String,
    @ColumnInfo(name = "Firstname")
    @SerializedName("Firstname")
    private var Firstname: String,
    @ColumnInfo(name = "Lastname")
    @SerializedName("Lastname")
    private var Lastname: String,
    @ColumnInfo(name = "Comp_Name")
    @SerializedName("Comp_Name")
    private var Comp_Name: String,
    @ColumnInfo(name = "Country")
    @SerializedName("Country")
    private var Country: String,
    @ColumnInfo(name = "Email")
    @SerializedName("Email")
    private var Email: String,
    @ColumnInfo(name = "Password")
    @SerializedName("Password")
    private var Password: String,
    @ColumnInfo(name = "Mobile")
    @SerializedName("Mobile")
    private var Mobile: String,
    @ColumnInfo(name = "Comp_ReferenceNo")
    @SerializedName("Comp_ReferenceNo")
    private var Comp_ReferenceNo: String,
    @ColumnInfo(name = "GST")
    @SerializedName("GST")
    private var GST: String,
    @ColumnInfo(name = "PAN")
    @SerializedName("PAN")
    private var PAN: String,
    @ColumnInfo(name = "Website")
    @SerializedName("Website")
    private var Website: String,
    @ColumnInfo(name = "Address")
    @SerializedName("Address")
    private var Address: String,
    @ColumnInfo(name = "Token_Key")
    @SerializedName("Token_Key")
    private var Token_Key: String,
    @ColumnInfo(name = "ExhibitorType")
    @SerializedName("ExhibitorType")
    private var ExhibitorType: String,
    @ColumnInfo(name = "Badge_Quota")
    @SerializedName("Badge_Quota")
    private var Badge_Quota: Int,
    @ColumnInfo(name = "Area")
    @SerializedName("Area")
    private var Area: String,
    @ColumnInfo(name = "HallNo")
    @SerializedName("HallNo")
    private var HallNo: String,
    @ColumnInfo(name = "StallNo")
    @SerializedName("StallNo")
    private var StallNo: String,
    @ColumnInfo(name = "CatalogueFlag")
    @SerializedName("CatalogueFlag")
    private var CatalogueFlag: Int,
    @ColumnInfo(name = "short_company_name")
    @SerializedName("short_company_name")
    private var short_company_name: String,
    @ColumnInfo(name = "fax_country_code")
    @SerializedName("fax_country_code")
    private var fax_country_code: String,
    @ColumnInfo(name = "exhibitor_fax")
    @SerializedName("exhibitor_fax")
    private var exhibitor_fax: String,
    @ColumnInfo(name = "Nodal_Officer_Name")
    @SerializedName("Nodal_Officer_Name")
    private var Nodal_Officer_Name: String,
    @ColumnInfo(name = "Nodal_Officer_Email")
    @SerializedName("Nodal_Officer_Email")
    private var Nodal_Officer_Email: String,
    @ColumnInfo(name = "Nodal_Designation")
    @SerializedName("Nodal_Designation")
    private var Nodal_Designation: String,
    @ColumnInfo(name = "Nodal_Country_Code")
    @SerializedName("Nodal_Country_Code")
    private var Nodal_Country_Code: String,
    @ColumnInfo(name = "Nodal_Phone")
    @SerializedName("Nodal_Phone")
    private var Nodal_Phone: String,
    @ColumnInfo(name = "Company_Head_Name")
    @SerializedName("Company_Head_Name")
    private var Company_Head_Name: String,
    @ColumnInfo(name = "Company_Head_Email")
    @SerializedName("Company_Head_Email")
    private var Company_Head_Email: String,
    @ColumnInfo(name = "Company_Head_Designation")
    @SerializedName("Company_Head_Designation")
    private var Company_Head_Designation: String,
    @ColumnInfo(name = "Company_Head_Country_Code")
    @SerializedName("Company_Head_Country_Code")
    private var Company_Head_Country_Code: String,
    @ColumnInfo(name = "Company_Head_Phone")
    @SerializedName("Company_Head_Phone")
    private var Company_Head_Phone: String,
    @ColumnInfo(name = "Login_Email")
    @SerializedName("Login_Email")
    private var Login_Email: String,
    @ColumnInfo(name = "Address_Line_1")
    @SerializedName("Address_Line_1")
    private var Address_Line_1: String,
    @ColumnInfo(name = "Address_Line_2")
    @SerializedName("Address_Line_2")
    private var Address_Line_2: String,
    @ColumnInfo(name = "City")
    @SerializedName("City")
    private var City: String,
    @ColumnInfo(name = "State")
    @SerializedName("State")
    private var State: String,
    @ColumnInfo(name = "Pin_Zip_Code")
    @SerializedName("Pin_Zip_Code")
    private var Pin_Zip_Code: String,
    @ColumnInfo(name = "Category")
    @SerializedName("Category")
    private var Category: String,
    @ColumnInfo(name = "Sub_Category")
    @SerializedName("Sub_Category")
    private var Sub_Category: String,
    @ColumnInfo(name = "Sub_Sub_Category")
    @SerializedName("Sub_Sub_Category")
    private var Sub_Sub_Category: String,
    @ColumnInfo(name = "Logo")
    @SerializedName("Logo")
    private var Logo: String,
    @ColumnInfo(name = "Hall_and_Stall_Number")
    @SerializedName("Hall_and_Stall_Number")
    private var Hall_and_Stall_Number: String,
    @ColumnInfo(name = "Twitter")
    @SerializedName("Twitter")
    private var Twitter: String,
    @ColumnInfo(name = "LinkedIn")
    @SerializedName("LinkedIn")
    private var LinkedIn: String,
    @ColumnInfo(name = "Instagram")
    @SerializedName("Instagram")
    private var Instagram: String,
    @ColumnInfo(name = "Facebook")
    @SerializedName("Facebook")
    private var Facebook: String,
    @ColumnInfo(name = "Company_Brief")
    @SerializedName("Company_Brief")
    private var Company_Brief: String,
    @ColumnInfo(name = "Submit_additional_150_words")
    @SerializedName("Submit_additional_150_words")
    private var Submit_additional_150_words: String,
    ) {

    fun getId() : Int {
        return id
    }

    fun getType() : String {
        return Type
    }

    fun getFirstname(): String {
        return Firstname
    }
    fun getLastname(): String {
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

    fun getWebsite(): String {
        return Website
    }

    fun getComp_Name(): String {
        return Comp_Name
    }

    fun getLogo(): String {
        return Logo
    }

    fun getHall_and_Stall_Number() : String {
        return Hall_and_Stall_Number
    }

    fun getCompany_Brief(): String {
        return Company_Brief
    }

    fun getCompany_Head_Email(): String {
        return Company_Head_Email
    }

    fun getPassword(): String {
        return Password
    }

    fun getComp_ReferenceNo(): String {
        return Comp_ReferenceNo
    }

    fun getGST(): String {
        return GST
    }

    fun getPAN(): String {
        return PAN
    }

    fun getToken_Key(): String {
        return Token_Key
    }

    fun getExhibitorType(): String {
        return ExhibitorType
    }

    fun getBadge_Quota(): Int {
        return Badge_Quota
    }

    fun getArea(): String {
        return Area
    }

    fun getHallNo(): String {
        return HallNo
    }

    fun getStallNo(): String {
        return StallNo
    }

    fun getCatalogueFlag(): Int {
        return CatalogueFlag
    }

    fun getShort_company_name(): String {
        return short_company_name
    }

    fun getFax_country_code(): String {
        return fax_country_code
    }

    fun getExhibitor_fax(): String {
        return exhibitor_fax
    }

    fun getNodal_Designation(): String {
        return Nodal_Designation
    }

    fun getNodal_Country_Code(): String {
        return Nodal_Country_Code
    }

    fun getNodal_Phone(): String {
        return Nodal_Phone
    }

    fun getNodal_Officer_Email(): String {
        return Nodal_Officer_Email
    }

    fun getNodal_Officer_Name(): String {
        return Nodal_Officer_Name
    }

    fun getCompany_Head_Country_Code(): String {
        return Company_Head_Country_Code
    }

    fun getCompany_Head_Designation(): String {
        return Company_Head_Designation
    }

    fun getCompany_Head_Name(): String {
        return Company_Head_Name
    }

    fun getCompany_Head_Phone(): String {
        return Company_Head_Phone
    }

    fun getLogin_Email(): String {
        return Login_Email
    }

    fun getAddress_Line_1(): String {
        return Address_Line_1
    }

    fun getAddress_Line_2(): String {
        return Address_Line_2
    }

    fun getCity(): String {
        return City
    }

    fun getState(): String {
        return State
    }

    fun getPin_Zip_Code(): String {
        return Pin_Zip_Code
    }

    fun getCategory(): String {
        return Category
    }

    fun getSub_Category(): String {
        return Sub_Category
    }

    fun getSub_Sub_Category(): String {
        return Sub_Sub_Category
    }

    fun getTwitter(): String {
        return Twitter
    }

    fun getLinkedIn(): String {
        return LinkedIn
    }

    fun getInstagram(): String {
        return Instagram
    }

    fun getFacebook(): String {
        return Facebook
    }

    fun getSubmit_additional_150_words(): String {
        return Submit_additional_150_words
    }

}