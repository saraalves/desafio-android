package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.repositoryModules
import com.picpay.desafio.android.di.serviceModules
import com.picpay.desafio.android.di.storageModules
import com.picpay.desafio.android.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
// ele vai iniciar o koin, pegando o contexto da aplicação, e vai injetar um por um
// no cenario que precisamos por baixo dos panos ele tá criando a instancia do reposiroto
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModules,
                    storageModules,
                    repositoryModules,
                    serviceModules
                )
            )

        }
    }
}