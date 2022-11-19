package com.example.fragment6;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment {
    ArrayList<Article> articles = new ArrayList<>();
    ArticleAdapter.OnItemClickListener clickListener;
    float rating;
    int position;

    public static RecyclerFragment newInstance(float data, int position){
        RecyclerFragment recyclerFragment=new RecyclerFragment();
        Bundle bundle=new Bundle();
        bundle.putFloat("rating", data);
        bundle.putInt("position", position);
        recyclerFragment.setArguments(bundle);
        return  recyclerFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.recycler_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        Bundle bundle=getArguments();
        if (bundle!=null) {
            articles.get(position).setRating(rating);
        }

        clickListener=new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article article, int position) {
                WebViewFragment webViewFragment=new WebViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("URL", article.getUrl());
                bundle.putFloat("RATING", article.getRating());
                bundle.putInt("id", position);
                webViewFragment.setArguments(bundle);
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, webViewFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        };


        ArticleAdapter adapter = new ArticleAdapter(articles, clickListener);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle!=null){
            position=bundle.getInt("position");
            rating=bundle.getFloat("rating");
            Log.i("ONCREATE", "ON CREATE WORK");
        }
        articles.add(new Article("https://en.wikipedia.org/wiki/2022_Welsh_Open_(snooker)", 5));
        articles.add(new Article(" https://javarush.ru/groups/posts/2767-parsing-html-bibliotekoy-jsoup-",3));
        articles.add(new Article("https://ux-journal.ru/kak-sozdat-prototip-mobilnogo-prilozheniya-v-5-shagov.html",2));
        articles.add(new Article("https://incrussia.ru/understand/polnyj-slovar-blokchejna/",1));
        articles.add(new Article("https://theblueprint.ru/beauty/wellness/pros-and-cons-of-sugar",0));


    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}