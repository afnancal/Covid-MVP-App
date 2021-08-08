package com.afnan.covidmvp.covid_list;

import com.afnan.covidmvp.model.Covid;

import java.util.List;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public class CovidListPresenter implements CovidListContract.Presenter, CovidListContract.Model.OnFinishedListener {

    private CovidListContract.View covidListView;

    private CovidListContract.Model covidListModel;

    public CovidListPresenter(CovidListContract.View covidListView) {
        this.covidListView = covidListView;
        covidListModel = new CovidListModel();
    }

    @Override
    public void onDestroy() {
        this.covidListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {

        if (covidListView != null) {
            covidListView.showProgress();
        }
        covidListModel.getCovidList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {

        if (covidListView != null) {
            covidListView.showProgress();
        }
        covidListModel.getCovidList(this, 1);
    }

    @Override
    public void onFinished(List<Covid> covidArrayList) {
        covidListView.setDataToRecyclerView(covidArrayList);
        if (covidListView != null) {
            covidListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        covidListView.onResponseFailure(t);
        if (covidListView != null) {
            covidListView.hideProgress();
        }
    }

}
