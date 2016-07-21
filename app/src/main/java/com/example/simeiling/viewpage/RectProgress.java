package com.example.simeiling.viewpage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by simeiling on 2016/7/21.
 */
public class RectProgress extends View {
    public RectProgress(Context context) {
        super(context);
    }

    public RectProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMod = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMod = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMod==MeasureSpec.AT_MOST||widthMod==MeasureSpec.UNSPECIFIED){
             width = Math.min(widthSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 160, getResources().getDisplayMetrics()));
        }else
            width = widthMeasureSpec;
        if(heightMod==MeasureSpec.AT_MOST||heightMod==MeasureSpec.UNSPECIFIED){
             height = Math.min(heightSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 160, getResources().getDisplayMetrics()));
        }else
            height = heightMeasureSpec;
        setMeasuredDimension(width,height);

    }

    int width;
    int height;
    int progress;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF();
        updateProgress();
    }

    void updateProgress(){
        postDelayed(new Runnable() {
            @Override
            public void run() {
                progress += 10;
                if (progress >= 360) {
                    progress = 0;
                }
                invalidate();
            }
        }, 100);
    }
}
