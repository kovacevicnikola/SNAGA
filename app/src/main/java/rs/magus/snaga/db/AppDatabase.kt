package rs.magus.snaga.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExerciseLogEntity::class], version = 1)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): ExerciseLogDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase ::class.java,
                "train-db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}