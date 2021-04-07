package com.picpay.desafio.android.di


import android.os.Build.HOST
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.datalocal.UserRepositoryLocal
import com.picpay.desafio.android.viewmodel.PicPayDAO
import com.picpay.desafio.android.viewmodel.PicPayViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModules = module {
    factory {
        PicPayViewModel(get(), get(), get(), get())
    }
}

val storageModules = module {
    factory {
        PicPayDAO(get())
    }
}

val repositoryModules = module {
    factory {
        UserRepositoryLocal(get())
    }
}