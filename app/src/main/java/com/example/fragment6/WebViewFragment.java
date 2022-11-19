package com.example.fragment6;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WebViewFragment extends Fragment {
    WebView webView;
    int position;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.webview_fragment, container, false);
        webView = view.findViewById(R.id.web);
        RatingBar ratingBar=view.findViewById(R.id.ratingBar);

        Bundle bundle = getArguments();
        if (bundle != null) {
            webLoaded(bundle.getString("URL"));
            ratingBar.setRating(bundle.getFloat("RATING"));
            position=bundle.getInt("id");

        }
        //webView.loadUrl("http://developer.alexanderklimov.ru/android/theory/fragment-lifecycle.php");

        Button button=view.findViewById(R.id.back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, RecyclerFragment.newInstance(ratingBar.getRating(), position));
                transaction.addToBackStack(null);
                transaction.commit();
                ///////////////
            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    void webLoaded(String string){
        // указываем страницу загрузки
        webView.setWebViewClient(new WebViewClientK());
        // включаем поддержку JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(string);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
