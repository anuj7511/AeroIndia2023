package universal.appfactory.aeroindia2023.faqs.event

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import universal.appfactory.aeroindia2023.faqs.FAQsModel
import universal.appfactory.aeroindia2023.faqs.FaqsDatabase

@Database(
    entities = [EventsModel::class],
    version = 1,
    exportSchema = true
)
abstract class EventsDatabase: RoomDatabase() {
    abstract fun eventsDao(): EventsDao

    companion object{
        @Volatile
        private var INSTANCE : EventsDatabase? = null

        fun getDatabase(context: Context): EventsDatabase {

            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): EventsDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                EventsDatabase::class.java,
                "events_database"
            ).build()

        }

    }
}