package com.elfec.sice.presenter;

import com.elfec.sice.presenter.views.IBaseView;

/**
 * Created by drodriguez on 07/07/2016.
 * Base presenter
 */
public abstract class BasePresenter<T extends IBaseView> {

    protected T mView;
    public BasePresenter(T view){
        mView = view;
    }
}
