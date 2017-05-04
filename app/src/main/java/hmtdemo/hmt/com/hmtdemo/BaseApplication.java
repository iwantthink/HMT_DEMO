package hmtdemo.hmt.com.hmtdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cn.com.iresearch.phonemonitor.irssdk.IRSSDK;

/**
 * Created by renbo on 2017/4/14.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        buildSDK();
        getIRSSDK().start();
    }

    private IRSSDK mIRSSDK;

    private void buildSDK() {
        mIRSSDK = new IRSSDK.Builder(getApplicationContext())
                .build();
    }

    public IRSSDK getIRSSDK() {
        if (mIRSSDK == null) {
            buildSDK();
        }
        return mIRSSDK;
    }
}
