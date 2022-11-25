package com.bats.admin;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Mapa extends AppCompatActivity {

    private WebView webView;
    private static final String url = "https://www.google.com.br/maps/place/R.+Arlindo+Veiga+dos+Santos+-+Campo+Grande,+S%C3%A3o+Paulo+-+SP,+04671-300/@-23.6606842,-46.6923369,17z/data=!3m1!4b1!4m5!3m4!1s0x94ce50402ebbf84f:0xa5d75dec15d9823f!8m2!3d-23.6606891!4d-46.6901429";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        initComponents();
        webViewSettings();
    }

    private void initComponents() {
        webView = findViewById(R.id.web_view);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void webViewSettings() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new CallBack());
        webView.loadUrl(url);
    }

    public static class CallBack extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}