package com.derek.fate_gr.adapter;

import android.util.Log;
import android.widget.Toast;

import com.derek.fate_gr.FeedAPI;
import com.derek.fate_gr.model.Feed;
import com.derek.fate_gr.model.children.ChildData;
import com.derek.fate_gr.model.children.Children;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Michael on 7/28/2017.
 */

public class JSONManager {

    private final String BASE_URL = "https://reddit.com/";

    private ArrayList<ChildData> child_data;

    public JSONManager(ArrayList<Children> childList){
        child_data = new ArrayList<ChildData>();
        parseChildData(childList);
    }

    private void parseChildData(ArrayList<Children> childList){
        for(Children cd: childList){
            child_data.add(cd.getData());
        }
    }

    public ArrayList<ChildData> getChildData(){
        return child_data;
    }
}
