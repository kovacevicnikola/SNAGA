package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import rs.magus.snaga.pojo.constants.TableNames
import rs.magus.snaga.repository.datasources.db.entities.baseEntities.BaseEntity

@Entity(
    tableName = TableNames.EXERCISE_LOGS,
    foreignKeys = [ForeignKey(
        entity = ExerciseEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExerciseLogEntity(
    val exerciseId: Int,
    val reps: Int,
    val weight: Double,
    val createdDate: String

) : BaseEntity()

