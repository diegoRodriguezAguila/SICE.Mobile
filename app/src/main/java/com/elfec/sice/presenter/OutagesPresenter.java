package com.elfec.sice.presenter;

import com.elfec.sice.presenter.views.IOutagesView;
import com.elfec.sice.security.SessionManager;

/**
 * Created by drodriguez on 07/07/2016.
 * Outages view's presenter
 */
public class OutagesPresenter extends BasePresenter<IOutagesView> {

    public OutagesPresenter(IOutagesView view) {
        super(view);
    }

    public void loadOutages(){
        if(!SessionManager.instance().isAuthenticated()){
            mView.requestAuthentication();
            return;
        }
        int i = 0;
        //TODO actually load outages
    }
}
