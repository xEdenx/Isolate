package com.tneciv.zhihudaily.base;

/**
 * Created by Tneciv
 * on 2016-11-09 20:44 .
 */

public interface BasePresenter {
    void subscribe(String... state);

    void unSubscribe();
}
