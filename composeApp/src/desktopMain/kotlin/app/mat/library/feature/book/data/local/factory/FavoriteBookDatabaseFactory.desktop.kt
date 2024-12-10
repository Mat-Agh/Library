package app.mat.library.feature.book.data.local.factory

import androidx.room.Room
import androidx.room.RoomDatabase
import app.mat.library.feature.book.data.local.database.FavoriteBookDatabase
import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class FavoriteBookDatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val operatingSystem = System.getProperty(
            "os.name"
        ).lowercase()

        val userHome = System.getProperty(
            "user.home"
        )

        val applicationDataDirectory = when {
            operatingSystem.contains("win") -> File(
                System.getenv("APPDATA"),
                "Library"
            )

            operatingSystem.contains("mac") -> File(
                userHome,
                "Library/Application Support/Library"
            )

            else -> File(userHome, ".local/share/Library")
        }

        if (!applicationDataDirectory.exists()) {
            applicationDataDirectory.mkdirs()
        }

        val databaseFile = File(
            applicationDataDirectory,
            FavoriteBookDatabase.DATABASE_NAME
        )

        return Room.databaseBuilder(
            name = databaseFile.absolutePath
        )
    }
}