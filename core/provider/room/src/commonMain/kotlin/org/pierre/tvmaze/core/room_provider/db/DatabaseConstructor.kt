package org.pierre.tvmaze.core.room_provider.db

import androidx.room.RoomDatabaseConstructor

expect object DatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
