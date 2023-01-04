package universal.appfactory.aeroindia2023.delegate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DelegateModel::class],
    version = 1,
    exportSchema = true
)
abstract class DelegateDatabase : RoomDatabase() {
    abstract fun delegateDao(): DelegateDao

    companion object {

        @Volatile
        private var INSTANCE: DelegateDatabase? = null

        fun getDatabase(context: Context): DelegateDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = DelegateDatabase.buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): DelegateDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                DelegateDatabase::class.java,
                "delegate_database"
            ).build()

        }
    }
}