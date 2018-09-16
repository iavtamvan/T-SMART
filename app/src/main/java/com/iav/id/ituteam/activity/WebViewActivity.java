package com.iav.id.ituteam.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;

public class WebViewActivity extends AppCompatActivity {

    private WebView activityMainWebview;
    private String url;
    private TextView tvWebviewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().hide();
        initView();

        url = getIntent().getStringExtra(Config.BUNDLE_URL_NEWS);
        // Enable Javascript
        WebSettings webSettings = activityMainWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        activityMainWebview.loadUrl(url);

        tvWebviewUrl.setText(url);
        tvWebviewUrl.setFocusable(true);
        tvWebviewUrl.setEnabled(false);

    }

    private void initView() {
        activityMainWebview = findViewById(R.id.activity_main_webview);
        tvWebviewUrl = findViewById(R.id.edt_webview_url);
    }
}
