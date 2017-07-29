package com.derek.fate_gr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.derek.fate_gr.adapter.CustomListAdapter;
import com.derek.fate_gr.adapter.JSONManager;
import com.derek.fate_gr.model.Feed;
import com.derek.fate_gr.model.children.ChildData;
import com.derek.fate_gr.model.children.Children;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://reddit.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeedAPI feedAPI = retrofit.create(com.derek.fate_gr.FeedAPI.class);
        Call<Feed> call = feedAPI.getFeed();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Server Respond: " + response.toString());
                Log.d(TAG, "onResponse: Received info: " + response.body().toString());

                ArrayList<Children> childrenList = response.body().getData().getChildren();
                JSONManager jManager = new JSONManager(childrenList);
                ArrayList<ChildData> cData = jManager.getChildData();
/*                for(int i = 0;i < childrenList.size();i++){
                    Log.d(TAG, "onResponse: \n" +
                            "title: " + childrenList.get(i).getData().getTitle() + "\n" +
                            "author: " + childrenList.get(i).getData().getAuthor() + "\n" +
                            "url: " + childrenList.get(i).getData().getUrl() + "\n" +
                            "flair: " + childrenList.get(i).getData().getFlair_text() + "\n" +
                            "thumbnail: " + childrenList.get(i).getData().getThumbnail() + "\n" +
                            "-------------------------------------------------------------------------\n\n");
                } */
                ListView listView = (ListView) findViewById(R.id.listView);
                CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, R.layout.card_layout_main, cData);
                listView.setAdapter(customListAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
