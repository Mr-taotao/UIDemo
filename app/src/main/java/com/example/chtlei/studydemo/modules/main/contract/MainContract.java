package com.example.chtlei.studydemo.modules.main.contract;

import com.example.chtlei.studydemo.base.presenter.IPresenter;
import com.example.chtlei.studydemo.base.view.IView;

/**
 * Created by chtlei on 19-3-27.
 */

public interface MainContract {
    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {
    }
}
