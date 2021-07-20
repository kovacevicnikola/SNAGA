package rs.magus.snaga.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.magus.snaga.R
import rs.magus.snaga.db.AppDatabase
import rs.magus.snaga.db.DBDataSource
import rs.magus.snaga.db.ExerciseLogEntity

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val exerciseList = arrayOf("Deadlift", "Benchpress", "Overhead press", "Lat pulldowns", "Squats", "Face pulls")
    var database: AppDatabase = AppDatabase.getDatabase(application)
    suspend fun insertExerciseLog(entity: ExerciseLogEntity) {
        database.wordDao().insertAll(entity)
    }

}