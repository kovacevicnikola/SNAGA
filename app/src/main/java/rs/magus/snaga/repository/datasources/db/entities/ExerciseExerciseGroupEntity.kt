package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import rs.magus.snaga.pojo.constants.TableNames

@Entity(
    tableName = TableNames.EXERCISE_EXERCISE_GROUPS,
    foreignKeys = [ForeignKey(
        entity = ExerciseEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseID"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = ExerciseGroupEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseGroupID"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExerciseExerciseGroupEntity(
    val exerciseID: Int,
    val exerciseGroupID: Int
)