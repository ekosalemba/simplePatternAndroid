package com.simplepatternandroid.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.simplepatternandroid.BaseActivity;
import com.simplepatternandroid.BaseAplication;
import com.simplepatternandroid.R;
import com.simplepatternandroid.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView {
    private String TAG = HomeActivity.class.getSimpleName();
    private HomePresenter homePresenter;

    private RecyclerView.Adapter provinsiAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ActivityHomeBinding binding;
    private List<ProvinsiDetailResponse> provinsiDetailResponseList;

    @Inject
    public HomeService homeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseAplication) getApplication()).getDeps().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homePresenter = new HomePresenter(homeService, this);
        renderView();
        homePresenter.getProvinsiReactive();
    }

    private void renderView() {
        mLayoutManager = new LinearLayoutManager(this);
        binding.provinsiRecyclerView.setLayoutManager(mLayoutManager);

        provinsiDetailResponseList = new ArrayList<ProvinsiDetailResponse>();
        provinsiDetailResponseList.add(new ProvinsiDetailResponse("1", "Silakan tunggu...", "1"));
        provinsiAdapter = new ProvinsiAdapter(provinsiDetailResponseList);
        binding.provinsiRecyclerView.setAdapter(provinsiAdapter);
    }

    @Override
    public void showLog(String log) {
        Log.i(TAG, log);
    }

    @Override
    public void onSuccessGetProvinsi(ProvinsiResponse provinsiResponse) {
        provinsiDetailResponseList.clear();
        provinsiDetailResponseList.addAll(provinsiResponse.getData());
        provinsiAdapter.notifyDataSetChanged();
    }
}
