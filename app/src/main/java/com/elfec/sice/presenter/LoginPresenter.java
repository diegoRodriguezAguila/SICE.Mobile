package com.elfec.sice.presenter;

import android.text.TextUtils;

import com.elfec.sice.R;
import com.elfec.sice.presenter.views.ILoginView;
import com.elfec.sice.security.SessionManager;
import com.elfec.sice.web_services.ServiceErrorFactory;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by drodriguez on 07/07/2016.
 * Presenter for Login view abstractions
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView view) {
        super(view);
    }

    public void logIn() {
        String username = mView.getUsername();
        String regToken = mView.getRegToken();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(regToken))
            return;
        mView.onProcessing(R.string.msg_login_user);
        SessionManager.instance().logIn(username, regToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(session -> {
                    mView.onSuccess(session.getUsername());
                }, t -> mView.onError(ServiceErrorFactory
                        .fromThrowable(t)));
    }
}
