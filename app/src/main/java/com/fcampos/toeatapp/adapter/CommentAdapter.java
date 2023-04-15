package com.fcampos.toeatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.domain.Establishment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> dataList) {
        this.context = context;
        this.commentList = dataList;
    }

    @Override
    public CommentAdapter.CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_comment_list_item, parent, false);
        return new CommentAdapter.CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.CommentHolder holder, int position) {
        holder.tv_Message_CommentList.setText(commentList.get(position).getMessage());
        holder.tv_Rating_CommentList.setText(String.valueOf(commentList.get(position).getRating()));
        holder.tv_DatePost_CommentList.setText(commentList.get(position).getDatePost());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        public TextView tv_Message_CommentList;
        public TextView tv_Rating_CommentList;
        public TextView tv_DatePost_CommentList;
        public View parentView;

        public CommentHolder(View itemView) {
            super(itemView);
            parentView = itemView;

            tv_Message_CommentList = itemView.findViewById(R.id.tv_Message_CommentList);
            tv_Rating_CommentList = itemView.findViewById(R.id.tv_Rating_CommentList);
            tv_DatePost_CommentList = itemView.findViewById(R.id.tv_DatePost_CommentList);
        }
    }
}
