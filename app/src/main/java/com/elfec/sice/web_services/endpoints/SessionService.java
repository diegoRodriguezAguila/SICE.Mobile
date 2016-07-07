package com.elfec.sice.web_services.endpoints;

import com.elfec.sice.model.security.Session;
import com.elfec.sice.model.security.AuthCredentials;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Representa el endpoint de las sesiones <i>/sessions</i> en la API
 */
public interface SessionService {

    @POST("sessions/companies")
    Observable<Session> logIn(@Body AuthCredentials credentials);

    @DELETE("sessions/{id}")
    Observable<Void> logOut(@Path("id") String token);

}
