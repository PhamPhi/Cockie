package com.techiedb.app.clockie;

import android.support.v4.app.Fragment;
public abstract class BaseFragment extends Fragment {

    public static final String TAG = Constant.APP_PREF + "BaseFragment";
    public abstract void invalidate();
    public abstract void invalidate(Object params);

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
