package com.elfec.sice.model;

import com.elfec.sice.model.enums.ApiStatus;

import java.util.List;

/**
 * Created by drodriguez on 08/07/2016.
 * Power pole model
 */
public class PowerPole {
    private int id;
    private String poleCode;
    private String material;
    private String condition;
    private String owner;
    private String tensionType;
    private String poleType;
    private double latitude;
    private double longitude;
    private List<SupportedCompany> supportedCompanies;
    private ApiStatus status;

    //region getter setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoleCode() {
        return poleCode;
    }

    public void setPoleCode(String poleCode) {
        this.poleCode = poleCode;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTensionType() {
        return tensionType;
    }

    public void setTensionType(String tensionType) {
        this.tensionType = tensionType;
    }

    public String getPoleType() {
        return poleType;
    }

    public void setPoleType(String poleType) {
        this.poleType = poleType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<SupportedCompany> getSupportedCompanies() {
        return supportedCompanies;
    }

    public void setSupportedCompanies(List<SupportedCompany> supportedCompanies) {
        this.supportedCompanies = supportedCompanies;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    //endregion
}
