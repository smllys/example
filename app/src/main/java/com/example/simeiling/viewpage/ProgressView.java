package com.example.simeiling.viewpage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by simeiling on 2016/7/21.
 */
public class ProgressView extends View {
    public ProgressView(Context context) {
        super(context);
    }
int mProgress;
    Paint paint;
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ColorProgressBar);
         paint = new Paint();
         paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(typedArray.getColor(R.styleable.ColorProgressBar_firstColor, Color.RED));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST||widthMode==MeasureSpec.UNSPECIFIED) {
            mWidth = Math.min(widthSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 160, getResources().getDisplayMetrics()));
            setMeasuredDimension(mWidth, mWidth);
        }
        stroek = mWidth/10;
        paint.setStrokeWidth(stroek);
    }
    int stroek ;
    int mWidth;
    int[] color= new int[]{0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00,
            0xFFFFFF00, 0xFFFF0000  };
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Shader shader = new SweepGradient(mWidth/2 -stroek,mWidth/2 -stroek,color,null);
        int center = mWidth/2;
        int rad = center-stroek/2;
        paint.setShader(null);
        RectF rectF = new RectF(center-rad,center-rad,center+rad,center+rad);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2-stroek,paint);
        paint.setShader(shader);
        paint.setStyle(Paint.Style.STROKE);
        Paint cPaint = new Paint();
        canvas.drawArc(rectF,-90, mProgress,false,paint);
//        cPaint.setColor(Color.BLACK);
        startProgress();

    }

     void draRectProgress(Canvas canvas){
         int height = mWidth/10;
         int cx = mWidth/2;
         int cy = height/2;
//         Rect rectF = new Rect(){cx};
     }
    private void startProgress() {
        if (isShown()) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("ly", "调用post");
                    mProgress += 10;
                    if (mProgress >= 360) {
                        mProgress = 0;
                    }
                    invalidate();
                }
            }, 100);
        }
    }
}
