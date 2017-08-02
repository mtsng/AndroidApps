package com.derek.fate_gr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.derek.fate_gr.adapter.NextPostAdapter;
import com.derek.fate_gr.adapter.PostAdapter;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://reddit.com/";

    ListView listView;

    public static String after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        loadFirstPage();

        Scroll_Listener();

    }

    private void loadFirstPage(){
        Log.d("MainActivity", "loadNextPage: " + 0);
        PostAdapter pa = new PostAdapter(MainActivity.this, listView);
        pa.init();
    }

    private void loadNextPage(){
        Log.d("MainActivity", "loadNextPage: " + 1 + " " + after);
        NextPostAdapter npa = new NextPostAdapter(this, listView);
        npa.init();
    }

    private void Scroll_Listener(){
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout below;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted(){
                if(totalItem - currentFirstVisibleItem == currentVisibleItemCount &&
                        this.currentScrollState == SCROLL_STATE_IDLE){
                    System.out.println("Hello ---------------\n\n");
                    loadNextPage();
                }
            }
        });
    }

    private void setAfter(String after){
        this.after = after;
    };
}
