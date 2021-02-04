package com.sling.webbrowser;
import com.sling.webbrowser.History;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //create Web view
        final WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());

        final History history = new History();


        SearchBar searchBarLayout = new SearchBar(this, webView, history);



        layout.addView(searchBarLayout);
        layout.addView(webView);

        setContentView(layout);
    }
}
