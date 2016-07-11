package com.elfec.sice.view.adapters.marker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.elfec.sice.R;
import com.elfec.sice.helpers.utils.HtmlCompat;
import com.elfec.sice.model.PowerPole;
import com.elfec.sice.model.SupportedCompany;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by drodriguez on 11/07/2016.
 * marker popup adapter
 */
public class MarkerPopupAdapter implements GoogleMap.InfoWindowAdapter {

    @BindView(R.id.lbl_pole_code)
    protected TextView mTxtPoleCode;
    @BindView(R.id.lbl_pole_description)
    protected TextView mTxtPoleDescription;
    @BindView(R.id.lbl_pole_condition)
    protected TextView mTxtPoleCondition;
    @BindView(R.id.lbl_pole_owner)
    protected TextView mTxtPoleOwner;
    @BindView(R.id.lbl_supported_companies)
    protected TextView mTxtSupportedCompanies;

    private LayoutInflater mInflater;
    private Context mContext;
    private View mPopupView;

    public MarkerPopupAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getInfoContents(Marker marker) {
        if (mPopupView == null) {
            mPopupView = mInflater.inflate(R.layout.marker_power_pole_popup, null, false);
            ButterKnife.bind(this, mPopupView);
        }

        PowerPole powerPole = new Gson().fromJson(marker.getSnippet(), PowerPole.class);
        mTxtPoleCode.setText(powerPole.getPoleCode());

        String poleDesc = mContext.getString(R.string.pole_description_format,
                powerPole.getMaterial(), powerPole.getTensionType());
        mTxtPoleDescription.setText(HtmlCompat.fromHtml(poleDesc));

        String poleCond = mContext.getString(R.string.pole_condition_format,
                powerPole.getCondition());
        mTxtPoleCondition.setText(HtmlCompat.fromHtml(poleCond));

        String poleOwner = mContext.getString(R.string.pole_owner_format,
                powerPole.getOwner());
        mTxtPoleOwner.setText(HtmlCompat.fromHtml(poleOwner));
        List<SupportedCompany> companies = powerPole.getSupportedCompanies();
        if (companies == null || companies.size() == 0) {
            mTxtSupportedCompanies.setVisibility(View.GONE);
        }
        String supports = TextUtils.join(", ", companies);
        String supportsFormatted = mContext.getString(R.string.pole_supports_format, supports);
        mTxtSupportedCompanies.setText(HtmlCompat.fromHtml(supportsFormatted));
        return mPopupView;
    }

}
