package com.example.fragment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        RecyclerFragment firstFragment=new RecyclerFragment();
        //WebViewFragment firstFragment=new WebViewFragment();
        fragmentTransaction.add(R.id.container, firstFragment);
        fragmentTransaction.commit();

    }
    /*
    @Override
    public void sendUrl(String data) {

            WebViewFragment webViewFragment=new WebViewFragment();
            webViewFragment.webLoaded(data);
            transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, webViewFragment);
            transaction.addToBackStack(null);
            transaction.commit();

    }

     */
}