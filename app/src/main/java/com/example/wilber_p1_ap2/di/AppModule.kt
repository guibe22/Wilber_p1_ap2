package com.example.wilber_p1_ap2.di

import android.content.Context
import androidx.room.Room
import com.example.wilber_p1_ap2.data.DivisionBD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesPrestamoDatabase(@ApplicationContext appContext: Context):  =
        Room.databaseBuilder(
            appContext,
            DivisionBD::class.java,
            ".db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
        fun providesTicketDao(db: DivisionBD) = db.DivisionDao()

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context) = CounterRepository(context)

}

