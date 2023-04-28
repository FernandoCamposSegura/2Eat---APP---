package com.fcampos.toeatapp.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.domain.Comment;
import com.fcampos.toeatapp.view.CommentUpdateView;
import com.fcampos.toeatapp.view.UserLoginView;

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
        holder.rating_CommentList.setRating((float) commentList.get(position).getRating());
        holder.tv_DatePost_CommentList.setText(commentList.get(position).getDatePost());
        System.out.println(UserLoginView.USER_ID);
        System.out.println(commentList.get(position).getUser_id());
        if(commentList.get(position).getUser_id() == UserLoginView.USER_ID) {
            holder.bt_Update_CommentList.setEnabled(false);
            holder.bt_Update_CommentList.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        public TextView tv_Message_CommentList;
        public RatingBar rating_CommentList;
        public TextView tv_DatePost_CommentList;
        public Button bt_Update_CommentList;
        public View parentView;

        public CommentHolder(View itemView) {
            super(itemView);
            parentView = itemView;

            tv_Message_CommentList = itemView.findViewById(R.id.tv_Message_CommentList);
            rating_CommentList = itemView.findViewById(R.id.rating_CommentList);
            tv_DatePost_CommentList = itemView.findViewById(R.id.tv_DatePost_CommentList);
            bt_Update_CommentList = itemView.findViewById(R.id.bt_Update_CommentList);

            bt_Update_CommentList.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    float scaleFactor = 0.9f; // Escala de reducción de tamaño
                    ValueAnimator animator;
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            animator = ValueAnimator.ofFloat(1f, scaleFactor);
                            animator.setDuration(300); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    bt_Update_CommentList.setScaleX(scale);
                                    bt_Update_CommentList.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                        case MotionEvent.ACTION_UP:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(300); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    bt_Update_CommentList.setScaleX(scale);
                                    bt_Update_CommentList.setScaleY(scale);
                                }
                            });
                            animator.start();
                            updateComment(getAdapterPosition());
                        case MotionEvent.ACTION_CANCEL:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(300); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    bt_Update_CommentList.setScaleX(scale);
                                    bt_Update_CommentList.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                    }
                    return false;
                }
            });
        }

        public void updateComment(int adapterPosition) {
            Comment comment = commentList.get(adapterPosition);

            if(!tv_Message_CommentList.isEnabled()) {
                tv_Message_CommentList.setEnabled(false);
                tv_Message_CommentList.setFocusable(false);
                tv_Message_CommentList.setFocusableInTouchMode(false);
            } else {
                tv_Message_CommentList.setEnabled(true);
                tv_Message_CommentList.setFocusable(true);
                tv_Message_CommentList.setFocusableInTouchMode(true);
            }
        }
    }
}
