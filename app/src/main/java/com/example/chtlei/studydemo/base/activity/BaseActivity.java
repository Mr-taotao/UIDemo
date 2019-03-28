package com.example.chtlei.studydemo.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.chtlei.studydemo.base.presenter.IPresenter;
import com.example.chtlei.studydemo.base.view.IView;
import com.example.chtlei.studydemo.utils.CommonUtils;

/**
 * Created by chtlei on 19-3-27.
 */

public abstract class BaseActivity<T extends IPresenter> extends AbstractSimpleActivity implements IView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressedSupport() {
        CommonUtils.hideKeyBoard(this, this.getWindow().getDecorView().getRootView());
        super.onBackPressedSupport();
    }
}
