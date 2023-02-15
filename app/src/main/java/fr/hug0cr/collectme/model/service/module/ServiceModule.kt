package fr.hug0cr.collectme.model.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.hug0cr.collectme.model.service.AccountService
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.model.service.StorageService
import fr.hug0cr.collectme.model.service.impl.AccountServiceImpl
import fr.hug0cr.collectme.model.service.impl.LogServiceImpl
import fr.hug0cr.collectme.model.service.impl.StorageServiceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}