package com.elfec.sice.helpers.utils;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.Collection;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by drodriguez on 11/07/2016.
 * utils for google maps handling
 */
public class GoogleMapUtils {
    /**
     * Gets an observable for handling get map async task. This observable runs on main thread
     * becuase its a requirement of {@link SupportMapFragment#getMapAsync(OnMapReadyCallback)}
     *
     * @param mapFragment map fragment
     * @return observable of google map
     */
    public static Observable<GoogleMap> getMapObservable(SupportMapFragment mapFragment) {
        Observable<GoogleMap> obs = Observable.create(subs -> {
            if (!subs.isUnsubscribed()) {
                try {
                    mapFragment.getMapAsync(googleMap -> {
                        subs.onNext(googleMap);
                        subs.onCompleted();
                    });
                } catch (Throwable e) {
                    subs.onError(e);
                    subs.onCompleted();
                }
            }
        });
        return obs.subscribeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Calculates the central point
     *
     * @param points points group
     * @return central point
     */
    public static LatLng calculateCenter(@NonNull Collection<LatLng> points) {
        if (points.size() == 0)
            return new LatLng(-17.393353, -66.164009);
        double latSum = 0;
        double longSum = 0;
        for (LatLng point : points) {
            latSum += point.latitude;
            longSum += point.longitude;
        }
        return new LatLng(latSum / points.size(), longSum / points.size());
    }
}
