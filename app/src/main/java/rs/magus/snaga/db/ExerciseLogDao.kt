package rs.magus.snaga.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseLogDao {
    @Insert
    suspend fun insertAll(vararg exerciseLogs: ExerciseLogEntity)

    @Delete
    suspend fun delete(user: ExerciseLogEntity)

    @Query("SELECT * FROM ExerciseLogs")
    suspend fun getAll(): List<ExerciseLogEntity>

}