package universal.appfactory.aeroindia2023.faqs.exhibitors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "exhibitorsfaqs")
class ExhibitorsFaqModel (

    @PrimaryKey
    @ColumnInfo(name= "id")
    @SerializedName("id")
    private var id : Int,
    @ColumnInfo(name = "user_type_category_id")
    @SerializedName("user_type_category_id")
    private var user_type_category_id : String,
    @ColumnInfo(name = "faq_question")
    @SerializedName("faq_question")
    private var faq_question : String,
    @ColumnInfo(name = "faq_answer")
    @SerializedName("faq_answer")
    private var faq_answer : String,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    private var status : Int,
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    private var created_at : String,
    @ColumnInfo(name= "updated_at")
    @SerializedName("updated_at")
    private var updated_at : String,
){



    fun getId() : Int{
        return id
    }

    fun getUser_type_category_id(): String{
        return user_type_category_id
    }


    fun getFaq_question(): String{
        return faq_question
    }

    fun getFaq_answer(): String{
        return faq_answer
    }

    fun getStatus() : Int{
        return status
    }

    fun getCreated_at(): String{
        return created_at
    }

    fun getUpdated_at(): String{
        return updated_at
    }

}