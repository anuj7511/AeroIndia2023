package universal.appfactory.aeroindia2023.faqs.event

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
import universal.appfactory.aeroindia2023.faqs.FAQsModel
import universal.appfactory.aeroindia2023.faqs.FaqDao
import universal.appfactory.aeroindia2023.faqs.FaqsDatabase

class EventsRepository(application: Application) {
    private var eventsDao : EventsDao
    private var faqs: LiveData<List<FAQsModel>>

    init {
        eventsDao = application.let { EventsDatabase.getDatabase(it).eventsDao() }
        faqs = eventsDao.getEventsFaqs()
        Log.d(ContentValues.TAG, "New instance created")
    }

    fun getAllEventsFaqs(): LiveData<List<FAQsModel>> {
        return eventsDao.getEventsFaqs()
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadAllEventsFaqs(reload: Boolean) {
        if (reload) {
            eventsDao.deleteAll()
            val faqApi = ApiClient.getInstance().create(ApiInterface::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val value = faqApi.getFaqs("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",1)
                    ?.awaitResponse()
                val data = value?.body()?.data as List<FAQsModel>
                Log.d("Response :", data.toString())
                eventsDao.insertAll(data)
            }
        }
    }
}