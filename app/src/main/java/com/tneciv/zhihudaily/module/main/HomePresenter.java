package com.tneciv.zhihudaily.module.main;

import android.util.Log;

import com.tneciv.zhihudaily.entity.HotEntity;
import com.tneciv.zhihudaily.entity.NewsEntity;
import com.tneciv.zhihudaily.retrofit.ApiServiceFactory;
import com.tneciv.zhihudaily.retrofit.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        ZhihuService service = ApiServiceFactory.getInstance().create(ZhihuService.class);
        Observable<NewsEntity> detail = service.getDetail(8959706);
        detail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsEntity value) {
                        Log.d("HomePresenter", value.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable<HotEntity> list = service.getHotList();
        list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hotEntity -> {
                    Log.d("HomePresenter", "hotEntity.getRecent().size():" + hotEntity.getRecent().size());
                });
    }

    @Override
    public void unSubscribe() {

    }
}
