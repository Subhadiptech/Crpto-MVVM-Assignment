package com.ersubhadip.crptoapp.di

import com.ersubhadip.crptoapp.data.ICryptoAPI
import com.ersubhadip.crptoapp.data.IRemoteDataRepository
import com.ersubhadip.crptoapp.domain.RemoteDataRepositoryImpl
import com.ersubhadip.crptoapp.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ICryptoAPI = retrofit.create(ICryptoAPI::class.java)


    @Provides
    @Singleton
    fun provideRemoteRepository(api: ICryptoAPI): IRemoteDataRepository =
        RemoteDataRepositoryImpl(api)
}