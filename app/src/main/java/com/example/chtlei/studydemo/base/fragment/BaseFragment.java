package com.example.chtlei.studydemo.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;
import com.example.chtlei.studydemo.R;
import com.example.chtlei.studydemo.base.presenter.IPresenter;
import com.example.chtlei.studydemo.base.view.IView;
import com.example.chtlei.studydemo.utils.ToastUtils;
import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends AbstractSimpleFragment implements IView {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
