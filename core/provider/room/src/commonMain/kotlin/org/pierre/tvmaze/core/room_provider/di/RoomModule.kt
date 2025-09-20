package org.pierre.tvmaze.core.room_provider.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import org.pierre.tvmaze.core.room_provider.db.AppDatabase

val roomModule = module {
    single<AppDatabase> {
        val builder: RoomDatabase.Builder<AppDatabase> = get()
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single { get<AppDatabase>().lastSearchDao() }
}
