package com.picpay.desafio.android.di


import androidx.room.Room
import com.picpay.desafio.android.datalocal.AppDatabase
import com.picpay.desafio.android.repository.PicPayRepository
import com.picpay.desafio.android.repository.PicPayService
import com.picpay.desafio.android.viewmodel.PicPayViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
// timeout pra chamdada
const val TIMEOUT_5_SECONDS = 5L
//sempre criar a base url até o primeiro /
const val BASE_URL = "http://careers.picpay.com/"

//
val viewModelModules = module {
    viewModel { PicPayViewModel(get(), get()) }
}


//iniciando a instancia do Room, pra manter ele de pé
val storageModules = module {
    single { Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "userdata")
        .build()}
    // esse outro é só pra criar uma instancia dele aqui dentro do UserDAO
    single { get<AppDatabase>().userDAO() }
}

val repositoryModules = module {
    single{  PicPayRepository(get()) }
}

// inciando a instancia do retrofit uma vez só pq é um single
val serviceModules = module {
    single {
        createWebService(
            okHttpClient = OkHttpClient.Builder()

                .connectTimeout(TIMEOUT_5_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_5_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_5_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(configDebugLog())
                .build(),
            baseUrl = BASE_URL
        )
    }
}

// colocando pra acompanhar as chamdas que eu faço do retrofit, intecpeta a chamda do backend exibindo o que retorna de request e response
private fun configDebugLog(): HttpLoggingInterceptor {
    val log = HttpLoggingInterceptor()
    log.level = HttpLoggingInterceptor.Level.BODY
    return log
}

// só funciona pra criar a instancia do retorfit
fun createWebService(
    okHttpClient: OkHttpClient, baseUrl: String
): PicPayService {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(PicPayService::class.java)
}