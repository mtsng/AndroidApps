package com.derek.fate_gr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.derek.fate_gr.adapter.CommentAdapter;
import com.derek.fate_gr.adapter.PostClickedAdapter;

/**
 * Created by Michael on 7/31/2017.
 */

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Log.d(TAG, "Oncreate: Started");

        TextView title = (TextView) findViewById(R.id.postTitle);
        TextView author = (TextView) findViewById(R.id.postAuthor);
        TextView selftext = (TextView) findViewById(R.id.postContent);
        TextView flair = (TextView) findViewById(R.id.postFlair);
        ImageView thumbnail = (ImageView) findViewById(R.id.postThumbnail);
        ListView commentList = (ListView) findViewById(R.id.commentListView);
        ProgressBar commentProgressBar = (ProgressBar) findViewById(R.id.commentsLoadingProgressBar);
        TextView progressText = (TextView) findViewById(R.id.progressText);
        Button btnReply = (Button) findViewById(R.id.btnPostReply);
        ProgressBar postProgressBar = (ProgressBar) findViewById(R.id.postLoadingProgressBar);

        commentProgressBar.setVisibility(View.VISIBLE);

        Intent incomingIntent = getIntent();

        PostClickedAdapter pca = new PostClickedAdapter(this, title, author, selftext,
                flair, thumbnail, incomingIntent, postProgressBar, commentList, btnReply);

        CommentAdapter ca = new CommentAdapter(this, commentList, commentProgressBar, progressText, incomingIntent);

        pca.initPost();

        ca.init();
    }
}
