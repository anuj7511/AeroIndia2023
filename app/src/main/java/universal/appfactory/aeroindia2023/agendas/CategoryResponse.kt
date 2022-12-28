package universal.appfactory.aeroindia2023.agendas

import com.google.gson.annotations.SerializedName

data class CategoryResponse(@SerializedName("status")
                          val status: Int,
                            @SerializedName("message")
                          val message: String,
                            @SerializedName("data")
                          val data: List<Categories>)

class Categories (@SerializedName("categories")
                  private var categories: String,) {

    fun getCategories(): String {
        return categories
    }
}
