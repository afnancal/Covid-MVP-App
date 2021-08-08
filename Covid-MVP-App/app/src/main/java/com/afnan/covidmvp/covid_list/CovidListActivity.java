package com.afnan.covidmvp.covid_list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afnan.covidmvp.R;
import com.afnan.covidmvp.adapter.CovidAdapter;
import com.afnan.covidmvp.model.Covid;
import com.afnan.covidmvp.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.afnan.covidmvp.utils.GridSpacingItemDecoration.dpToPx;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public class CovidListActivity extends AppCompatActivity implements CovidListContract.View, ShowEmptyView {

    private static final String TAG = "CovidListActivity";
    private CovidListPresenter covidListPresenter;
    private RecyclerView rvCovidList;
    private List<Covid> covidList;
    private CovidAdapter covidAdapter;
    private ProgressBar pbLoading;
    private TextView tvEmptyView;
    private GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_list);

        getSupportActionBar().setTitle(getString(R.string.app_name));
        initUI();

        //Initializing presenter
        covidListPresenter = new CovidListPresenter(this);

        covidListPresenter.requestDataFromServer();
    }

    /**
     * This method will initialize the UI components
     */
    private void initUI() {

        rvCovidList = findViewById(R.id.rv_covid_list);

        covidList = new ArrayList<>();
        covidAdapter = new CovidAdapter(this, covidList);

        mLayoutManager = new GridLayoutManager(this, 1);
        rvCovidList.setLayoutManager(mLayoutManager);
        rvCovidList.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(this, 10), true));
        rvCovidList.setItemAnimator(new DefaultItemAnimator());
        rvCovidList.setAdapter(covidAdapter);

        pbLoading = findViewById(R.id.pb_loading);
        tvEmptyView = findViewById(R.id.tv_empty_view);
    }


    @Override
    public void showProgress() {

        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Covid> covidArrayList) {

        covidList.addAll(covidArrayList);
        covidAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResponseFailure(Throwable throwable) {

        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        covidListPresenter.onDestroy();
    }

    @Override
    public void showEmptyView() {

        rvCovidList.setVisibility(View.GONE);
        tvEmptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyView() {
        rvCovidList.setVisibility(View.VISIBLE);
        tvEmptyView.setVisibility(View.GONE);
    }

}
