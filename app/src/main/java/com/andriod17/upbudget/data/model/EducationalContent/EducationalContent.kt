package com.andriod17.upbudget.data.model.EducationalContent
import com.andriod17.upbudget.data.model.EducationalContent.Request.EducationalContentRequest
import java.time.OffsetDateTime

data class EducationalContent(
    val id: Long,
    val title: String,
    val content: String,
    val created_at: OffsetDateTime
)

fun EducationalContent.toRequest(): EducationalContentRequest {
    return EducationalContentRequest(
        title = this.title,
        content = this.content
    )
}