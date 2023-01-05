package universal.appfactory.aeroindia2023.faqs.exhibitors

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import universal.appfactory.aeroindia2023.faqs.FaqsDatabase

@Database(
    entities = [ExhibitorsFaqModel::class],
    version = 1,
    exportSchema = true
)
abstract class ExhibitorsFaqDatabase : RoomDatabase() {
    abstract fun exhibitorsDao(): ExhibitorsFaqDao

    companion object {

        @Volatile
        private var INSTANCE: ExhibitorsFaqDatabase? = null

        fun getDatabase(context: Context): ExhibitorsFaqDatabase{

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): ExhibitorsFaqDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                ExhibitorsFaqDatabase::class.java,
                "exhibitors_faqs_database"
            ).build()

        }
    }
}