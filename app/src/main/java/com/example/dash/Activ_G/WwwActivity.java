package com.example.dash.Activ_G;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dash.R;

public class WwwActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_www);

        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://node-borky-fkppa.run-eu-central1.goorm.io/shop/admin/sites");

    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder constructor = new AlertDialog.Builder(WwwActivity.this);
        constructor.setMessage("Exit Weather Checker Panel?");
        constructor.setCancelable(true);
        constructor.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        constructor.setPositiveButton("Yes, close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog message = constructor.create();
        message.show();
    }
}
