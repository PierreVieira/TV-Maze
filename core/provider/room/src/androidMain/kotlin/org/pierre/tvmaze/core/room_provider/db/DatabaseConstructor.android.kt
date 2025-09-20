package org.pierre.tvmaze.core.room_provider.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.pierre.tvmaze.core.room_provider.utils.DatabaseUtils

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(DatabaseUtils.PATH)
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
