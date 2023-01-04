package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DelegateViewModel : ViewModel(){


    lateinit var allDelegates : LiveData<List<DelegateModel>>
    private lateinit var delegateDao: DelegateDao
    private lateinit var repository: DelegateRepository

    fun init(application: Application){
        if(::allDelegates.isInitialized) return
        delegateDao = DelegateDatabase.getDatabase(application).delegateDao()
        repository = DelegateRepository(application)
        allDelegates = repository.getAllFaqs()

    }

    fun loadAllDelegates(reload : Boolean){
        viewModelScope.launch (Dispatchers.IO){
            repository.loadAllDelegates(reload)
        }
    }

}