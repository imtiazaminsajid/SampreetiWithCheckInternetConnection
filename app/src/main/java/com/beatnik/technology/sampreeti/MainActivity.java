package com.beatnik.technology.sampreeti;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    private WebView webView;

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

//        webView = findViewById(R.id.webView);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("https://sampreeti.com.bd/");
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);


        if (!checkInternetConnection(this)) {
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
//            builder.setIcon(R.drawable.nointernet);
//            builder.setTitle("No Internet");
//            builder.setMessage("Please Check Your Internet Connection");
//            builder.setView(R.layout.connection);
//            builder.create().show();

            getSupportFragmentManager().beginTransaction().replace(R.id.webView,
                    new NoConnection()).commit();

            //Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {

            wv = (WebView) findViewById(R.id.webView);

            //        webView = findViewById(R.id.webView);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("https://sampreeti.com.bd/");
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

            CustomWebViewClient c = new CustomWebViewClient();
            wv.setWebViewClient(c);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            wv.loadUrl("https://sampreeti.com.bd/");
        }

    }


    @Override
    public void onBackPressed() {

        if (wv != null && wv.canGoBack())
            wv.goBack();

        else {
            if (wv != null)
                wv.stopLoading();

            super.onBackPressed();

        }


    }


    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected());
    }

    // Function to load all URLs in same Webview
    private class CustomWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            if (!checkInternetConnection(MainActivity.this)) {

                Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();

            } else {

                view.loadUrl(url);


            }
            return true;
        }

    }
}
