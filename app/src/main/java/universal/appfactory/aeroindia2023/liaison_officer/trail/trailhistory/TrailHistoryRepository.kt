package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailDao
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailDatabase
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel

class TrailHistoryRepository(application: Application) {
    private var trailHistoryDao : TrailHistoryDao
    private var trailHistory : LiveData<List<TrailHistoryModel>>

    init {
        trailHistoryDao = application.let { TrailHistoryDatabase.getDatabase(it).trailHistoryDao() }
        trailHistory = trailHistoryDao.getTrailHistory()
        Log.d(ContentValues.TAG, "New instance created")
    }

    fun getAllTrailHistory(): LiveData<List<TrailHistoryModel>> {
        return trailHistoryDao.getTrailHistory()
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadAllTrailHistory(reload: Boolean,id : Int ,type: String ) {
        if (reload) {
            trailHistoryDao.deleteAll()
            val trailHistoryApi = ApiClient.getInstance().create(ApiInterface::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val value = trailHistoryApi.getTrailHistory("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",id,type)
                    ?.awaitResponse()
                val data = value?.body()?.data as List<TrailHistoryModel>
                Log.d("Trail History Response :", data.toString())
                trailHistoryDao.insertAll(data)
            }
        }
    }
}