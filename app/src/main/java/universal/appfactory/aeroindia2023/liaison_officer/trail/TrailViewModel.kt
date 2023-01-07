package universal.appfactory.aeroindia2023.liaison_officer.trail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonDao
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonDatabase
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonModel
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonRepository

class TrailViewModel : ViewModel() {
    lateinit var allTrail : LiveData<List<TrailModel>>
    private lateinit var trailDao: TrailDao
    private lateinit var repository: TrailRepository

    fun init(application: Application){
        if(::allTrail.isInitialized) return
        trailDao = TrailDatabase.getDatabase(application).trailDao()
        repository = TrailRepository(application)
        allTrail = repository.getAllTrail()

    }

    fun loadAllTrail(reload : Boolean){
        viewModelScope.launch (Dispatchers.IO){
            repository.loadAllTrail(reload)
        }
    }
}