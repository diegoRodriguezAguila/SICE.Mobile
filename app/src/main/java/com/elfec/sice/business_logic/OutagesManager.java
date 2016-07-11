package com.elfec.sice.business_logic;

import com.elfec.sice.model.Outage;
import com.elfec.sice.model.web_services.PaginatedList;
import com.elfec.sice.security.SessionManager;
import com.elfec.sice.web_services.ServiceGenerator;
import com.elfec.sice.web_services.endpoints.OutageService;

import rx.Observable;

/**
 * Created by drodriguez on 08/07/2016.
 * bussiness logic for outages
 */
public class OutagesManager {

    /**
     * Connects to ws to get scheduled outages
     *
     * @param page page number
     * @return observable for paginated list of outages
     */
    public static Observable<PaginatedList<Outage>> getScheduledOutages(Integer page) {
        return ServiceGenerator
                .create(OutageService.class,
                        SessionManager.instance().getAccessToken())
                .getScheduledOutages(page);
    }

    /**
     * Conects to ws to get the details of a scheduled outage
     *
     * @param id scheduled outage's id
     * @return observable of the result outage
     */
    public static Observable<Outage> scheduledOutageDetails(int id) {
        String containDetails = "PowerPoles.SupportedCompanies";
        return ServiceGenerator
                .create(OutageService.class,
                        SessionManager.instance().getAccessToken())
                .scheduledOutage(id, containDetails);
    }
}
