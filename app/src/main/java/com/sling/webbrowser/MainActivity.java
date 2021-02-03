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

        LinearLayout searchBarLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                100, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        //create Web view
        final WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());

        final History history = new History();
        // create search bar
        final AppCompatEditText searchBar = new AppCompatEditText(this);
        searchBar.setTextColor(Color.WHITE);
        searchBar.setLayoutParams(params);


        //create forward and backward buttons
        AppCompatButton backwardButton = new AppCompatButton(this);
            backwardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: display previous history
                    history.traverseBackward();
                    String url = history.getName();
                    if(!url.equals("")){
                        webView.loadUrl(url);
                        searchBar.setText(url);
                    }
                }
            });
            backwardButton.setText("<-");
            backwardButton.setLayoutParams(buttonParams);

        AppCompatButton forwardButton = new AppCompatButton(this);
            forwardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: display forward history
                    history.traverseForward();
                    String url = history.getName();
                    if(!url.equals("")){
                        webView.loadUrl(url);
                        searchBar.setText(url);
                    }
                }
            });
            forwardButton.setText("->");
            forwardButton.setLayoutParams(buttonParams);


        // create go button
        AppCompatButton goButton = new AppCompatButton(this);
        goButton.setText("Go");
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = searchBar.getText().toString();
                if(!url.equals("")) {
                    if (!url.startsWith("http")) {
                        url = "https://" + url;
                        searchBar.setText(url);
                    }
                    webView.loadUrl(url);
                    String curURL = history.getName();
                    if(!curURL.equals(url)) {
                        history.addHistory(url);
                    }
                }
            }
        });

        searchBarLayout.addView(backwardButton);
        searchBarLayout.addView(forwardButton);
        searchBarLayout.addView(searchBar);
        searchBarLayout.addView(goButton);
        searchBarLayout.setBackgroundColor(Color.parseColor("#00574B"));


        // create webview



        layout.addView(searchBarLayout);
        layout.addView(webView);

        setContentView(layout);
    }
}
