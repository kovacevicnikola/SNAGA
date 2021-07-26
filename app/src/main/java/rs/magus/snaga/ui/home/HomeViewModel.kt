package rs.magus.snaga.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import rs.magus.snaga.repository.Repository
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val exerciseList =
        arrayOf("Deadlift", "Benchpress", "Overhead press", "Lat pulldowns", "Squats", "Face pulls")
    val repository: Repository = Repository(application)
    suspend fun insertExerciseLog(entity: ExerciseLogEntity) {
        //todo database.wordDao().insertAll(entity)
    }

    suspend fun getExercises(): List<ExerciseEntity> {
        return repository.getExercises()
    }

}