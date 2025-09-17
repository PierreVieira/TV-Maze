package org.pierre.tvmaze

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.pierre.tvmaze.di.initializeKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            config = {
                androidContext(this@MyApplication)
            }
        )
    }
}
