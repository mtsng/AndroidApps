package com.derek.fate_gr.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.derek.fate_gr.CommentsActivity;
import com.derek.fate_gr.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.w3c.dom.Text;

/**
 * Created by Michael on 7/31/2017.
 */

public class PostClickedAdapter {

    private int defaultImage;

    private static String postTitle;
    private static String postAuthor;
    private static String postImageURL;
    private static String postSelftext;
    private static String postFlair;

    private Context mContext;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mSelftext;
    private TextView mFlair;
    private ImageView mImage;
    private Intent mIntent;
    private ListView mCommentView;
    private ProgressBar mProgressBar;

    public PostClickedAdapter(Context context, TextView title,
                              TextView author, TextView selftext,
                              TextView flair, ImageView image,
                              Intent intent, ProgressBar progressbar, ListView listview){
        mContext = context;
        mTitle = title;
        mAuthor = author;
        mSelftext = selftext;
        mFlair = flair;
        mImage = image;
        mIntent = intent;
        mCommentView = listview;
        mProgressBar = progressbar;
        setupImageLoader();
    }

    public void initPost(){
        postTitle = mIntent.getStringExtra("@string/post_title");
        postAuthor = mIntent.getStringExtra("@string/post_author");
        postImageURL = mIntent.getStringExtra("@string/post_image");
        postSelftext = mIntent.getStringExtra("@string/post_selftext");
        postFlair = mIntent.getStringExtra("@string/post_flair");

        mTitle.setText(postTitle);
        mAuthor.setText(postAuthor);
        mFlair.setText(postFlair);
        mSelftext.setText(postSelftext);
        displayImage(postImageURL, mImage, mProgressBar);
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
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP

        defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed",null,mContext.getPackageName());
    }

}
