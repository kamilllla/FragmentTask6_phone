package com.example.fragment6;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.util.ServiceLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

public class Article  {

    private String url;
    private String header;
    private float rating;


    public Article(String url, float rating) {
        this.url = url;
        setHeader();
        this.rating=rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader() {
        SomeThread thread=new SomeThread();
        thread.start();

    }

    class SomeThread extends Thread{
        public void run(){
            try {
                org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
                Element h1 = doc.selectFirst("h1");
                header=h1.text();
                Log.i("H1: ",h1.text());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
