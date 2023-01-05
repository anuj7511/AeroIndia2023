package universal.appfactory.aeroindia2023.faqs.media

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import universal.appfactory.aeroindia2023.faqs.FAQsModel
import universal.appfactory.aeroindia2023.faqs.FaqDao
import universal.appfactory.aeroindia2023.faqs.FaqsDatabase

@Database(
    entities = [MediaModel::class],
    version = 1,
    exportSchema = true
)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun mediaDao() : MediaDao

    companion object {

        @Volatile
        private var INSTANCE: MediaDatabase? = null

        fun getDatabase(context: Context): MediaDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): MediaDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                MediaDatabase::class.java,
                "media_database"
            ).build()

        }
    }
}