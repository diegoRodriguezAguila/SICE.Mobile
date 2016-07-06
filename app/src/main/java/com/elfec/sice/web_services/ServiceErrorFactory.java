package com.elfec.sice.web_services;

import com.elfec.sice.model.exceptions.ServerConnectException;
import com.elfec.sice.model.exceptions.ServerSideException;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.zip.DataFormatException;

import retrofit2.adapter.rxjava.HttpException;


/**
 * Interpreta los errores que pudieron haber ocurrido al
 * contactar con un servicio web
 */
public class ServiceErrorFactory {

    /**
     * Interpreta un throwable a una excepción
     * @param throwable throwable
     * @return excepción correspondiente al error recibido
     */
    public static Exception fromThrowable(Throwable throwable){
        if (throwable instanceof JsonParseException)
            return new DataFormatException("La información recibida del servidor no es válida, " +
                    "detalles: "+throwable.getMessage());
        if (throwable instanceof IOException)
            return new ServerConnectException();
        if (throwable instanceof HttpException) {
            HttpException e = (HttpException) throwable;
            if (e.code()== HttpURLConnection.HTTP_INTERNAL_ERROR)
                return new ServerSideException();
            return ApiExceptionFactory.build(e.response());
        }
        if(throwable instanceof Exception)
            return (Exception) throwable;
        return ApiExceptionFactory.DEFAULT_ERROR;
    }
}
