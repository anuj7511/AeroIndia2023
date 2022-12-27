package universal.appfactory.aeroindia2023.products

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface


class ProductRepository(application: Application) {

    private var productDao: ProductDao
    private var products: LiveData<List<ProductModel>>

    init {
        productDao = application.let { ProductDatabase.getDatabase(it).productDao() }
        products = productDao.getProducts()
        Log.d(TAG, "New instance created...")
    }

    fun getAllProducts(): LiveData<List<ProductModel>> {
        return productDao.getProducts()
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadAllProducts(reload: Boolean) {
        if(reload)
        {
            productDao.deleteAll()
            val productApi = ApiClient.getInstance().create(ApiInterface::class.java)

            // launching a new coroutine
            GlobalScope.launch(Dispatchers.IO) {
                val value = productApi.getProducts("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")
                    ?.awaitResponse()
                val data = value?.body()?.data as List<ProductModel>
                Log.d("Response: ", data.toString())
                productDao.insertAll(data)

            }
        }
    }

}