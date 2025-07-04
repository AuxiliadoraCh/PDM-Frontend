package com.andriod17.upbudget.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.andriod17.upbudget.data.model.Used_Coupons.UsedCoupon
import java.util.Date

@Entity(tableName = "used_coupons",
  /*  foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PromotionEntity::class,
            parentColumns = ["id"],
            childColumns = ["promotion_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
    Index(value = ["user_id"]),
    Index(value = ["promotion_id"]),
    Index(value = ["user_id", "promotion_id"], unique = true)
    ]
   */
    )
data class UsedCouponEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val user_id: String,
    val promotion_id: Int,
    val code: String,
    val used_at: Date
)

fun UsedCouponEntity.toDomain(): UsedCoupon{
    return UsedCoupon(
        id = id,
        user_id = user_id,
        promotion_id = promotion_id,
        code = code,
        used_at = used_at
    )
}

fun UsedCoupon.toEntity(): UsedCouponEntity{
    return UsedCouponEntity(
        id = id,
        user_id = user_id,
        promotion_id = promotion_id,
        code = code,
        used_at = used_at
    )
}

