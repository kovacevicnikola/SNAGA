package rs.magus.snaga

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy

class StrengthApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) enableStrictMode()
    }


    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

    }
}