package com.example.dash.Activ_C;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dash.R;

public class StatsActivity extends AppCompatActivity {

    private WebView webLook;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_stats);
            webLook = findViewById(R.id.web_view);
            WebSettings webSettings = webLook.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webLook.setWebViewClient(new MyClientWebView());
            webLook.loadUrl("https://node-borky-fkppa.run-eu-central1.goorm.io/bieganie");
        }


        //our custom webviewclient
        private class MyClientWebView extends WebViewClient {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                final AlertDialog.Builder run = new AlertDialog.Builder(StatsActivity.this);
                //
                //
                // builder.setMessage(R.string.notification_error_ssl_cert_invalid);
                run.setPositiveButton("next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                run.setNegativeButton("abort", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = run.create();
                dialog.show();
            }
        }

    }




/*


        textViewResult = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://node-borky-fkppa.run-eu-central1.goorm.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                //   .addConverterFactory(ScalarsConverterFactory.create())
                //   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        JsonAtlasCloudUsers jsonAtlasCloudUsers = retrofit.create(JsonAtlasCloudUsers.class);

        Call<List<PostUser>> call = jsonAtlasCloudUsers.getPosts();

        call.enqueue(new Callback<List<PostUser>>() {
            @Override
            public void onResponse(Call<List<PostUser>> call, Response<List<PostUser>> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<PostUser> posts = response.body();
                for (PostUser post : posts) {
                    String content = "";
                    content += "isAdmin: " + post.getIsAdmin() + "\n";
                    content += "ID " + post.get_id() + "\n";
                    content += "username " + post.getUsername() + "\n";
                    content += "name " + post.getName() + "\n";
                    content += "surname " + post.getSurname() + "\n";
                    content += "email " + post.getEmail() + "\n";
                    content += "avatar " + post.getAvatar() + "\n";
                    content += "Date " + post.getCreatedAt() + "\n\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<PostUser>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });



    }
}
*/
