package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailDao
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailDatabase
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailRepository

class TrailHistoryViewModel : ViewModel() {
    lateinit var allTrailHistory : LiveData<List<TrailHistoryModel>>
    private lateinit var trailHistoryDao: TrailHistoryDao
    private lateinit var repository: TrailHistoryRepository

    fun init(application: Application){
        if(::allTrailHistory.isInitialized) return
        trailHistoryDao = TrailHistoryDatabase.getDatabase(application).trailHistoryDao()
        repository = TrailHistoryRepository(application)
        allTrailHistory = repository.getAllTrailHistory()

    }

    fun loadAllTrailHistory(reload : Boolean){
        viewModelScope.launch (Dispatchers.IO){
            repository.loadAllTrailHistory(reload)
        }
    }
}