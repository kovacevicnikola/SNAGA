package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import rs.magus.snaga.pojo.constants.TableNames

@Entity(tableName = TableNames.EXERCISE_DAY)
class ExerciseDayEntity(
    val dayEnum: Int,
    val name: String?,
    val exerciseTypeEnum: Int
) : BaseEntity()