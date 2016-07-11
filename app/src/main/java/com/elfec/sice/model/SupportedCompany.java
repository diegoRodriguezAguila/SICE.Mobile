package com.elfec.sice.model;

import com.elfec.sice.model.enums.ApiStatus;

/**
 * Created by drodriguez on 11/07/2016.
 * supported company model
 */
public class SupportedCompany {
    private int id;
    private int powerPoleId;
    private String company;
    private String supportType;
    private int amount;
    private ApiStatus status;

    //region getter setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPowerPoleId() {
        return powerPoleId;
    }

    public void setPowerPoleId(int powerPoleId) {
        this.powerPoleId = powerPoleId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    //endregion
}
