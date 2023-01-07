package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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