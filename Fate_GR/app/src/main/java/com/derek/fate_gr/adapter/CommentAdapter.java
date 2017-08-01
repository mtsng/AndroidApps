package com.derek.fate_gr.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.derek.fate_gr.R;
import com.derek.fate_gr.adapter.CommentsListAdapter;
import com.derek.fate_gr.model.comments.CommentFeed;
import com.derek.fate_gr.model.comments.CommentFeedAPI;
import com.derek.fate_gr.model.comments.children.Comment;
import com.derek.fate_gr.model.comments.children.CommentChildren;

import java.util.ArrayList;
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

    private ArrayList<Comment> comments;

    private static String permaLink;
    private Context mContext;
    private ListView mListView;
    private ProgressBar mProgressBar;
    private TextView mProgressText;
    private Intent mIntent;

    public CommentAdapter(Context context, ListView listview,
                          ProgressBar progressBar, TextView progresstext, Intent intent){
        mContext = context;
        mListView = listview;
        mProgressBar = progressBar;
        mProgressText = progresstext;
        mIntent = intent;
    }

    public void init(){
        permaLink = mIntent.getStringExtra("@string/post_permalink");
        comments = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentFeedAPI commentfeedAPI = retrofit.create(com.derek.fate_gr.model.comments.CommentFeedAPI.class);
        Call<List<CommentFeed>> call = commentfeedAPI.getFeed(permaLink.substring(1));
        System.out.println(permaLink.substring(1));
        call.enqueue(new Callback<List<CommentFeed>>() {
            @Override
            public void onResponse(Call<List<CommentFeed>> call, Response<List<CommentFeed>> response) {
                Log.d(TAG, "onResponse: Server Respond: " + response.toString());
                Log.d(TAG, "onResponse: Received info: " + response.body().toString());

                ArrayList<CommentChildren> children = response.body().get(1).getData().getChildren();

                for(int i = 0;i < children.size();i++){
                    comments.add(children.get(i).getComments());
                }

                CommentsListAdapter cadapter = new CommentsListAdapter(mContext, R.layout.comments_layout, comments);
                mListView.setAdapter(cadapter);

                mProgressBar.setVisibility(View.GONE);
                mProgressText.setText("");
            }

            @Override
            public void onFailure(Call<List<CommentFeed>> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
            }
        });
    }
}
