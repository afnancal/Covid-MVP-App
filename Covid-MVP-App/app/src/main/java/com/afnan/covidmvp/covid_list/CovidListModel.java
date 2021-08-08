package com.afnan.covidmvp.covid_list;

import android.util.Log;

import com.afnan.covidmvp.model.Covid;
import com.afnan.covidmvp.model.District;
import com.afnan.covidmvp.network.ApiClient;
import com.afnan.covidmvp.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public class CovidListModel implements CovidListContract.Model {

    private final String TAG = "CovidListModel";

    /**
     * This function will fetch Covid data
     *
     * @param onFinishedListener
     * @param pageNo             : Which page to load.
     */
    @Override
    public void getCovidList(final OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        /*Call<CovidListResponse> call = apiService.getCovid();
        call.enqueue(new Callback<CovidListResponse>() {
            @Override
            public void onResponse(Call<CovidListResponse> call, Response<CovidListResponse> response) {
                List<Covid> covids = response.body().getResults();
                Log.d(TAG, "Number of covids received: " + covids.size());
                onFinishedListener.onFinished(covids);
            }

            @Override
            public void onFailure(Call<CovidListResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });*/

        Call<Map<String, District>> call = apiService.getCovidObj();
        call.enqueue(new Callback<Map<String, District>>() {
            @Override
            public void onResponse(Call<Map<String, District>> call, Response<Map<String, District>> response) {
                /*String jsonString = response.body().toString();
                String newString = jsonString.replace("=", ":");
                try {
                    JSONObject jObject = new JSONObject(newString);
                    Iterator<String> keys = jObject.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        Log.v("category key", key);
                        JSONObject innerJObject = jObject.getJSONObject(key);
                        Iterator<String> innerKeys = innerJObject.keys();
                        while (innerKeys.hasNext()) {
                            String innerKkey = keys.next();
                            String value = innerJObject.getString(innerKkey);
                            Log.v("key = " + key, "value = " + value);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                List<Covid> covidList = new ArrayList<>();
                Map<String, District> districtMap = response.body();
                for (Map.Entry<String, District> entry : districtMap.entrySet()) {
                    String stateName = entry.getKey();
                    District dist = entry.getValue();

                    Map<String, Covid> covidMap = dist.getResults();
                    int totalDist = covidMap.size();
                    int confirmedCase = 0;
                    int activeCase = 0;
                    int totalDeath = 0;
                    for (Map.Entry<String, Covid> covidEntry : covidMap.entrySet()) {
                        //String s1 = covidEntry.getKey();
                        Covid covid = covidEntry.getValue();
                        System.out.println(entry.getKey() + "=" + entry.getValue());
                        confirmedCase += covid.getConfirmed();
                        activeCase += covid.getActive();
                        totalDeath += covid.getDeceased();
                    }
                    Covid covid = new Covid(stateName, totalDist, confirmedCase, activeCase, totalDeath);
                    covidList.add(covid);

                }
                onFinishedListener.onFinished(covidList);
            }

            @Override
            public void onFailure(Call<Map<String, District>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
