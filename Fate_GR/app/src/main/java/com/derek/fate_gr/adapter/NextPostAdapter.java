package com.derek.fate_gr.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.derek.fate_gr.CommentsActivity;
import com.derek.fate_gr.FeedAPI;
import com.derek.fate_gr.MainActivity;
import com.derek.fate_gr.PaginationFeedAPI;
import com.derek.fate_gr.R;
import com.derek.fate_gr.model.Feed;
import com.derek.fate_gr.model.children.ChildData;
import com.derek.fate_gr.model.children.Children;
import com.derek.fate_gr.parser.PostParser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.derek.fate_gr.MainActivity.postList;

/**
 * Created by Michael on 8/2/2017.
 */

public class NextPostAdapter {
    private final String BASE_URL = "https://reddit.com/";

    private Context mContext;
    private ListView mListView;

    private ArrayList<Children> childrenList;
    private PostParser parsedPosts;
    //ArrayList<ChildData> postList;

    public NextPostAdapter(Context context, ListView listView){
        mContext = context;
        mListView = listView;
    }

    public void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PaginationFeedAPI pFeedAPI = retrofit.create(com.derek.fate_gr.PaginationFeedAPI.class);
        Call<Feed> call = pFeedAPI.getPaginationFeed(25, MainActivity.after);

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Server Respond: " + response.toString());
                Log.d(TAG, "onResponse: Received info: " + response.body().toString());

                MainActivity.after = response.body().getData().getAfter_id();
                childrenList = response.body().getData().getChildren();
                parsedPosts = new PostParser(childrenList);
                addAll(parsedPosts.getChildData());


                savePosition();


                CustomListAdapter customListAdapter = new CustomListAdapter(mContext, R.layout.card_layout_main, postList);
                mListView.setAdapter(customListAdapter);

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Log.d(TAG, "onItemClick: Clicked: " + postList.get(position));
                        Intent intent = new Intent(mContext, CommentsActivity.class);
                        intent.putExtra("@string/post_title", postList.get(position).getTitle());
                        intent.putExtra("@string/post_selftext", postList.get(position).getSelftext());
                        intent.putExtra("@string/post_flair", postList.get(position).getFlair_text());
                        intent.putExtra("@string/post_author", postList.get(position).getAuthor());
                        intent.putExtra("@string/post_thumbnail", postList.get(position).getThumbnail());
                        intent.putExtra("@string/post_permalink", postList.get(position).getPermalink());
                        mContext.startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addAll(ArrayList<ChildData> cdList){
        for(ChildData cd: cdList){
            postList.add(cd);
        }
    }

    private void savePosition(){
        final int positionToSave = mListView.getFirstVisiblePosition();
        mListView.post(new Runnable() {
            @Override
            public void run() {
                mListView.setSelection(positionToSave);
            }
        });

        mListView.getViewTreeObserver().addOnPreDrawListener((new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if(mListView.getFirstVisiblePosition() == positionToSave){
                    mListView.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
                return false;
            }
        }));
    }
}
