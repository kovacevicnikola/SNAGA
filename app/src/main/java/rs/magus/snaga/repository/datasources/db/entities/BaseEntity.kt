package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.PrimaryKey


open class BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
