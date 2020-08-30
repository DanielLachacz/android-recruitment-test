package dog.snow.androidrecruittest.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dog.snow.androidrecruittest.ui.splashActivity.SplashActivity
import dog.snow.androidrecruittest.ui.splashActivity.SplashViewModel

@Module
abstract class SplashModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeSplashActivity(): SplashActivity

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}