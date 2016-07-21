package com.example.simeiling.viewpage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by simeiling on 2016/7/20.
 */
public class ProgressBar extends View {

    int mFirstColor;
    int mSecondColor;
    int mCircleWidth;
    Paint mPaint ;
    private RectF mRectF;

    public ProgressBar(Context context) {
        super(context);
    }
    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }
    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorProgressBar);
        mFirstColor = ta.getColor(R.styleable.ColorProgressBar_firstColor, Color.RED);
        mSecondColor = ta.getColor(R.styleable.ColorProgressBar_secondColor, Color.BLUE);
        mCircleWidth = ta.getDimensionPixelSize(R.styleable.ColorProgressBar_circleWidth, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
        ta.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);

        mChanged = false;
    }

    int mWidth;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = Math.min(widthSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 160, getResources().getDisplayMetrics()));
            setMeasuredDimension(mWidth, mWidth);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        mRectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        if (!mChanged) {
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mFirstColor);
            canvas.drawArc(mRectF, -90, mProgress, false, mPaint);
        } else {

            mPaint.setColor(mFirstColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(mRectF, -90, mProgress, false, mPaint);
        }
        startProgress();
    }
    int mProgress = 0;
    boolean mChanged;
    private void startProgress() {
        if (isShown()) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("ly", "调用post");
                    mProgress += 10;
                    if (mProgress >= 360) {
                        mProgress = 0;
                        mChanged = !mChanged;
                    }
                    invalidate();
                }
            }, 10);
        }
    }
}
