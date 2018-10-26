package com.vimeo.vimeonetworking2.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.vimeo.moshiexampleandroid.R
import com.vimeo.networking2.AppConfiguration
import com.vimeo.networking2.CategoryList
import com.vimeo.networking2.Video
import com.vimeo.networking2.VideoList
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
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
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.vimeo.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val vimeoService = retrofit.create(VimeoService::class.java)

    }

    interface VimeoService {

        @GET("/configs")
        fun getConfigs(): Observable<AppConfiguration>

        @GET("/categories")
        fun getCategories(): Observable<CategoryList>

        @GET("/channels/{channel_id}/videos")
        fun getChannelVideos(@Path("channel_id") channelId: String): Observable<VideoList>

        @GET("/videos/{video_id}")
        fun getVideo(@Path("video_id") videoId: String): Observable<Video>

    }

    class HeaderInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", "API_TOKEN")
                .addHeader("Accept", "application/vnd.vimeo.*+json;version=API_VERSION")
                .build()
            return chain.proceed(request)
        }
    }

}
