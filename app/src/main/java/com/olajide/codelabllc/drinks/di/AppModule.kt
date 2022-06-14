package com.olajide.codelabllc.drinks.di


import com.olajide.codelabllc.drinks.data.remote.DrinksApiService
import com.olajide.codelabllc.drinks.data.remote.RemoteRepository
import com.olajide.codelabllc.drinks.domain.repository.DrinksRepository
import com.olajide.codelabllc.drinks.resource.DispatchProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDrinksApiService(): DrinksApiService =
        Retrofit.Builder().baseUrl("https://www.thecocktaildb.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(DrinksApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteRepository(api: DrinksApiService): DrinksRepository = RemoteRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatchProvider = object : DispatchProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}
