package rs.magus.snaga.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "ExerciseLogs")
data class ExerciseLogEntity (
    @PrimaryKey val id: Int,
    val exerciseType: String,
    val sets: Int,
    val reps: Int,
     val date: String
)