package com.derek.fate_gr.adapter;

import android.content.Context;
import android.util.Log;

import com.derek.fate_gr.model.comments.CommentArray;
import com.derek.fate_gr.model.comments.CommentFeedAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Michael on 7/29/2017.
 */

public class CommentAdapter {

    private final String BASE_URL = "https://reddit.com/";

    private final String ex = "comments/6qabc6/assembled_a_catalyst_alter_for_my_wonderful/";

    private Context mContext;
    private int mResource;

    public void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentFeedAPI commentfeedAPI = retrofit.create(com.derek.fate_gr.model.comments.CommentFeedAPI.class);
        Call<List<CommentArray>> call = commentfeedAPI.getFeed(ex);

        call.enqueue(new Callback<List<CommentArray>>() {
            @Override
            public void onResponse(Call<List<CommentArray>> call, Response<List<CommentArray>> response) {
                Log.d(TAG, "onResponse: Server Respond: " + response.toString());
                Log.d(TAG, "onResponse: Received info: " + response.body().toString());
                System.out.println(response.body().get(1));
            }

            @Override
            public void onFailure(Call<List<CommentArray>> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
            }
        });
    }
}
