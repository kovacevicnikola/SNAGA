package rs.magus.snaga.db

import android.content.Context
import androidx.room.Room

public class DBDataSource {

    fun getDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }
}