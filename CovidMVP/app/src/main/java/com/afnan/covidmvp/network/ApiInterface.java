package com.afnan.covidmvp.network;

import com.afnan.covidmvp.model.District;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public interface ApiInterface {

    @GET("state_district_wise.json")
    Call<Map<String, District>> getCovidObj();
}
