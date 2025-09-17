package org.pierre.tvmaze.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.module.Module
import org.koin.dsl.module
import org.pierre.tvmaze.utils.DATA_STORE_FILE_NAME
import org.pierre.tvmaze.utils.createDataStore

actual val platformModule: Module = module {
    single<DataStore<Preferences>> { createDataStore { DATA_STORE_FILE_NAME } }
}
