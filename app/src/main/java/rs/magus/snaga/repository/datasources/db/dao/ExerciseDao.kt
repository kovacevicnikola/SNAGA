package rs.magus.snaga.repository.datasources.db.dao

import androidx.room.Dao
import androidx.room.Query
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

@Dao
interface ExerciseDao : BaseDao<ExerciseEntity> {

    @Query("SELECT * FROM Exercises")
    suspend fun getAll(): List<ExerciseEntity>
}