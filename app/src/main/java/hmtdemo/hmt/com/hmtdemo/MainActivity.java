package hmtdemo.hmt.com.hmtdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hmt.analytics.HMTAgent;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.common.NetworkUitlity;
import com.hmt.analytics.util.HParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_bind_muid)
    Button mBtnBindMuid;
    @BindView(R.id.btn_client_data)
    Button mBtnClientData;
    @BindView(R.id.btn_http_client)
    Button mBtnHttpClient;
    @BindView(R.id.btn_auto_error)
    Button mBtnAutoError;
    @BindView(R.id.btn_manual_error)
    Button mBtnManualError;
    @BindView(R.id.btn_change2webview)
    Button mBtnChange2webview;
    @BindView(R.id.btn_change_activity)
    Button mBtnChangeActivity;
    @BindView(R.id.btn_send_act)
    Button mBtnSendAct;
    @BindView(R.id.btn_send_okhttp)
    Button mBtnSendOkhttp;
    @BindView(R.id.btn_send_url)
    Button mBtnSendUrl;
    @BindView(R.id.btn_change_mode)
    Button mBtnChangeMode;
    private Context mContext;
//    private Button mBtnClientData;
//    private Button mBtnBindMuid;
//    private Button mBtnHttpClient;
//    private Button mBtnAutoError;
//    private Button mBtnManualError;
//    private Button mBtnChange2Webview;
//    private Button mBtnChangeActivity;
//    private Button mBtnSendAct;
//    private Button mBtnSendOkhttp;
//    private Button mBtnSendUrl;
//    private Button mBtnChangeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.bind(this);
        mContext = this;
        initHMT();
        init();
        initListener();

    }

    private void initHMT() {
        String[] strArr = new String[2];
        strArr[0] = "androidid";
        strArr[1] = "androidid1";
        HMTAgent.Initialize(mContext, 1, strArr);
        HMTAgent.onError(mContext); //监控页面错误信息
    }

    private void initListener() {
        mBtnChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMode();
            }
        });
        mBtnBindMuid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                HMTAgent.bindMuid(mContext, "sanmu");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                    }
                }).start();
            }
        });

        mBtnClientData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HMTAgent.postClientData(mContext);
            }
        });

        mBtnHttpClient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        NetworkUitlity.get("https://www.baidu.com");
                    }
                }.start();
            }
        });

        mBtnAutoError.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                int i = 1 / 0;
            }
        });

        mBtnManualError.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    int i = 1 / 0;
                } catch (Exception e) {
                    String[] a = new String[4];
                    a[0] = "0";
                    a[1] = "1";
                    a[2] = "2";
                    a[3] = "你好";
                    HParams property = new HParams();
                    property.setParams("addIntegral", a);
                    property.setParams("name", "error1");
                    property.setParams("name", "error2");
                    HMTAgent.onError(mContext, e.getMessage(), property);
                }
            }
        });

        mBtnChange2webview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jumpView();
            }
        });

        mBtnChangeActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jump2Seconed();
            }
        });

        mBtnSendAct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HParams property = new HParams();
                property.setParams("title", "百度新闻");
                property.setParams("desc", "第一条新闻");
                property.setParams("name", "error");
                HMTAgent.onAction(mContext, "自定义事件", property);
            }
        });

        mBtnSendOkhttp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        //sendOkHttp("http://www.baidu.com");
                    }
                }.start();
            }
        });

        mBtnSendUrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String str = getURLResponse("http://www.baidu.com");
                        System.out.print(str);
                    }
                }.start();
            }
        });
    }

    private void init() {
//        mBtnSendOkhttp = (Button) findViewById(R.id.btn_send_okhttp);
//        mBtnSendUrl = (Button) findViewById(R.id.btn_send_url);
//        mBtnSendAct = (Button) findViewById(R.id.btn_send_act);
//        mBtnChange2webview = (Button) findViewById(R.id.btn_change2webview);
//        mBtnChangeActivity = (Button) findViewById(R.id.btn_change_activity);
//        mBtnManualError = (Button) findViewById(R.id.btn_manual_error);
//        mBtnAutoError = (Button) findViewById(R.id.btn_auto_error);
//        mBtnChangeMode = (Button) findViewById(R.id.btn_change_mode);
//        mBtnBindMuid = (Button) findViewById(R.id.btn_bind_muid);
//        mBtnClientData = (Button) findViewById(R.id.btn_client_data);
//        mBtnHttpClient = (Button) findViewById(R.id.btn_http_client);

    }

    /*private void sendOkHttp(String url){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                String htmlStr =  response.body().string();
                int code = response.code();
            }
        });
    }*/
    private String getURLResponse(String urlString) {
        HttpURLConnection conn = null;
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine = "";
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return resultData;
    }

    public void jump2Seconed() {
        Intent intent = new Intent(this, SeconedActivity.class);
        startActivity(intent);
        finish();
    }

    public void jumpView() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void changeMode() {
        int mode = CommonUtil.getReportPolicyMode(mContext);
        Log.d("MainActivity", "mode:" + mode);
        if (mode == 0) {
            CommonUtil.setReportPolicy(mContext, 1, "client");
            CommonUtil.setReportPolicy(mContext, 1, "server");
            Toast.makeText(mContext, "当前发送模式为 实时发送", Toast.LENGTH_SHORT).show();
        } else {
            CommonUtil.setReportPolicy(mContext, 0, "client");
            CommonUtil.setReportPolicy(mContext, 0, "server");
            Toast.makeText(mContext, "当前发送模式为 启动时发送", Toast.LENGTH_SHORT).show();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
