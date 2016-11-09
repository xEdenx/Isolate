package com.tneciv.zhihudaily.module.main;

/**
 * Created by Tneciv
 * on 2016-11-09 20:48 .
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe(String... state) {

    }

    @Override
    public void unSubscribe() {

    }
}
