package com.derek.fate_gr.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.derek.fate_gr.CommentsActivity;
import com.derek.fate_gr.R;
import com.derek.fate_gr.WebViewActivity;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

/**
 * Created by Michael on 7/31/2017.
 */

public class PostClickedAdapter {

    private int defaultImage;

    private static String postTitle;
    private static String postAuthor;
    private static String postThumbnailURL;
    private static String postSelftext;
    private static String postFlair;

    private Context mContext;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mSelftext;
    private TextView mFlair;
    private ImageView mThumbnail;
    private Intent mIntent;
    private ListView mCommentView;
    private Button btnReply;
    private ProgressBar mProgressBar;
    private String permalink;
    private String BASE_URL = "https://www.reddit.com";

    public PostClickedAdapter(Context context, TextView title,
                              TextView author, TextView selftext,
                              TextView flair, ImageView thumbnail,
                              Intent intent, ProgressBar progressbar, ListView listview, Button btn){
        mContext = context;
        mTitle = title;
        mAuthor = author;
        mSelftext = selftext;
        mFlair = flair;
        mThumbnail = thumbnail;
        mIntent = intent;
        mCommentView = listview;
        mProgressBar = progressbar;
        btnReply = btn;
        setupImageLoader();
    }

    public void initPost(){
        postTitle = mIntent.getStringExtra("@string/post_title");
        postAuthor = mIntent.getStringExtra("@string/post_author");
        postThumbnailURL = mIntent.getStringExtra("@string/post_thumbnail");
        postSelftext = mIntent.getStringExtra("@string/post_selftext");
        postFlair = mIntent.getStringExtra("@string/post_flair");
        permalink = mIntent.getStringExtra("@string/post_permalink");

        mTitle.setText(postTitle);
        mAuthor.setText(postAuthor);
        mFlair.setText(postFlair);
        mSelftext.setText(postSelftext);
        displayImage(postThumbnailURL, mThumbnail, mProgressBar);

        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: reply");
                getUserComment();
            }
        });

        mThumbnail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "onClick: Opening URL in webview: ");
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", BASE_URL + permalink);
                mContext.startActivity(intent);
            }
        });


    }

    private void getUserComment(){
        final Dialog dialog = new Dialog(mContext);
        dialog.setTitle("dialog");
        dialog.setContentView(R.layout.comment_input_dialog);

        int width = (int)(mContext.getResources().getDisplayMetrics().widthPixels*.95);
        int height = (int)(mContext.getResources().getDisplayMetrics().widthPixels*.95);

        dialog.getWindow().setLayout(width, height);
        dialog.show();
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
