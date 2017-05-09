package hmtdemo.hmt.com.hmtdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

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
    }


}
