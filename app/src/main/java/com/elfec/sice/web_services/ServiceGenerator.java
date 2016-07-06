package com.elfec.sice.web_services;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elfec.sice.BuildConfig;
import com.elfec.sice.model.security.AccessToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Factory para obtener el Endpoint de los webservices
 * Utilizando la configuración de RestAdapter necesaria
 */
public class ServiceGenerator {

    /**
     * La URL de los web services de SGAM, si fuera necesario conectar a otro webservice
     * se puede pasar otra URL
     */
    public static final String BASE_URL = BuildConfig.WS_SERVER_URL;
    /**
     * Caché para el builder, para evitar redundancia de creación
     */
    private static WeakReference<Retrofit.Builder> sBuilder;
    /**
     * Caché para clientes http, para evitar redundancia de creación
     */
    private static WeakReference<OkHttpClient> sClient;

    /**
     * Crea un endpoint Rest  con la url especificada
     *
     * @param url     especificada
     * @param service servicio especificado
     * @return Endpoint
     */
    public static <T> T create(@NonNull String url, @NonNull Class<T> service) {
        return create(url, service, null);
    }

    /**
     * Crea un endpoint Rest  con la url por defecto {@link ServiceGenerator#BASE_URL}
     *
     * @return Endpoint
     */
    public static <T> T create(@NonNull Class<T> service) {
        return create(BASE_URL, service);
    }

    /**
     * Crea un endpoint Rest  con la url por defecto {@link ServiceGenerator#BASE_URL}
     * y con los headers de autenticación con token necesarios
     *
     * @param service endpoint
     * @param token   token de autenticación
     * @param <T>     endpoint type
     * @return endpoint instance
     */
    public static <T> T create(@NonNull Class<T> service, AccessToken token) {
        return create(BASE_URL, service, token);
    }

    /**
     * Crea un endpoint Rest con la url especificada
     * y con los headers de autenticación con token necesarios
     *
     * @param url      especificada
     * @param endpoint tipo endpoint
     * @param token    token auth
     * @param <T>      tipo endpoint
     * @return Instancia de endpoint
     */
    public static <T> T create(@NonNull String url, @NonNull Class<T> endpoint,
                               @Nullable AccessToken token) {
        return getBuilder()
                .baseUrl(url)
                .client(getClient(token))
                .build().create(endpoint);
    }

    /**
     * Obtiene el builder para retrofit
     *
     * @return {@link retrofit2.Retrofit.Builder}
     */
    @NonNull
    private synchronized static Retrofit.Builder getBuilder() {
        if (sBuilder == null || sBuilder.get() == null)
            sBuilder = new WeakReference<>(new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().setFieldNamingPolicy(
                                    FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create())));
        return sBuilder.get();
    }

    /**
     * Obtiene el cliente de http para retrofit, si ya estaba creado devuelve la caché
     * y si no existe lo crea
     *
     * @param token credentials of API
     * @return {@link OkHttpClient} http client, not null
     */
    @NonNull
    private synchronized static OkHttpClient getClient(@Nullable final AccessToken token) {
        if (token == null && sClient != null)
            sClient.clear();
        if (sClient == null || sClient.get() == null) {
            sClient = new WeakReference<>(buildClient(token));
        }
        return sClient.get();
    }

    /**
     * Crea el cliente de http para retrofit
     *
     * @param token credentials of API
     * @return {@link OkHttpClient} http client
     */
    @NonNull
    private static OkHttpClient buildClient(@Nullable final AccessToken token) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder()
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json");
                    if (token != null) {
                        builder.header("Authorization", token.getTokenType() + " " +
                                token.getAccessToken());
                    }
                    return chain.proceed(builder.build());
                }).build();
    }
}
