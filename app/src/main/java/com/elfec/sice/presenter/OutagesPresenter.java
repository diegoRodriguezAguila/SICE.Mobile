package com.elfec.sice.presenter;

import com.elfec.sice.R;
import com.elfec.sice.business_logic.OutagesManager;
import com.elfec.sice.presenter.views.IOutagesView;
import com.elfec.sice.security.SessionManager;
import com.elfec.sice.web_services.ServiceErrorFactory;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by drodriguez on 07/07/2016.
 * Outages view's presenter
 */
public class OutagesPresenter extends BasePresenter<IOutagesView> {

    private int mCurrentPage;
    private boolean mCanLoadMore;
    private boolean mIsLoading;

    public OutagesPresenter(IOutagesView view) {
        super(view);
    }

    public void loadOutages() {
        mCurrentPage = 0;
        mCanLoadMore = true;
        loadMoreOutages();
    }

    public void loadMoreOutages() {
        if (mIsLoading)
            return;
        if (!SessionManager.instance().isAuthenticated()) {
            mView.requestAuthentication();
            return;
        }
        if (!mCanLoadMore)
            return;
        mIsLoading = true;
        mCurrentPage++;
        if (mCurrentPage > 1)
            mView.onLoading(R.string.lbl_loading_outages);
        OutagesManager.getScheduledOutages(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(outagePaginatedList -> {
                            mCanLoadMore = outagePaginatedList.getPagination().hasNextPage();
                            mView.onLoaded(outagePaginatedList);
                        }, t -> mView.onError(ServiceErrorFactory.fromThrowable(t)),
                        () -> mIsLoading = false);
    }
}
