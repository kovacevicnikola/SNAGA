package rs.magus.snaga.repository.datasources.db.dao

import androidx.room.Dao
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

@Dao
interface ExerciseDao : BaseDao<ExerciseEntity> {

//    @Query("SELECT Exercises.*, IFNULL(MAX(weight), 1) as defaultWeight, IFNULL(AVG(sets), 1) as defaultSets, IFNULL(AVG(reps), 1) as defaultReps FROM Exercises LEFT JOIN ExerciseLogs ON ExerciseLogs.exerciseId = Exercises.id GROUP BY Exercises.id")
//    suspend fun getAll(): List<ExerciseData>
}