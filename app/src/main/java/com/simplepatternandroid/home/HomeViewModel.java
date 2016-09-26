package com.simplepatternandroid.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.simplepatternandroid.BR;

public class HomeViewModel extends BaseObservable {
    private boolean isOnLoading;
    public HomeActivity view;

    public HomeViewModel(HomeActivity view) {
        this.view = view;
    }

    public void setOnLoading(boolean isOnLoading) {
        this.isOnLoading = isOnLoading;
        notifyPropertyChanged(BR.onLoading);
    }

    @Bindable
    public boolean isOnLoading() {
        return isOnLoading;
    }

    public OnRefreshListener getRefreshListener() {
        return new OnRefreshListener() {
            @Override
            public void onRefresh() {
                view.getDataProvinsi();
            }
        };
    }
}
