package com.derek.paginationtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManger;

    RecyclerView rv;
    ProgressBar progressBar;

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        adapter = new PaginationAdapter(this);

        linearLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManger);

        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManger) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount(){
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage(){
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                    loadingFirstPage();
            }
        }, 1000);
    }

    private void loadingFirstPage(){
        Log.d(TAG, "loadFirstPage: ");
        ArrayList<Movie> movies = Movie.createMovies(adapter.getItemCount());
        progressBar.setVisibility(View.GONE);
        adapter.addAll(movies);

        if(currentPage <= TOTAL_PAGES){
           adapter.addLoadingFooter();
        }
        else{
            isLastPage = true;
        }
    }

    private void loadNextPage(){
        Log.d(TAG, "loadNextPage: " + currentPage);
        ArrayList<Movie> movies = Movie.createMovies(adapter.getItemCount());

        adapter.removeLoadingFooter();
        isLoading = false;

        adapter.addAll(movies);

        if(currentPage != TOTAL_PAGES){
            adapter.addLoadingFooter();
        }
        else{
            isLastPage = true;
        }
    }
}
