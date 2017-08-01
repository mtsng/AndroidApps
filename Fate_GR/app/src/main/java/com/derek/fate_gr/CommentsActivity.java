package com.derek.fate_gr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.derek.fate_gr.model.comments.children.Comment;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Michael on 7/31/2017.
 */

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";
    private int defaultImage;

    private static String postTitle;
    private static String postAuthor;
    private static String postThumbnailURL;
    private static String postSelftext;
    private static String postFlair;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Log.d(TAG, "Oncreate: Started");

        setupImageLoader();

        initPost();
    }

    private void initPost(){
        Intent incomingIntent = getIntent();
        postTitle = incomingIntent.getStringExtra("@string/post_title");
        postAuthor = incomingIntent.getStringExtra("@string/post_author");
        postThumbnailURL = incomingIntent.getStringExtra("@string/post_thumbnail");
        postSelftext = incomingIntent.getStringExtra("@string/post_selftext");
        postFlair = incomingIntent.getStringExtra("@string/post_flair");

        TextView title = (TextView) findViewById(R.id.postTitle);
        TextView author = (TextView) findViewById(R.id.postAuthor);
        ImageView thumbnail = (ImageView) findViewById(R.id.postThumbnail);
        //TODO add selftext and flair
        Button btnReply = (Button) findViewById(R.id.btnPostReply);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.postLoadingProgressBar);

        title.setText(postTitle);
        author.setText(postAuthor);
        displayImage(postThumbnailURL, thumbnail, progressBar);
    }

    private void displayImage(String imgUrl, ImageView imgView, final ProgressBar progressBar){
        //create the imageloader object
        ImageLoader imageLoader = ImageLoader.getInstance();

        //create display options
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();
        //System.out.println(imgUrl + "----\n\n");
        //download and display image from url
        imageLoader.displayImage(imgUrl, imgView, options , new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
            }

        });
    }

    //This method is only called once
    /**
     * Required for setting up the Universal Image loader Library
     */
    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                CommentsActivity.this)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP

        defaultImage = CommentsActivity.this.getResources().getIdentifier("@drawable/image_failed",null,CommentsActivity.this.getPackageName());
    }
}
