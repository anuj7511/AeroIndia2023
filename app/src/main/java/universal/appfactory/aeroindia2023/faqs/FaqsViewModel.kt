package universal.appfactory.aeroindia2023.faqs

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaqsViewModel : ViewModel(){

    lateinit var allFaqs : LiveData<List<FAQsModel>>
    private lateinit var faqsDao: FaqDao
    private lateinit var repository: FaqsRepository

    fun init(application: Application){
        if(::allFaqs.isInitialized) return
        faqsDao = FaqsDatabase.getDatabase(application).faqsDao()
        repository = FaqsRepository(application)
        allFaqs = repository.getAllFaqs()

    }

    fun loadAllFaqs(reload : Boolean){
        viewModelScope.launch (Dispatchers.IO){
            repository.loadAllFaqs(reload)
        }
    }

}