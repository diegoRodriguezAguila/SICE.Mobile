package com.elfec.sice.presenter.views;

import android.support.annotation.StringRes;

/**
 * Created by drodriguez on 07/07/2016.
 * View abstraction of a proces where implies a loading step,
 * on error and on success. It extends from {@link IBaseView}, so
 * is not necessary to extend it in its child interfaces.
 */
public interface ILoadView<T> extends IBaseView{
    /**
     * Executed when loading starts
     * @param message message for loading status
     */
    void onLoading(@StringRes int message);

    /**
     * Called when an error while loading ocurred
     * @param e error
     */
    void onError(Throwable e);

    /**
     * Called when the loading finished successfully
     * @param result result of loading
     */
    void onLoaded(T result);
}
