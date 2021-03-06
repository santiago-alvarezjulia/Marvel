package com.saj.marvel.di

import com.saj.marvel.ui.imageManager.GlideManager
import com.saj.marvel.ui.imageManager.ImageManager
import com.saj.marvel.ui.utils.PxToDP
import com.saj.marvel.ui.utils.PxToDpInt
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class CharactersFragmentModule {

    @Binds
    abstract fun bindImageLoader(
        glideImageLoader: GlideManager
    ): ImageManager

    @Binds
    abstract fun bindPxToDp(
        pxToDP: PxToDP
    ): PxToDpInt
}