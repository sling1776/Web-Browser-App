package com.sling.webbrowser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.sling.webbrowser.History;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

public class SearchBar extends LinearLayout {
    SearchBar(Context context, final WebView webView, final History history){
        super(context);

        //Parameters:
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                100, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;



        //create forward and backward buttons
        AppCompatButton backwardButton = new AppCompatButton(context);
            backwardButton.setText("<-");
            backwardButton.setLayoutParams(buttonParams);
            backwardButton.setBackgroundColor(Color.parseColor("#7ff0fc"));
        AppCompatButton forwardButton = new AppCompatButton(context);
            forwardButton.setText("->");
            forwardButton.setLayoutParams(buttonParams);
            forwardButton.setBackgroundResource(R.drawable.button_background);

        // create search bar
        final AppCompatEditText searchBar = new AppCompatEditText(context);
            searchBar.setTextColor(Color.WHITE);
            searchBar.setLayoutParams(params);

        // create go button
        AppCompatButton goButton = new AppCompatButton(context);
            goButton.setText("Go");
            //goButton.setBackgroundColor((Color.parseColor("#7ff0fc")));




        ///////////////////Button Logic///////////////////////////
        //backward Button
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


        //forward Button
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


        //Go Button
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url;
                try{
                    url = searchBar.getText().toString();}
                catch(NullPointerException e){
                    System.out.println("Handled Exception-- Null String");
                    url = "";
                }
                if (!url.equals("")) {
                    if (!url.startsWith("http")) {
                        url = "https://" + url;
                        searchBar.setText(url);
                    }
                    webView.loadUrl(url);
                    String curURL = history.getName();
                    if (!curURL.equals(url)) {
                        history.addHistory(url);
                    }
                }
            }
        });


        // create layout
        addView(backwardButton);
        addView(forwardButton);
        addView(searchBar);
        addView(goButton);
        setBackgroundColor(Color.parseColor("#00574B"));
    }


}
