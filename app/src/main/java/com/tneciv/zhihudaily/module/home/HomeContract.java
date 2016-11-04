package com.tneciv.zhihudaily.module.home;

import com.tneciv.zhihudaily.BasePresenter;
import com.tneciv.zhihudaily.BaseView;

/**
 * Created by Tneciv on 2016/11/4.
 */

public interface HomeContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
