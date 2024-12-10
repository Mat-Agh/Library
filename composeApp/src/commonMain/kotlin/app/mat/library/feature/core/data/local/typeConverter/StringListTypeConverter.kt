package app.mat.library.feature.core.data.local.typeConverter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringListTypeConverter {
    @TypeConverter
    fun fromString(
        value: String
    ): List<String> = Json.decodeFromString(
        value
    )

    @TypeConverter
    fun fromList(
        list: List<String>
    ): String = Json.encodeToString(
        list
    )
}