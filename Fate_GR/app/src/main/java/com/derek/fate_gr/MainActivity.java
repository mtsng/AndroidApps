package com.derek.fate_gr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.derek.fate_gr.adapter.CommentAdapter;
import com.derek.fate_gr.adapter.PostAdapter;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://reddit.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);

        PostAdapter pa = new PostAdapter(MainActivity.this, listView);
        pa.init();

    }
}
