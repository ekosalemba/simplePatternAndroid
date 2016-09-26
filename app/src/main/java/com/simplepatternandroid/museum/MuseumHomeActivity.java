package com.simplepatternandroid.museum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.simplepatternandroid.BaseActivity;
import com.simplepatternandroid.BaseAplication;
import com.simplepatternandroid.R;
import com.simplepatternandroid.home.ProvinsiDetailResponse;
import com.simplepatternandroid.network.NetworkError;
import com.simplepatternandroid.util.IntentKey;

import javax.inject.Inject;

public class MuseumHomeActivity extends BaseActivity implements MuseumHomeView {
    private ProvinsiDetailResponse provinsiDetailResponse;
    private MuseumPresenter museumPresenter;

    @Inject
    MuseumService museumService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseAplication) getApplication()).getDeps().inject(this);
        museumPresenter = new MuseumPresenter(museumService, this);
        setContentView(R.layout.activity_museum_home);
        getReceiveIntent();
    }

    public void getReceiveIntent() {
        Intent intent = getIntent();
        provinsiDetailResponse = intent.getParcelableExtra(IntentKey.province);
        setTitle(provinsiDetailResponse.nama);
        museumPresenter.getMuseumByProvince(provinsiDetailResponse);
    }

    @Override
    public void onSuccessGetMuseumByProvince(MuseumResponse callback) {
        Log.i("Total", callback.data.size() + "");
    }

    @Override
    public void onErrorGetMuseumByProvince(NetworkError networkError) {

    }
}
