package com.tneciv.zhihudaily.module.detail;

import com.tneciv.zhihudaily.BasePresenter;
import com.tneciv.zhihudaily.BaseView;

/**
 * Created by Tneciv on 2016/11/4.
 */

public interface DetailContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
