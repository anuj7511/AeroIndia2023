package universal.appfactory.aeroindia2023.faqs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FAQsModel::class],
    version = 1,
    exportSchema = true
)
abstract class FaqsDatabase : RoomDatabase() {
    abstract fun faqsDao(): FaqDao

    companion object{

        @Volatile
        private var INSTANCE : FaqsDatabase? = null

        fun getDatabase(context: Context): FaqsDatabase{

            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): FaqsDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                FaqsDatabase::class.java,
                "faqs_database"
            ).build()

        }
    }
}