package com.simplepatternandroid.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import com.simplepatternandroid.BaseActivity;
import com.simplepatternandroid.BaseAplication;
import com.simplepatternandroid.R;
import com.simplepatternandroid.databinding.ActivityHomeBinding;
import com.simplepatternandroid.museum.MuseumHomeActivity;
import com.simplepatternandroid.network.NetworkError;
import com.simplepatternandroid.util.IntentKey;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView, ProvinsiAdapter.OnItemClickListener {
    private String TAG = HomeActivity.class.getSimpleName();
    private HomePresenter homePresenter;

    private RecyclerView.Adapter provinsiAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ActivityHomeBinding binding;
    private HomeViewModel homeViewModel;
    private List<ProvinsiDetailResponse> provinsiDetailResponseList;

    @Inject
    public HomeService homeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseAplication) getApplication()).getDeps().inject(this);
        setTitle("Dolan Museum");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeViewModel = new HomeViewModel(this);
        binding.setViewModel(homeViewModel);
        homePresenter = new HomePresenter(homeService, this);
        binding.setPresenter(homePresenter);
        renderView();
        getDataProvinsi();
        setDisplayShowHome(false);
    }

    private void renderView() {
        mLayoutManager = new LinearLayoutManager(this);
        binding.provinsiRecyclerView.setLayoutManager(mLayoutManager);

        provinsiDetailResponseList = new ArrayList<ProvinsiDetailResponse>();
        provinsiAdapter = new ProvinsiAdapter(provinsiDetailResponseList, this);
        binding.provinsiRecyclerView.setAdapter(provinsiAdapter);
    }

    @Override
    public void showLog(String log) {
        Log.i(TAG, log);
    }

    @Override
    public void setLoading(boolean status) {
        homeViewModel.setOnLoading(status);
    }

    public void getDataProvinsi() {
        setLoading(true);
        homePresenter.getProvinsiReactive();
    }

    @Override
    public void onSuccessGetProvinsi(ProvinsiResponse provinsiResponse) {
        provinsiDetailResponseList.clear();
        provinsiDetailResponseList.addAll(provinsiResponse.getData());
        provinsiAdapter.notifyDataSetChanged();
        setLoading(false);
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onErrorGetProvinsi(NetworkError networkError) {
        provinsiDetailResponseList.clear();
        provinsiDetailResponseList.add(new ProvinsiDetailResponse("", networkError.getMessage(), ""));
        provinsiAdapter.notifyDataSetChanged();
        setLoading(false);
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(int position, ProvinsiDetailResponse provinsiDetailResponse) {
        Intent intent = new Intent(this, MuseumHomeActivity.class);
        intent.putExtra(IntentKey.province, provinsiDetailResponse);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
