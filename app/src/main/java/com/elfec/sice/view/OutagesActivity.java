package com.elfec.sice.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.elfec.sice.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OutagesActivity extends AppCompatActivity implements LoginFragment.OnLoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outages);
        LoginFragment login =  new LoginFragment();
        login.setCancelable(false);
        login.show(getSupportFragmentManager(),"Login");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onAuthenticated(String companyName) {

    }
}
