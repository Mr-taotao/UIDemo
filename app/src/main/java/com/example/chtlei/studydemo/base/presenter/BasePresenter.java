package com.example.chtlei.studydemo.base.presenter;

import com.example.chtlei.studydemo.base.view.IView;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends IView> implements IPresenter<T> {
}
