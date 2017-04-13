package hmtdemo.hmt.com.hmtdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hmt.analytics.HMTAgent;
/*import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Activity_B extends Activity {

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

        bindMuid =(Button)findViewById(R.id.bindMuid);
        bindMuid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                HMTAgent.bindMuid(context,"sanmu");
            }
        });

        clientdDta = (Button)findViewById(R.id.clientdata);
        clientdDta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                HMTAgent.postClientData(context);
            }
        });

        tag = (Button)findViewById(R.id.tag);
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

        error1 = (Button)findViewById(R.id.error1);
        error1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int i = 1/0;
            }
        });

        error2 = (Button)findViewById(R.id.error2);
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

        action = (Button)findViewById(R.id.action);
        action.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                jump_view();
            }
        });

        activity = (Button)findViewById(R.id.activity);
        activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                jump_b();
            }
        });

        reportModel1 = (Button)findViewById(R.id.reportModel1);
        reportModel1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                HMTAgent.onAction(context, "自定义事件");
            }
        });

        reportModel0 = (Button)findViewById(R.id.reportModel0);
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

        baseUrl = (Button)findViewById(R.id.baseUrl);
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
