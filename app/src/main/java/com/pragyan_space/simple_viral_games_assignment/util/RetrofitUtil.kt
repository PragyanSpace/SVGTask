package com.pragyan_space.simple_viral_games_assignment.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtil {
    companion object {
        private lateinit var retrofit: Retrofit

        val BASE_URL = "https://dog.ceo/"


        fun getRetrofit(): Retrofit {

            if (!::retrofit.isInitialized) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create()
                    ).client(OkHttpClient.Builder().also {
                        it.connectTimeout(60, TimeUnit.SECONDS)
                        it.readTimeout(60, TimeUnit.SECONDS)
                        it.writeTimeout(60, TimeUnit.SECONDS)
                    }.also { client ->
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                            client.addInterceptor(logging);
                        }.build()
                    ).build()
                return retrofit
            }
            return retrofit
        }
    }
}