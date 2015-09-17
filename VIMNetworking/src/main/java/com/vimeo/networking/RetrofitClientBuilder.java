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

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import retrofit.client.OkClient;

/**
 * Builder for creating Square OkHttpClient with pinned certificate that can be used with Retrofit.
 * </p>
 * Created by kylevenn on 6/10/15.
 */
public class RetrofitClientBuilder {

    private static final String KEYSTORE_PASSWORD = "vimeo123";

    protected OkHttpClient okHttpClient = new OkHttpClient();

    public RetrofitClientBuilder setConnectionTimeout(int connectionTimeout) {
        okHttpClient.setConnectTimeout(connectionTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    public RetrofitClientBuilder setCookieStore(CookieHandler cookieHandler) {
        okHttpClient.setCookieHandler(cookieHandler);
        return this;
    }

    public RetrofitClientBuilder setCache(Cache cache) {
        okHttpClient.setCache(cache);
        return this;
    }

    public RetrofitClientBuilder addNetworkInterceptor(Interceptor interceptor) {
        okHttpClient.networkInterceptors().add(interceptor);
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

        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trusted);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory
                .getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(trusted, KEYSTORE_PASSWORD.toCharArray());

        SSLContext sslContext = SSLContext.getInstance("TLS"); // SSLSocketFactory.TLS
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());

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
        sc.init(null, trustAllCerts, new java.security.SecureRandom());

        okHttpClient.setSslSocketFactory(sc.getSocketFactory());

        return this;
    }

    public OkClient build() {
        return new OkClient(okHttpClient);
    }


}
