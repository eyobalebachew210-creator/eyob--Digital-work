package com.eyobdigitalwork.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    private static final String HOME =
            "https://eyobalebachew210-creator.github.io/eyob--Digital-work/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view,
                    WebResourceRequest request) {

                return handleExternalLink(
                        request.getUrl().toString()
                );
            }

            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view,
                    String url) {

                return handleExternalLink(url);
            }
        });

        webView.loadUrl(HOME);
    }

    private boolean handleExternalLink(String url) {

        if (url.startsWith("tel:")
                || url.startsWith("mailto:")
                || url.startsWith("sms:")
                || url.startsWith("whatsapp:")) {

            try {
                Intent intent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                startActivity(intent);

            } catch (Exception ignored) {
            }

            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {

        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
