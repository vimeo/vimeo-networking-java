/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Builder for creating Square OkHttpClient with pinned certificate that can be used with Retrofit.
 * <p>
 * Created by kylevenn on 6/10/15.
 */
@SuppressWarnings("unused")
public class RetrofitClientBuilder {

    private static final String KEYSTORE_PASSWORD = "vimeo123";
    private static final int NO_TIMEOUT = -1;

    private int mConnectionTimeout = NO_TIMEOUT;
    private TimeUnit mConnectionTimeoutTimeUnit;
    private int mReadTimeout = NO_TIMEOUT;
    private TimeUnit mReadTimeoutTimeUnit;
    private Cache mCache;
    private List<Interceptor> mInterceptorList = new ArrayList<>();
    private List<Interceptor> mNetworkInterceptorList = new ArrayList<>();
    private SSLSocketFactory mSSLSocketFactory;

    public RetrofitClientBuilder setConnectionTimeout(int connectionTimeout, TimeUnit timeUnit) {
        this.mConnectionTimeout = connectionTimeout;
        this.mConnectionTimeoutTimeUnit = timeUnit;
        return this;
    }

    public RetrofitClientBuilder setReadTimeout(int readTimeout, TimeUnit timeUnit) {
        this.mReadTimeout = readTimeout;
        this.mReadTimeoutTimeUnit = timeUnit;
        return this;
    }

    public RetrofitClientBuilder setCache(Cache cache) {
        this.mCache = cache;
        return this;
    }

    public RetrofitClientBuilder addNetworkInterceptor(Interceptor interceptor) {
        mNetworkInterceptorList.add(interceptor);
        return this;
    }

    public RetrofitClientBuilder addNetworkInterceptors(List<Interceptor> interceptors) {
        mNetworkInterceptorList.addAll(interceptors);
        return this;
    }

    public RetrofitClientBuilder addInterceptor(Interceptor interceptor) {
        mInterceptorList.add(interceptor);
        return this;
    }

    public RetrofitClientBuilder addInterceptors(List<Interceptor> interceptors) {
        mInterceptorList.addAll(interceptors);
        return this;
    }

    public RetrofitClientBuilder pinCertificates()
            throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException,
                   KeyManagementException, UnrecoverableKeyException, NullPointerException {

        // For full implementation refer to https://github.com/ikust/hello-pinnedcerts/blob/master/pinnedcerts/src/main/java/co/infinum/https/RetrofitClientBuilder.java
        // Current implementation: http://stackoverflow.com/questions/24006545/how-can-i-pin-a-certificate-with-square-okhttp
        // [KV]
        InputStream inputStream = this.getClass().getResourceAsStream("/keystore.bks");
        KeyStore trusted = KeyStore.getInstance("BKS"); // HttpClientBuilder.BOUNCY_CASTLE
        trusted.load(inputStream, KEYSTORE_PASSWORD.toCharArray());

        TrustManagerFactory trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trusted);

        KeyManagerFactory keyManagerFactory =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(trusted, KEYSTORE_PASSWORD.toCharArray());

        SSLContext sslContext = SSLContext.getInstance("TLS"); // SSLSocketFactory.TLS
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        mSSLSocketFactory = sslContext.getSocketFactory();

        return this;
    }

    public RetrofitClientBuilder ignoreCertificates()
            throws NoSuchAlgorithmException, KeyManagementException {
        X509TrustManager easyTrustManager = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        };

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                easyTrustManager
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());

        mSSLSocketFactory = sc.getSocketFactory();

        return this;
    }

    public OkHttpClient build() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (mConnectionTimeout != NO_TIMEOUT) {
            builder.connectTimeout(mConnectionTimeout, mConnectionTimeoutTimeUnit);
        }
        if (mReadTimeout != NO_TIMEOUT) {
            builder.readTimeout(mReadTimeout, mReadTimeoutTimeUnit);
        }
        if (mCache != null) {
            builder.cache(mCache);
        }
        for (Interceptor interceptor : mNetworkInterceptorList) {
            builder.addNetworkInterceptor(interceptor);
        }
        for (Interceptor interceptor : mInterceptorList) {
            builder.addInterceptor(interceptor);
        }
        if (mSSLSocketFactory != null) {
            builder.sslSocketFactory(mSSLSocketFactory);
        }

        return builder.build();
    }


}
