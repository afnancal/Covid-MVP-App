package com.afnan.covidmvp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afnan.covidmvp.R;
import com.afnan.covidmvp.covid_list.CovidListActivity;
import com.afnan.covidmvp.model.Covid;

import java.util.List;

/**
 * Created by Mohammad Afnan Haseeb on 08-Aug-2021.
 */
public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.MyViewHolder> {

    private final CovidListActivity covidListActivity;
    private final List<Covid> covidList;
    private final List<Covid> originalCovidList;

    public CovidAdapter(CovidListActivity covidListActivity, List<Covid> covidList) {
        this.covidListActivity = covidListActivity;
        this.covidList = covidList;
        this.originalCovidList = covidList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.covid_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Covid covid = covidList.get(position);

        holder.tvCovidTitle.setText("Sate name: " + covid.getState());
        holder.tv_covid_total.setText("Total District: " + covid.getTotalDist());
        holder.tv_covid_confirmed.setText("Confirmed Case: " + covid.getConfirmed());
        holder.tv_covid_active.setText("Active case: " + covid.getActive());
        holder.tv_covid_death.setText("Total Death: " + covid.getDeceased());


        holder.itemView.setOnClickListener(view -> {
            //covidListActivity.onCovidItemClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return covidList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCovidTitle;
        public TextView tv_covid_total;
        public TextView tv_covid_confirmed, tv_covid_active, tv_covid_death;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvCovidTitle = itemView.findViewById(R.id.tv_covid_title);
            tv_covid_total = itemView.findViewById(R.id.tv_covid_total);
            tv_covid_confirmed = itemView.findViewById(R.id.tv_covid_confirmed);
            tv_covid_active = itemView.findViewById(R.id.tv_covid_active);
            tv_covid_death = itemView.findViewById(R.id.tv_covid_death);
        }
    }

}
