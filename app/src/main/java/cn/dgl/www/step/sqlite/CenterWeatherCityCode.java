package cn.dgl.www.step.sqlite;

import java.io.Serializable;

/**
 * Created by dugaolong on 17/12/28.
 */

public class CenterWeatherCityCode implements Serializable {

    private String countryID;
    private String countryName;
    private String countryEN;
    private String areaID;
    private String areaName;
    private String areaEN;
    private String cityID;
    private String cityName;
    private String cityEN;
    private String townID;
    private String townName;
    private String townEN;

    public CenterWeatherCityCode() {
    }

    public CenterWeatherCityCode(String countryID, String countryName, String countryEN, String areaID, String areaName, String areaEN, String cityID, String cityName, String cityEN, String townID, String townName, String townEN) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.countryEN = countryEN;
        this.areaID = areaID;
        this.areaName = areaName;
        this.areaEN = areaEN;
        this.cityID = cityID;
        this.cityName = cityName;
        this.cityEN = cityEN;
        this.townID = townID;
        this.townName = townName;
        this.townEN = townEN;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryEN() {
        return countryEN;
    }

    public void setCountryEN(String countryEN) {
        this.countryEN = countryEN;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaEN() {
        return areaEN;
    }

    public void setAreaEN(String areaEN) {
        this.areaEN = areaEN;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityEN() {
        return cityEN;
    }

    public void setCityEN(String cityEN) {
        this.cityEN = cityEN;
    }

    public String getTownID() {
        return townID;
    }

    public void setTownID(String townID) {
        this.townID = townID;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownEN() {
        return townEN;
    }

    public void setTownEN(String townEN) {
        this.townEN = townEN;
    }

    @Override
    public String toString() {
        return "CenterWeatherCityCode{" +
                "countryID='" + countryID + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryEN='" + countryEN + '\'' +
                ", areaID='" + areaID + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaEN='" + areaEN + '\'' +
                ", cityID='" + cityID + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityEN='" + cityEN + '\'' +
                ", townID='" + townID + '\'' +
                ", townName='" + townName + '\'' +
                ", townEN='" + townEN + '\'' +
                '}';
    }
}
