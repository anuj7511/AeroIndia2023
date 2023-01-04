package universal.appfactory.aeroindia2023.liaison_officer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import universal.appfactory.aeroindia2023.delegate.DelegateDao
import universal.appfactory.aeroindia2023.delegate.DelegateDatabase
import universal.appfactory.aeroindia2023.delegate.DelegateModel
import universal.appfactory.aeroindia2023.delegate.DelegateRepository

class LiaisonViewModel : ViewModel() {
    lateinit var allLiaison : LiveData<List<LiaisonModel>>
    private lateinit var liaisonDao: LiaisonDao
    private lateinit var repository: LiaisonRepository

    fun init(application: Application){
        if(::allLiaison.isInitialized) return
        liaisonDao = LiaisonDatabase.getDatabase(application).liaisonDao()
        repository = LiaisonRepository(application)
        allLiaison = repository.getAllLiaisonOfficers()

    }

    fun loadAllLiaisonOfficers(reload : Boolean){
        viewModelScope.launch (Dispatchers.IO){
            repository.loadAllLiaisonOfficers(reload)
        }
    }
}