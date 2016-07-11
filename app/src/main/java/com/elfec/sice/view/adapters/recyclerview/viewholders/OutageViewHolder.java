package com.elfec.sice.view.adapters.recyclerview.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.elfec.sice.R;
import com.elfec.sice.helpers.ui.ButtonClicksHelper;
import com.elfec.sice.model.Outage;
import com.elfec.sice.view.PowerPolesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by drodriguez on 08/07/2016.
 * viewholder for outages
 */
public class OutageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.lbl_day)
    protected TextView mTxtDay;
    @BindView(R.id.lbl_time)
    protected TextView mTxtTime;
    @BindView(R.id.lbl_zones)
    protected TextView mTxtZones;

    private View mRootView;

    public OutageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mRootView = itemView;
    }

    public void bindOutage(Outage outage) {
        final Context context = mRootView.getContext();
        mTxtDay.setText(outage.getStartDate()
                .toString(context.getString(R.string.outage_date_format)));
        mTxtTime.setText(context.getString(R.string.lbl_time_range,
                outage.getStartDate().toString(context.getString(R.string.outage_time_format)),
                outage.getEndDate().toString(context.getString(R.string.outage_time_format))));
        mTxtZones.setText(outage.getZones());
        mRootView.setOnClickListener(view -> {
            if (!ButtonClicksHelper.canClickButton())
                return;
            Intent powerPoles = new Intent(context, PowerPolesActivity.class);
            powerPoles.putExtra(PowerPolesActivity.OUTAGE_ID, outage.getId());
            context.startActivity(powerPoles);
            if (context instanceof AppCompatActivity)
                ((AppCompatActivity) context)
                        .overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);

        });
    }
}
