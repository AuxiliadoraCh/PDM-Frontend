package com.andriod17.upbudget.data.model.EducationalContent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromOffsetDateTime(value: OffsetDateTime): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        } else {
            throw UnsupportedOperationException("OffsetDateTime is not supported on API levels below 26")
        }
    }

    @TypeConverter
    fun toOffsetDateTime(value: String): OffsetDateTime {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        } else {
            throw UnsupportedOperationException("OffsetDateTime is not supported on API levels below 26")
        }
    }
}
