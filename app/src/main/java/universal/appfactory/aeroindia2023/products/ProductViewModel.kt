package universal.appfactory.aeroindia2023.products

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    lateinit var allproduct: LiveData<List<ProductModel>>
    private lateinit var productDao: ProductDao
    private lateinit var repository: ProductRepository

    fun init(app: Application) {
        if (::allproduct.isInitialized) return
        productDao = ProductDatabase.getDatabase(app).productDao()
        repository = ProductRepository(app)
        allproduct = repository.getAllProducts()
    }


    fun loadAllProducts(reload: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadAllProducts(reload)
        }
    }

}