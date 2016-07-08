package com.elfec.sice.presenter.views;

import com.elfec.sice.model.Outage;
import com.elfec.sice.model.web_services.PaginatedList;

/**
 * Created by drodriguez on 07/07/2016.
 * Outages view abstraction
 */
public interface IOutagesView extends ILoadView<PaginatedList<Outage>> {
    /**
     * Asks the user for authentication
     */
    void requestAuthentication();
}
