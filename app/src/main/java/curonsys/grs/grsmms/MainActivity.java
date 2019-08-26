package curonsys.grs.grsmms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long pressedTime = 0L;

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //com.grs.mobile.MainActivity.
        mWebView = (WebView) findViewById(R.id.mWebView);

        //웹뷰에서 자바스크립트 실행가능
        mWebView.getSettings().setJavaScriptEnabled(true);

        //연결할 url 지정
        mWebView.loadUrl("http://106.251.64.55:8081/main/Login.do");
        // WebViewClient 지정
        mWebView.setWebViewClient(new WebViewClientClass());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    //뒤로 가기 2번 누르면 종료
    public void onBackPressed() {
        /*if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            super.onBackPressed();
        }*/
        if (pressedTime == 0L) {
            Toast.makeText(this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        } else if ((int)(System.currentTimeMillis() - pressedTime) > 2000) {
            Toast.makeText(this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            pressedTime = 0L;
        } else {
            super.onBackPressed();
            finish();
        }
    }
}
