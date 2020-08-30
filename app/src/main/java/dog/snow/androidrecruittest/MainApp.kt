package dog.snow.androidrecruittest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dog.snow.androidrecruittest.di.DaggerAppComponent
import timber.log.Timber

class MainApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}