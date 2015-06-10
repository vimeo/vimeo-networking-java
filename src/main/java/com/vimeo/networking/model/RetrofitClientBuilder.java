package com.vimeo.networking.model;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.vimeo.networking.TrustKeyStore;

import java.io.IOException;
import java.net.CookieHandler;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

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

    public RetrofitClientBuilder pinCertificates(TrustKeyStore trustKeyStore)
            throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException,
                   KeyManagementException {

        // For full implementation refer to https://github.com/ikust/hello-pinnedcerts/blob/master/pinnedcerts/src/main/java/co/infinum/https/RetrofitClientBuilder.java
        // Current implementation: http://stackoverflow.com/questions/24006545/how-can-i-pin-a-certificate-with-square-okhttp
        // [KV]
        KeyStore trusted = KeyStore.getInstance("BKS");
        trusted.load(trustKeyStore.mKeyStoreInputStream, trustKeyStore.mPassword.toCharArray());
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trusted);
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

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
