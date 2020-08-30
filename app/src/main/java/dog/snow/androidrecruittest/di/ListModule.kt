package dog.snow.androidrecruittest.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dog.snow.androidrecruittest.ui.listFragment.ListFragment
import dog.snow.androidrecruittest.ui.listFragment.ListViewModel

@Module
abstract class ListModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeListFragment(): ListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel
}