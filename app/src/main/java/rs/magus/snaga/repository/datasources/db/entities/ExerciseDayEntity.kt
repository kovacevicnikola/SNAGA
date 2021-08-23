package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import rs.magus.snaga.pojo.constants.TableNames
import rs.magus.snaga.repository.datasources.db.entities.baseEntities.BaseEntity

@Entity(tableName = TableNames.EXERCISE_DAY)
class ExerciseDayEntity(
    val name: String,
    val createdDate: String,
    val dayOfWeek: Int,
    val repeatAfter: Int
) : BaseEntity()