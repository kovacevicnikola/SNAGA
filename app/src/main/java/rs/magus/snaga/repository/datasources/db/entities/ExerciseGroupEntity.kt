package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import rs.magus.snaga.pojo.constants.TableNames
import rs.magus.snaga.repository.datasources.db.entities.baseEntities.BaseEntity

@Entity(tableName = TableNames.EXERCISE_GROUPS)
data class ExerciseGroupEntity(
    val name: String,
    val description: String
) : BaseEntity()