package com.derek.fate_gr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Michael on 8/3/2017.
 */

public class WebViewActivity extends AppCompatActivity{

    private static final String TAG = "WebViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webview = (WebView) findViewById(R.id.webview);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.webviewLoadingProgressBar);
        Log.d(TAG, "onCreate: Started");
        final TextView loadingText = (TextView) findViewById(R.id.webProgressText);

        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                progressBar.setVisibility(View.GONE);
                loadingText.setText("");
            }
        });
    }
}
