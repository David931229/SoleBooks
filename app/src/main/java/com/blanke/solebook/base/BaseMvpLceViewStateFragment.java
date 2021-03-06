package com.blanke.solebook.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.avos.avoscloud.AVAnalytics;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;

/**
 * Created by Blanke on 16-1-7.
 */
public abstract class BaseMvpLceViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends MvpLceViewStateFragment<CV, M, V, P> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        AVAnalytics.onFragmentEnd(getClass().getSimpleName());
    }

    public void onResume() {
        super.onResume();
        AVAnalytics.onFragmentStart(getClass().getSimpleName());
    }
}
