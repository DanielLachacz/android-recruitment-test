package dog.snow.androidrecruittest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dog.snow.androidrecruittest.MainApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    SplashModule::class,
    ListModule::class]
)
interface AppComponent: AndroidInjector<MainApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}