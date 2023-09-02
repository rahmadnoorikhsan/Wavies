package com.ranosan.wavies.search.di

import android.content.Context
import com.ranosan.wavies.di.SearchModuleDependencies
import com.ranosan.wavies.search.SearchFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [SearchModuleDependencies::class]
)

interface SearchComponent {
    fun inject(searchFragment: SearchFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(searchModuleDependencies: SearchModuleDependencies): Builder
        fun build(): SearchComponent
    }
}