package hmtdemo.hmt.com.hmtdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class ViewActivity extends Activity {

 private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //this.web = (DialerBaseWebView)findViewById(R.id.webView);
        this.web = (WebView)findViewById(R.id.webView);
        this.web.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view,String url){
                super.onLoadResource(view,url);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){

                super.onPageStarted(view,url,favicon);
            }
        });
          WebSettings settings = this.web.getSettings();
         settings.setJavaScriptEnabled(true);
        this.web.loadUrl("http://www.baidu.com");
    }
}
