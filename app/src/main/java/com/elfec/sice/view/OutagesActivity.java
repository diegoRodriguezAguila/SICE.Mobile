package com.elfec.sice.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.elfec.sice.R;
import com.elfec.sice.presenter.OutagesPresenter;
import com.elfec.sice.presenter.views.IOutagesView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OutagesActivity extends AppCompatActivity implements IOutagesView, LoginFragment
        .OnLoginListener {

    private OutagesPresenter mPresenter;
    private LoginFragment mLoginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outages);
        mPresenter = new OutagesPresenter(this);
        mPresenter.loadOutages();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onAuthenticated(String companyUsername) {
        if(mLoginDialog!=null){
            mLoginDialog.dismiss();
            mPresenter.loadOutages();
        }
    }

    //region Interface Methods

    @Override
    public void requestAuthentication() {
        mLoginDialog =  new LoginFragment();
        mLoginDialog.setCancelable(false);
        mLoginDialog.show(getSupportFragmentManager(),"Login");
    }

    //endregion
}
