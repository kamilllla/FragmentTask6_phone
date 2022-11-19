package com.example.fragment6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    //определяем интерфейс слушателя события нажатия

    interface OnItemClickListener{
        void onItemClick(Article countries, int position);
    }


    //поля
    private List<Article> articlesList;
    public  OnItemClickListener onItemClickListener;//объект интерфейса
    // конструктор
    public ArticleAdapter(List<Article> articles,  OnItemClickListener onClickListener) {
        articlesList = articles;
        this.onItemClickListener=onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView articleText;
        public RatingBar ratingBar;
        //конструктор
        public ViewHolder(View itemView) {
            super(itemView);
            articleText=(TextView) itemView.findViewById(R.id.text);
            ratingBar=(RatingBar) itemView.findViewById(R.id.ratingRecycler);

        }
    }
    // создание новой ячейки
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //создание из содержимого layout View-элемента
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recycler_view, parent, false);
        // передаем объекту класса ViewHolder View -ячейку
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // приязываем данные к ячейки
    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        // получаем объект
        Article article = articlesList.get(position);

        // устанавливаем данные View на основе макета(содержащемся в holder)
        TextView articleText = holder.articleText;
       articleText.setText(article.getHeader());
       RatingBar rBar=holder.ratingBar;
       rBar.setRating(article.getRating());
       holder.articleText.setOnClickListener(new View.OnClickListener() {//метод через который подключаем слушатель нажатия
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(article, holder.getAdapterPosition());

            }

        });
       holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar mRatingBar, float rating,
                                        boolean fromUser) {
                article.setRating(rating);
                rBar.setRating(article.getRating());

            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return articlesList.size();
    }

}
