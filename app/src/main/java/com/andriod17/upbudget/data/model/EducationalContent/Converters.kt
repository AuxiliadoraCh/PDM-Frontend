package com.andriod17.upbudget.data.model.EducationalContent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromOffsetDateTime(value: OffsetDateTime): String {
        return value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toOffsetDateTime(value: String): OffsetDateTime {
        return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}
