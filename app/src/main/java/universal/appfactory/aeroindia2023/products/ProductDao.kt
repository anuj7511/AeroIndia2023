package universal.appfactory.aeroindia2023.products

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(products: List<ProductModel?>?)

    @Query("SELECT * FROM products")
    fun getProducts(): LiveData<List<ProductModel>>

    @Query("DELETE FROM products")
    suspend fun deleteAll()
}