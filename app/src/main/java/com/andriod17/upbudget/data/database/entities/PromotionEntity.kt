package com.andriod17.upbudget.data.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.andriod17.upbudget.data.model.Promotion.Promotion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date


@Entity(tableName = "promotions")
data class PromotionEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val title: String,
    val description: String,
    val active: Boolean,
    val start_date: Date,
    val end_date: Date,
    val images: List<String>,
    val restaurants: List<String>
)

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(timestamp: Long?): Date? = timestamp?.let { Date(it) }
}

fun PromotionEntity.toDomain(): Promotion {
    return Promotion(
        id = id,
        title = title,
        description = description,
        active = active,
        start_date = start_date,
        end_date = end_date,
        images = images,
        restaurants = restaurants
    )
}

fun Promotion.toEntity(): PromotionEntity {
    return PromotionEntity(
        id = id,
        title = title,
        description = description,
        active = active,
        start_date = start_date,
        end_date = end_date,
        images = images,
        restaurants = restaurants
    )
}