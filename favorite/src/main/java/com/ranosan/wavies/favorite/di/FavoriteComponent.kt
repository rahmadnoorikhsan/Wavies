package com.ranosan.wavies.favorite.di

import android.content.Context
import com.ranosan.wavies.di.FavoriteModuleDependencies
import com.ranosan.wavies.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteModuleDependencies::class]
)
interface FavoriteComponent {
    fun inject(favoriteFragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}