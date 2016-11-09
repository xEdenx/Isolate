package com.tneciv.zhihudaily.module.main;

import com.tneciv.zhihudaily.base.BasePresenter;
import com.tneciv.zhihudaily.base.BaseView;

/**
 * Created by Tneciv
 * on 2016-11-09 20:46 .
 */

public interface HomeContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
