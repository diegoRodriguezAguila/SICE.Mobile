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
    private final String REGISTRATION_TOKEN = "reg-token";

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
     * Obtiene el token de registración, guardado por el usuario
     *
     *
     * @return null si es que aun no se registró token
     */
    public String getRegistrationToken() {
        return preferences.getString(REGISTRATION_TOKEN, null);
    }

    /**
     * Asigna el token de registración, que se utilizará para obtener un token de autenticación
     * en los web services
     *
     * @return la instancia actual de {@link AppPreferences}
     */
    public AppPreferences setRegistrationToken(String loggedUsername) {
        preferences.edit().putString(REGISTRATION_TOKEN, loggedUsername).apply();
        return this;
    }

    /**
     * Obtiene el token de autenticación para webservices
     *
     * @return null si es que no se autenticó el token de registración
     */
    public String getAuthToken() {
        return preferences.getString(AUTH_TOKEN, null);
    }

    /**
     * Asigna el token de autenticación para webservices, sobreescribe cualquier token que haya
     * sido guardado antes
     *
     * @return la instancia actual de {@link AppPreferences}
     */
    public AppPreferences setAuthToken(String loggedUsername) {
        preferences.edit().putString(AUTH_TOKEN, loggedUsername).apply();
        return this;
    }

    /**
     * Elimina la instancia cacheada junto con su contexto
     */
    public static void dispose() {
        context = null;
        if (appPreferencesInstanceRef != null)
            appPreferencesInstanceRef.clear();
        appPreferencesInstanceRef = null;
    }
}
