package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import rs.magus.snaga.pojo.constants.TableNames

@Entity(
    tableName = TableNames.EXERCISE_DAY_TO_EXERCISE,
    foreignKeys = [ForeignKey(
        entity = ExerciseEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseId"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = ExerciseDayEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseDayId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExerciseDayExerciseEntity(
    val exerciseDayId: Int,
    val exerciseId: Int,
    val reps: Int,
    val sets: Int
) : BaseEntity()