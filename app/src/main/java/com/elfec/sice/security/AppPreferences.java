package com.elfec.sice.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.SoftReference;

/**
 * Maneja las sharedpreferences de toda la aplicación
 *
 * @author Diego
 */
public class AppPreferences {
    private final String AUTH_TOKEN = "ws-token";
    private final String TOKEN_TYPE = "ws-token-type";

    /**
     * Contexto de app
     */
    private static Context context;
    /**
     * Referencia débil de la instancia de appPreferences
     */
    private static SoftReference<AppPreferences> appPreferencesInstanceRef;

    private SharedPreferences preferences;

    private AppPreferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    /**
     * Este método se debe llamar al inicializar la aplicación
     *
     * @param context context
     */
    public static void init(Context context) {
        AppPreferences.context = context;
    }

    /**
     * Obtiene el contexto de la aplicación
     *
     * @return el contexto de la aplicación
     */
    public static Context getApplicationContext() {
        return AppPreferences.context;
    }

    public static AppPreferences instance() {
        if (appPreferencesInstanceRef == null || appPreferencesInstanceRef.get() == null) {
            if (context == null)
                throw new IllegalStateException("You should call init() before getting the " +
                        "context");
            appPreferencesInstanceRef = new SoftReference<>(new AppPreferences(context));
        }
        return appPreferencesInstanceRef.get();
    }

    /**
     * Gets the saved token type
     * @return token type or null if no token is registered
     */
    public String getTokenType(){
        return preferences.getString(TOKEN_TYPE, null);
    }

    /**
     * Sets the token type
     * @param tokenType token type
     * @return current instance of {@link AppPreferences}
     */
    public AppPreferences setTokenType(String tokenType){
        preferences.edit().putString(TOKEN_TYPE, tokenType).apply();
        return this;
    }

    /**
     * Gets the saved authentication token
     * @return auth token or null if no token is registered
     */
    public String getAuthToken(){
        return preferences.getString(AUTH_TOKEN, null);
    }

    /**
     * Sets the authentication token
     * @param authToken authentication token
     * @return current instance of {@link AppPreferences}
     */
    public AppPreferences setAuthToken(String authToken){
        preferences.edit().putString(AUTH_TOKEN, authToken).apply();
        return this;
    }


    /**
     * Elimina la instancia cacheada junto con su contexto
     */
    public static void dispose() {
        if (appPreferencesInstanceRef != null)
            appPreferencesInstanceRef.clear();
        appPreferencesInstanceRef = null;
    }
}
