package com.andriod17.upbudget.data.remote.responses

import android.os.Build
import androidx.annotation.RequiresApi
import com.andriod17.upbudget.data.database.entities.EducationalContentEntity
import com.andriod17.upbudget.data.model.EducationalContent.EducationalContent
import java.time.OffsetDateTime


data class EducationalContentResponse(
    val id: Long,
    val title: String,
    val content: String,
    val created_at: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun EducationalContentResponse.toDomain(): EducationalContent {
    return EducationalContent(
        id = this.id,
        title = this.title,
        content = this.content,
        created_at = OffsetDateTime.parse(this.created_at)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun EducationalContentResponse.toEntity(): EducationalContentEntity {
    return EducationalContentEntity(
        id = this.id,
        title = this.title,
        content = this.content,
        created_at = OffsetDateTime.parse(this.created_at)
    )
}
