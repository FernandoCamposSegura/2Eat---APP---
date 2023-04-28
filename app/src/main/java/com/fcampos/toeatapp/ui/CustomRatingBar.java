package com.fcampos.toeatapp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RatingBar;

@SuppressLint("AppCompatCustomView")
public class CustomRatingBar extends RatingBar {
    public CustomRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRatingBar(Context context) {
        super(context);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setNumStars(5); // Establecer el número de estrellas a 5
        setMinimumWidth(24);
    }
}
