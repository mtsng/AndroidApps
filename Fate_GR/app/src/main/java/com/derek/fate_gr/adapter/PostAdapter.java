package com.derek.fate_gr.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.derek.fate_gr.FeedAPI;
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

/**
 * Created by Michael on 7/29/2017.
 */

public class PostAdapter {

    private final String BASE_URL = "https://reddit.com/";

    private Context mContext;
    private ListView mListView;

    public PostAdapter(Context context, ListView listView){
        mContext = context;
        mListView = listView;
    }

    public void init(){
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
                PostParser jManager = new PostParser(childrenList);
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
                CustomListAdapter customListAdapter = new CustomListAdapter(mContext, R.layout.card_layout_main, cData);
                mListView.setAdapter(customListAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
