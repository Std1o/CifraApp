package com.stdio.cifraapp.di

import com.stdio.cifraapp.data.MainRepository
import com.stdio.cifraapp.data.MainService
import com.stdio.cifraapp.data.OAuthInterceptor
import com.stdio.cifraapp.data.RemoteDataSource
import com.stdio.cifraapp.presentation.viewmodel.BankListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { BankListViewModel(get()) }
}

val appModule = module {
    fun providesBaseUrl(): String = "https://mb-ci.cifra.app/api/v1/"

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(OAuthInterceptor())
            .addInterceptor(httpLoggingInterceptor)
    }

    fun provideRetrofit(BASE_URL: String, httpBuilder: OkHttpClient.Builder): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpBuilder.build())
            .build()

    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)

    fun provideRemoteDataSource(mainService: MainService) =
        RemoteDataSource(mainService)

    fun provideRepository(remoteDataSource: RemoteDataSource) =
        MainRepository(remoteDataSource)

    single { providesBaseUrl() }
    single {provideHttpLoggingInterceptor()}
    factory {provideHttpClient(get())}
    single { provideRetrofit(get(), get()) }
    single { provideMainService(get()) }
    single { provideRemoteDataSource(get()) }
    single { provideRepository(get()) }
}

val allModules = viewModelModule + appModule