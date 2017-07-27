package com.derek.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.derek.jsontest.Model.Feed;
import com.derek.jsontest.Model.children.Children;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String BASE_URL = "https://www.reddit.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetData = (Button) findViewById(R.id.btnGetData);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        redditAPI redditAPI = retrofit.create(com.derek.jsontest.redditAPI.class);
        Call<Feed> call = redditAPI.getData();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Server Respond: " + response.toString());
                Log.d(TAG, "onResponse: Received info: " + response.body().toString());

                ArrayList<Children> childrenList = response.body().getData().getChildren();
                for(int i = 0;i < childrenList.size();i++){
                    Log.d(TAG, "onResponse: \n" +
                        "kind: " + childrenList.get(i).getKind() + "\n" +
                        "subreddit: " + childrenList.get(i).getData().getSubreddit() + "\n" +
                        "author: " + childrenList.get(i).getData().getAuthor() + "\n" +
                        "flair: " + childrenList.get(i).getData().getFlair() + "\n" +
                        "-------------------------------------------------------------------------\n\n");
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
