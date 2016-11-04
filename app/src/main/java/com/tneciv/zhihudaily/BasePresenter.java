package com.tneciv.zhihudaily;

/**
 * Created by Tneciv on 2016/11/4.
 */

public interface BasePresenter {
    void subscribe(String... state);

    void unSubscribe();
}
