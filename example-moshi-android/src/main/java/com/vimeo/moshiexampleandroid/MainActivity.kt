package com.vimeo.moshiexampleandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.vimeo.networking2.Album
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moshi = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()

        val client = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.vimeo.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val vimeoService = retrofit.create(VimeoService::class.java)
    }

    interface VimeoService {

        @GET("/albums/{album_id}")
        fun getAlbums(@Path("album_id") albumId: String): Observable<Album>

    }

    class HeaderInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", "TEST ACCESS TOKEN")
                .build()
            return chain.proceed(request)
        }
    }
}
