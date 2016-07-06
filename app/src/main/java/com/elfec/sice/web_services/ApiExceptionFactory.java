package com.elfec.sice.web_services;

import com.elfec.sice.model.exceptions.ApiException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Creates ApiException from responses
 */
public class ApiExceptionFactory {

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    public static final ApiException DEFAULT_ERROR =
            new ApiException("Error desconocido, intente nuevamente mas tarde.", -1);

    /**
     * Construye un error a partir de un Response de retrofit
     * @param response response de retrofit
     * @return {@link ApiException} apiError
     */
    public static ApiException build(Response<?> response){
        return build(response.errorBody());
    }

    /**
     * Construye un error a partir de un errorBody de un Response
     * @param errorBody error body
     * @return {@link ApiException} apiError
     */
    public static ApiException build(ResponseBody errorBody){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        ApiException error;
        try {
            error = gson.fromJson(errorBody.string(),
                    ApiException.class);
            return error;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULT_ERROR;
    }
}
