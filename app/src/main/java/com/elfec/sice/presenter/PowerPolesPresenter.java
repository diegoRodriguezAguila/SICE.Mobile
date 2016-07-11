package com.elfec.sice.presenter;

import com.elfec.sice.business_logic.OutagesManager;
import com.elfec.sice.model.Outage;
import com.elfec.sice.presenter.views.IPowerPolesView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by drodriguez on 11/07/2016.
 * power poles view's presenter
 */
public class PowerPolesPresenter extends BasePresenter<IPowerPolesView> {

    public PowerPolesPresenter(IPowerPolesView view) {
        super(view);
    }

    public Observable<Outage> loadScheduledOutage(int outageId) {
        return OutagesManager.scheduledOutageDetails(outageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
