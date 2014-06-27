package com.techiedb.app.clockie;

import android.content.Intent;
import android.os.Message;

public abstract class BaseController {
    public static final String TAG = Constant.APP_PREF + "BaseController";
    protected BaseFragment mBaseFragment;
    protected ClokieApp mApp;

    public abstract void handleMessage(Message msg);
    public class BaseFragmentHandler extends BaseHandler{
        @Override
        public void callBack(Message msg) {
            handleMessage(msg);
        }
    }

    public void sendMessage(int what, Object obj){
        Message msg = new Message();
        msg.what = what;
        msg.obj = obj;
        handleMessage(msg);
    }
    // startup the controller, and using its method for handling the first-time of running
    public void onCreate(){

    }
    // terminate the controller after calling this methods.
    public void onDestroy(){

    }

}
