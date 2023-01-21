package universal.appfactory.aeroindia2023.products

import com.google.gson.annotations.SerializedName
import universal.appfactory.aeroindia2023.products.ProductModel

data class ProductResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ProductModel>
)

data class ProductResponse2(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ProductModel2>
)