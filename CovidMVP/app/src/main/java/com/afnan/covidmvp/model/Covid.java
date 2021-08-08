package com.afnan.covidmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public class Covid {

    private String state;
    private int totalDist;

    @SerializedName("notes")
    private String notes;

    @SerializedName("active")
    private int active;

    @SerializedName("confirmed")
    private int confirmed;

    @SerializedName("migratedother")
    private int migratedother;

    @SerializedName("deceased")
    private int deceased;

    @SerializedName("recovered")
    private int recovered;


    public Covid(String state, int totalDist, String notes, int active, int confirmed, int migratedother, int deceased, int recovered) {
        this.state = state;
        this.totalDist = totalDist;
        this.notes = notes;
        this.active = active;
        this.confirmed = confirmed;
        this.migratedother = migratedother;
        this.deceased = deceased;
        this.recovered = recovered;
    }

    public Covid(String state, int totalDist, int totalConfirmed, int totalActive, int totalDeath) {
        this.state = state;
        this.totalDist = totalDist;
        this.confirmed = totalConfirmed;
        this.active = totalActive;
        this.deceased = totalDeath;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalDist() {
        return totalDist;
    }

    public void setTotalDist(int totalDist) {
        this.totalDist = totalDist;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getMigratedother() {
        return migratedother;
    }

    public void setMigratedother(int migratedother) {
        this.migratedother = migratedother;
    }

    public int getDeceased() {
        return deceased;
    }

    public void setDeceased(int deceased) {
        this.deceased = deceased;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    /*@Override
    public String toString() {
        return "Covid{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", rating=" + rating +
                ", thumbPath='" + thumbPath + '\'' +
                ", overview='" + overview + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", credits=" + credits +
                ", runTime='" + runTime + '\'' +
                ", tagline='" + tagline + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }*/

}
