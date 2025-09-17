package org.pierre.tvmaze.core.data_store_provider

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.java.KoinJavaComponent.getKoin

actual fun createDataStore(): DataStore<Preferences> = commonCreateDataStore { dataStoreFileName ->
        getKoin().get<Context>().filesDir.resolve(dataStoreFileName).absolutePath
    }
