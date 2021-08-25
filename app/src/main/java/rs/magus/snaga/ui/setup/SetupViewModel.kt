package rs.magus.snaga.ui.setup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import rs.magus.snaga.repository.Repository
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

class SetupViewModel(application:Application) : AndroidViewModel(application){
    val repository: Repository = Repository(application)

    public suspend fun getExercises(): List<ExerciseEntity> {
        return repository.getExercises()
    }
}