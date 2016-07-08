package com.elfec.sice.model;

import com.elfec.sice.model.enums.ApiStatus;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by drodriguez on 08/07/2016.
 * Outage model
 */
public class Outage {
    private int id;
    private DateTime startDate;
    private DateTime endDate;
    private String zones;
    private String industries;
    private String buildings;
    private String hospitals;
    private String radioAntennas;
    private String farms;
    private List<PowerPole> powerPoles;
    private ApiStatus status;

    //region getters setters


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public String getZones() {
        return zones;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    public String getIndustries() {
        return industries;
    }

    public void setIndustries(String industries) {
        this.industries = industries;
    }

    public String getBuildings() {
        return buildings;
    }

    public void setBuildings(String buildings) {
        this.buildings = buildings;
    }

    public String getHospitals() {
        return hospitals;
    }

    public void setHospitals(String hospitals) {
        this.hospitals = hospitals;
    }

    public String getRadioAntennas() {
        return radioAntennas;
    }

    public void setRadioAntennas(String radioAntennas) {
        this.radioAntennas = radioAntennas;
    }

    public String getFarms() {
        return farms;
    }

    public void setFarms(String farms) {
        this.farms = farms;
    }

    public List<PowerPole> getPowerPoles() {
        return powerPoles;
    }

    public void setPowerPoles(List<PowerPole> powerPoles) {
        this.powerPoles = powerPoles;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }
    //endregion
}
