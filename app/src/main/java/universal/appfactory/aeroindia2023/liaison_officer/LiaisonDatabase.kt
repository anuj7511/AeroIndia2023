package universal.appfactory.aeroindia2023.liaison_officer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import universal.appfactory.aeroindia2023.delegate.DelegateDao

@Database(
    entities = [LiaisonModel::class],
    version = 1,
    exportSchema = true
)
abstract class LiaisonDatabase : RoomDatabase() {
    abstract fun liaisonDao(): LiaisonDao

    companion object {

        @Volatile
        private var INSTANCE: LiaisonDatabase? = null

        fun getDatabase(context: Context): LiaisonDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = LiaisonDatabase.buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): LiaisonDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                LiaisonDatabase::class.java,
                "liaison_database"
            ).build()

        }
    }
}
