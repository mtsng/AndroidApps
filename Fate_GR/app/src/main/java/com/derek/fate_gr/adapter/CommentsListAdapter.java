package com.derek.fate_gr.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.derek.fate_gr.R;
import com.derek.fate_gr.model.comments.children.Comment;

import java.util.ArrayList;

/**
 * Created by Michael on 7/31/2017.
 */

public class CommentsListAdapter extends ArrayAdapter<Comment>{

    private static final String TAG = "CommentsListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView comment;
        TextView author;
        ProgressBar mProgressBar;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CommentsListAdapter(Context context, int resource, ArrayList<Comment> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        //get the persons information
        String comment = getItem(position).getBody();
        String author = getItem(position).getAuthor();


        try{


            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            final CommentsListAdapter.ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new CommentsListAdapter.ViewHolder();
                holder.comment = (TextView) convertView.findViewById(R.id.comment);
                holder.author = (TextView) convertView.findViewById(R.id.commentAuthor);
                holder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.commentProgressBar);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (CommentsListAdapter.ViewHolder) convertView.getTag();
                result = convertView;
                holder.mProgressBar.setVisibility(View.VISIBLE);
            }

            lastPosition = position;

            holder.comment.setText(comment);
            holder.author.setText(author);
            holder.mProgressBar.setVisibility(View.GONE);

            return convertView;
        }catch (IllegalArgumentException e){
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage() );
            return convertView;
        }

    }

}
