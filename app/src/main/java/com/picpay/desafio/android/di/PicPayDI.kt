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

const val TIMEOUT_5_SECONDS = 5L
const val BASE_URL = "http://careers.picpay.com/"

val viewModelModules = module {
    viewModel { PicPayViewModel(get(), get()) }
}

val storageModules = module {
    single { Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "userdata")
        .build()}
    single { get<AppDatabase>().userDAO() }
}

val repositoryModules = module {
    single{  PicPayRepository(get()) }
}

// inciando a instancia do retrofit
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

private fun configDebugLog(): HttpLoggingInterceptor {
    val log = HttpLoggingInterceptor()
    log.level = HttpLoggingInterceptor.Level.BODY
    return log
}

// criar a instancia do retrofit
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