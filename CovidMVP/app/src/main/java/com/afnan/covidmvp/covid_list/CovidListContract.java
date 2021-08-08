package com.afnan.covidmvp.covid_list;

import com.afnan.covidmvp.model.Covid;

import java.util.List;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public class CovidListContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Covid> covidArrayList);

            void onFailure(Throwable t);
        }

        void getCovidList(OnFinishedListener onFinishedListener, int pageNo);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Covid> covidArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

    }

}
