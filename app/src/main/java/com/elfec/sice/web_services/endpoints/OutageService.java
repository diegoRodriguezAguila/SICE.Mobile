package com.elfec.sice.web_services.endpoints;

import com.elfec.sice.model.Outage;
import com.elfec.sice.model.web_services.PaginatedList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by drodriguez on 08/07/2016.
 * endpoint for scheduled outages
 */
public interface OutageService {
    @GET("scheduled_outages")
    Observable<PaginatedList<Outage>> getScheduledOutages(@Query("page") Integer page);
}
