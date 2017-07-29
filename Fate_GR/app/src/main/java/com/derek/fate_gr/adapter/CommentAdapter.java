package com.derek.fate_gr.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.derek.fate_gr.FeedAPI;
import com.derek.fate_gr.MainActivity;
import com.derek.fate_gr.R;
import com.derek.fate_gr.model.comments.CommentFeed;
import com.derek.fate_gr.model.comments.CommentFeedAPI;
import com.derek.fate_gr.model.comments.children.Comments;

import org.w3c.dom.Comment;

import java.util.ArrayList;

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
        Call<CommentFeed> call = commentfeedAPI.getFeed(ex);

        call.enqueue(new Callback<CommentFeed>() {
            @Override
            public void onResponse(Call<CommentFeed> call, Response<CommentFeed> response) {
                Log.d(TAG, "onResponse: Server Respond: " + response.toString());
                Log.d(TAG, "onResponse: Received info: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<CommentFeed> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
            }
        });
    }
}
