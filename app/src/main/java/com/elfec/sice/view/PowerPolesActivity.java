package com.elfec.sice.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.elfec.sice.R;
import com.elfec.sice.helpers.utils.GoogleMapUtils;
import com.elfec.sice.model.Outage;
import com.elfec.sice.model.PowerPole;
import com.elfec.sice.presenter.PowerPolesPresenter;
import com.elfec.sice.presenter.views.IPowerPolesView;
import com.elfec.sice.view.adapters.marker.MarkerPopupAdapter;
import com.elfec.sice.web_services.ServiceErrorFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PowerPolesActivity extends FragmentActivity implements IPowerPolesView {

    public static final String OUTAGE_ID = "outage-id";
    private static final float DEFAULT_ZOOM = 16.5f;

    private PowerPolesPresenter mPresenter;

    private GoogleMap mMap;
    private BitmapDescriptor mBitmapPowerPole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_poles);
        Bundle extras = getIntent().getExtras();
        if (extras == null)
            throw new RuntimeException("OUTAGE_ID must be passed as extra!");
        int outageId = extras.getInt(OUTAGE_ID);
        if (outageId == 0)
            throw new RuntimeException("OUTAGE_ID must be passed as extra!");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mPresenter = new PowerPolesPresenter(this);
        mBitmapPowerPole = BitmapDescriptorFactory.fromResource(R.drawable.power_pole);
        loadData(outageId, mapFragment);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }


    private void loadData(int outageId, SupportMapFragment mapFragment) {
        Observable<GoogleMap> mapObs = GoogleMapUtils.getMapObservable(mapFragment);
        Observable<Outage> outObs = mPresenter.loadScheduledOutage(outageId);
        Observable.zip(mapObs, outObs, this::onMapAndOutageReady)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(outage -> {
                }, this::onError);
    }

    public Outage onMapAndOutageReady(GoogleMap googleMap, Outage outage) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new MarkerPopupAdapter(this));

        LatLng center = addMarkers(outage);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, DEFAULT_ZOOM));

        return outage;
    }

    /**
     * Adds the markers of the power poles to the map. Also returns the average center of
     * all the power poles
     *
     * @param outage outage
     * @return point of center of power poles
     */
    private LatLng addMarkers(Outage outage) {
        if (outage.getPowerPoles() == null || outage.getPowerPoles().size() == 0) {
            Toast.makeText(PowerPolesActivity.this, R.string.msg_no_power_poles, Toast.LENGTH_LONG).show();
        }
        List<LatLng> points = new ArrayList<>();
        Gson gson = new Gson();
        for (PowerPole pole : outage.getPowerPoles()) {
            LatLng point = new LatLng(pole.getLatitude(), pole.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(point).snippet(gson.toJson(pole))
                    .title(pole.getPoleCode()).icon(mBitmapPowerPole));
            points.add(point);
        }
        return GoogleMapUtils.calculateCenter(points);
    }


    //region Interface Methods
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void onError(Throwable e) {
        Toast.makeText(this, "ERROR: " + ServiceErrorFactory.fromThrowable(e).getMessage(),
                Toast.LENGTH_LONG).show();
    }
    //endregion
}
