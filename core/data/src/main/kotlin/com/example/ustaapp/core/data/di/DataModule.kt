package com.example.ustaapp.core.data.di

import com.example.ustaapp.core.data.repository.OfflineFirstScheduleRepository
import com.example.ustaapp.core.data.repository.ScheduleRepository
import com.example.ustaapp.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.ustaapp.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsScheduleRepository(
        scheduleRepository: OfflineFirstScheduleRepository,
    ): ScheduleRepository

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
