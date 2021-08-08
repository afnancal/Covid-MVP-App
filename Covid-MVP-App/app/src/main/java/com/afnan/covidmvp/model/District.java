package com.afnan.covidmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public class District {

    @SerializedName("districtData")
    private Map<String, Covid> results;

    @SerializedName("statecode")
    private String statecode;


    public Map<String, Covid> getResults() {
        return results;
    }

    public void setResults(Map<String, Covid> results) {
        this.results = results;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

}
