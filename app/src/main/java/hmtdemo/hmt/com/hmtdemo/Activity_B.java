package hmtdemo.hmt.com.hmtdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hmt.analytics.HMTAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;*/

public class Activity_B extends AppCompatActivity {

    private Context context;
    private Button clientdDta;
    private Button bindMuid;
    private Button tag;
    private Button error1;
    private Button error2;
    private Button action;
    private Button activity;
    private Button reportModel1;
    private Button reportModel0;
    private Button baseUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        context = this;
        HMTAgent.onError(context); //监控页面错误信息

        bindMuid =(Button)findViewById(R.id.btn_bind_muid);
        bindMuid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                HMTAgent.bindMuid(context,"sanmu");
            }
        });

        clientdDta = (Button)findViewById(R.id.btn_client_data);
        clientdDta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                HMTAgent.postClientData(context);
            }
        });

        tag = (Button)findViewById(R.id.btn_http_client);
        tag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(){
                    @Override
                    public void run()
                    {
                     //NetworkUitlity.get("http://www.baidu.com");
                    }
                }.start();
            }
        });

        error1 = (Button)findViewById(R.id.btn_auto_error);
        error1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int i = 1/0;
            }
        });

        error2 = (Button)findViewById(R.id.btn_manual_error);
        error2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try{
                    int i = 1/0;
                }catch(Exception e){
                    HMTAgent.onError(context,e.getMessage());
                }

            }
        });

        action = (Button)findViewById(R.id.btn_change2webview);
        action.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                jump_view();
            }
        });

        activity = (Button)findViewById(R.id.btn_change_activity);
        activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                jump_b();
            }
        });

        reportModel1 = (Button)findViewById(R.id.btn_send_act);
        reportModel1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                HMTAgent.onAction(context, "自定义事件");
            }
        });

        reportModel0 = (Button)findViewById(R.id.btn_send_okhttp);
        reportModel0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(){
                    @Override
                    public void run()
                    {
                       // sendOkHttp("http://www.baidu.com");
                    }
                }.start();
            }
        });

        baseUrl = (Button)findViewById(R.id.btn_send_url);
        baseUrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(){
                    @Override
                    public void run()
                    {
                        String str = getURLResponse("http://www.baidu.com");
                        System.out.print(str);
                    }
                }.start();
            }
        });
    }

    public void jump_b(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void jump_view(){
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
        //finish();
    }

   /* private void sendOkHttp(String url){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                String htmlStr = response.body().string();
                int code = response.code();
                Log.e("OkHttp==>htmlStr:", htmlStr);
                Log.e("OkHttp==>code:", code + "");
            }
        });
    }*/

    private String getURLResponse(String urlString){
        HttpURLConnection conn = null;
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine  = "";
            while((inputLine = bufferReader.readLine()) != null){
                resultData += inputLine + "\n";
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(conn != null){
                conn.disconnect();
            }
        }

        return resultData;
    }
}
