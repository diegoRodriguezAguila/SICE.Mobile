package com.elfec.sice.security;

import com.elfec.sice.model.security.AccessToken;
import com.elfec.sice.model.security.AuthCredentials;
import com.elfec.sice.model.security.Session;
import com.elfec.sice.web_services.ServiceGenerator;
import com.elfec.sice.web_services.endpoints.SessionService;

import java.lang.ref.SoftReference;

import rx.Observable;


/**
 * Created by drodriguez on 31/08/2015.
 * Clase que se encarga del manejo de las sesiones de los usuarios
 */
public class SessionManager {
    /**
     * Referencia débil utilizada para singletone
     */
    private static SoftReference<SessionManager> sessionManagerInstanceRef;

    /**
     * No se puede instanciar esta clase directamente, debe utilizar el método {@link
     * SessionManager#instance()}
     */
    private SessionManager() {
    }

    /**
     * Devuelve la isntancia actual de sessionmanager, si no existe o la instancia actual
     * ya fue recolectada por el garbage collector crea una nueva
     *
     * @return instancia del {@link SessionManager}, nunca retorna null
     */
    public static SessionManager instance() {
        if (sessionManagerInstanceRef == null || sessionManagerInstanceRef.get() == null)
            sessionManagerInstanceRef = new SoftReference<>(new SessionManager());
        return sessionManagerInstanceRef.get();
    }

    /**
     * Se conecta remotamente a los webservices para realizar un inicio de sesión
     * En caso de ser exitoso el inicio de sesión se guarda al usuario y su token de
     * autenticación actual y se inicializa en las variables de sesión el usuario logeado
     *
     * @param username company username
     * @param regToken registration token
     * @return observable de user
     */
    public Observable<Session> logIn(final String username, final String regToken) {
        return ServiceGenerator
                .create(SessionService.class)
                .logIn(new AuthCredentials(username, regToken))
                .map(this::setAccessToken);

    }

    /**
     * Se conecta remotamente a los webservices para realizar un cierre de sesión.<br/>
     * En caso de ser exitoso el cierre de sesión se replica de forma local,
     * eliminando las variables de sesión.
     *
     * @return observable
     */
    public Observable<Void> logOut() {
        throw new RuntimeException("Log out is not implemented!");
    }

    /**
     * Obtiene el token de autenticación para webservices
     *
     * @return null si es que no se autenticó el token de registración
     */
    public AccessToken getAccessToken() {
        String tokenType = AppPreferences.instance().getTokenType();
        String token = AppPreferences.instance().getAuthToken();
        if (tokenType == null || token == null)
            return null;
        return new AccessToken(token, tokenType);
    }

    /**
     * Asigna el token de autenticación para webservices, sobreescribe cualquier token que haya
     * sido guardado antes
     */
    public Session setAccessToken(Session session) {
        if (session == null)
            return null;
        AccessToken token = session.getAccessToken();
        setAccessToken(token.getTokenType(), token.getAccessToken());
        return session;
    }

    /**
     * Asigna el token de autenticación para webservices, sobreescribe cualquier token que haya
     * sido guardado antes
     */
    public void setAccessToken(String tokenType, String token) {
        AppPreferences.instance().setAuthToken(token)
                .setTokenType(tokenType);
    }

    /**
     * Verifica si existe un token de autenticación registrado.
     * Si existe el usuario está autenticado
     *
     * @return true si el usuario está autenticado
     */
    public boolean isAuthenticated() {
        String tokenType = AppPreferences.instance().getTokenType();
        String token = AppPreferences.instance().getAuthToken();
        return tokenType != null && token != null;
    }


    /**
     * Limpia las variables de la sesión actuales
     */
    private void clearSession() {
        AppPreferences.instance()
                .setTokenType(null)
                .setAuthToken(null);
    }

}
