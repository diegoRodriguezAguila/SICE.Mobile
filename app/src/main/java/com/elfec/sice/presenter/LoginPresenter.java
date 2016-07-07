package com.elfec.sice.presenter;

import com.elfec.sice.presenter.views.ILoginView;

/**
 * Created by drodriguez on 07/07/2016.
 * Presenter for Login view abstractions
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView view) {
        super(view);
    }

    public void login() {
        String username = mView.getUsername();
        String regToken = mView.getRegToken();
    }
}
