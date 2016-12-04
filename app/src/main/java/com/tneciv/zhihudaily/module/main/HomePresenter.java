package com.tneciv.zhihudaily.module.main;

import android.util.Log;

import com.tneciv.zhihudaily.entity.HotEntity;
import com.tneciv.zhihudaily.entity.NewsEntity;
import com.tneciv.zhihudaily.retrofit.ApiServiceFactory;
import com.tneciv.zhihudaily.retrofit.ZhihuService;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Tneciv
 * on 2016-11-09 20:48 .
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe(Object... state) {
        ZhihuService service = ApiServiceFactory.getInstance().create(ZhihuService.class);
        Flowable<NewsEntity> detail = service.getDetail(8959706);

        Log.d("HomePresenter", "wth");

        mCompositeDisposable.add(detail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<NewsEntity>() {
                    @Override
                    public void onNext(NewsEntity newsEntity) {
                        Log.d("HomePresenter", newsEntity.getTitle());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

        Flowable<HotEntity> list = service.getHotList();
        mCompositeDisposable.add(list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<HotEntity>() {
                    @Override
                    public void onNext(HotEntity hotEntity) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }
}
