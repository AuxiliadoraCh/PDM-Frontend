package com.andriod17.upbudget.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andriod17.upbudget.data.model.EducationalContent.EducationalContent
import java.time.OffsetDateTime

@Entity(tableName = "educational_content")
data class EducationalContentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val created_at: OffsetDateTime
)

fun EducationalContentEntity.toDomain(): EducationalContent {
    return EducationalContent(
        id = this.id,
        title = this.title,
        content = this.content,
        created_at = this.created_at
    )
}
