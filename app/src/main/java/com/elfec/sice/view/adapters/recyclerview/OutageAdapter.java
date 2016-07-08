package com.elfec.sice.view.adapters.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfec.sice.R;
import com.elfec.sice.model.Outage;
import com.elfec.sice.view.adapters.recyclerview.viewholders.OutageViewHolder;

import java.util.Collection;
import java.util.List;

/**
 * Created by drodriguez on 08/07/2016.
 * recyclerview adapter for outages
 */
public class OutageAdapter extends RecyclerView.Adapter<OutageViewHolder>{

    private List<Outage> mOutages;

    public OutageAdapter(List<Outage> outages) {
        this.mOutages = outages;
    }

    @Override
    public OutageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.outage_list_item, parent, false);
        return new OutageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OutageViewHolder holder, int position) {
        holder.bindOutage(mOutages.get(position));
    }

    @Override
    public int getItemCount() {
        return mOutages.size();
    }

    /**
     * Push items at the end of the list
     * @param outages outages to add
     */
    public void pushItems(Collection<Outage> outages){
        mOutages.addAll(outages);
        this.notifyDataSetChanged();
    }
}
