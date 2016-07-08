package com.elfec.sice.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.elfec.sice.R;
import com.elfec.sice.model.Outage;
import com.elfec.sice.model.web_services.PaginatedList;
import com.elfec.sice.presenter.OutagesPresenter;
import com.elfec.sice.presenter.views.IOutagesView;
import com.elfec.sice.view.adapters.recyclerview.OutageAdapter;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OutagesActivity extends AppCompatActivity implements IOutagesView, LoginFragment
        .OnLoginListener {

    private OutagesPresenter mPresenter;
    private LoginFragment mLoginDialog;
    private OutageAdapter mAdapter;

    @BindView(R.id.outage_list)
    protected SuperRecyclerView mOutageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outages);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mOutageList.setLayoutManager(layoutManager);
        mOutageList.setRefreshListener(() -> mPresenter.loadOutages());
        mPresenter = new OutagesPresenter(this);
        mPresenter.loadOutages();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onAuthenticated(String companyUsername) {
        if (mLoginDialog != null) {
            mLoginDialog.dismiss();
            mPresenter.loadOutages();
        }
    }

    //region Interface Methods

    @Override
    public void requestAuthentication() {
        mLoginDialog = new LoginFragment();
        mLoginDialog.setCancelable(false);
        mLoginDialog.show(getSupportFragmentManager(), "Login");
    }

    @Override
    public void onLoading(@StringRes int message) {
        mOutageList.showMoreProgress();
    }

    @Override
    public void onError(Throwable e) {
        mOutageList.setAdapter(mAdapter);
        Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoaded(PaginatedList<Outage> result) {
        mOutageList.hideMoreProgress();
        if (result.getPagination().getPage() > 1) {
            mAdapter.pushItems(result.getData());
            return;
        }
        mAdapter = new OutageAdapter(result.getData());
        mOutageList.setAdapter(mAdapter);
        setOnMoreListener();
    }

    //endregion

    private void setOnMoreListener() {
        mOutageList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {}
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(!recyclerView.canScrollVertically(1)){
                    mPresenter.loadMoreOutages();
                }
            }
        });
    }
}
