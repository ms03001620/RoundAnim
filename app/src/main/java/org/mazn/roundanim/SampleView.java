package org.mazn.roundanim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2015/8/4.
 */
public class SampleView extends LetterSpacingTextView {
    private static final String TAG = "SampleView";
    private Paint[] mPaints;
    private Paint mFramePaint;
    private boolean[] mUseCenters;
    private RectF[] mOvals;
    private RectF mBigOval;
    private float mStart = 270;
    private float mSweep;
    private int mBigIndex;

    private static final float SWEEP_INC = 2;
    private static final float START_INC = 0;

    private int mTotal;
    private int mCurrent;
    private int mAngle;

    private int mRingForeground;
    private int mRingBackground;

    public SampleView(Context context) {
        this(context, null);
    }

    public SampleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        mRingForeground = getCurrentTextColor();
        mRingBackground = 0x88888888;

        mPaints = new Paint[4];
        mUseCenters = new boolean[4];
        mOvals = new RectF[4];

        mPaints[0] = new Paint();
        mPaints[0].setAntiAlias(true);
        mPaints[0].setStyle(Paint.Style.FILL);
        mPaints[0].setColor(0x88FF0000);
        mUseCenters[0] = false;

        mPaints[1] = new Paint(mPaints[0]);
        mPaints[1].setColor(0x8800FF00);
        mUseCenters[1] = true;

        mPaints[2] = new Paint(mPaints[0]);
        mPaints[2].setStyle(Paint.Style.STROKE);
        mPaints[2].setStrokeWidth(5);
        mPaints[2].setColor(mRingBackground);
        mUseCenters[2] = false;

        mPaints[3] = new Paint(mPaints[2]);
        mPaints[3].setColor(0x88888888);
        mUseCenters[3] = true;

        mBigOval = new RectF(0, 0, 280, 250);

        mOvals[0] = new RectF(10, 270, 70, 330);
        mOvals[1] = new RectF(90, 270, 150, 330);
        mOvals[2] = new RectF(170, 270, 230, 330);
        mOvals[3] = new RectF(250, 270, 310, 330);

        mFramePaint = new Paint();
        mFramePaint.setAntiAlias(true);
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFramePaint.setStrokeWidth(0);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "re", 0).show();
                mSweep = 0;
                mStart = 270;
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArcs(canvas, mBigOval, mUseCenters[2], mPaints[2]);
        super.onDraw(canvas);

        if (mSweep <= mAngle) {
            setText(String.valueOf(animCurrent(mSweep)) + "/" + mTotal);
            invalidate();
        }
        mSweep += SWEEP_INC;
    }

    private int animCurrent(float sweep){
        double unit =  360D / mTotal;
        int result = (int)(sweep/unit);
        return result;
    }

    private void drawArcs(Canvas canvas, RectF oval, boolean useCenter, Paint paint) {
        int oldColor = paint.getColor();
        canvas.drawOval(oval, paint);
        paint.setColor(mRingForeground);
        canvas.drawArc(oval, mStart, mSweep, useCenter, paint);
        paint.setColor(oldColor);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "changed:" + changed + ", left:" + left + ", top:" + top + ", right:" + right + ", bottom:" + bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure w:" + MeasureSpec.toString(widthMeasureSpec) + ", h:" + MeasureSpec.toString(heightMeasureSpec));
        int width = measureWidth(widthMeasureSpec);
        int height = measureWidth(heightMeasureSpec);
        setMeasuredDimension(width, height);

        mBigOval = new RectF(10, 10, width-10, height-10);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 50;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    public void setTotal(int total) {
        mTotal = total;
        mAngle = SampleView.getAngle(mCurrent, mTotal);
    }

    public void setCurrent(int current) {
        mCurrent = current;
        mAngle = SampleView.getAngle(mCurrent, mTotal);
        invalidate();
    }


    public static double getPercent(double current ,double total){
        return current / total;
    }

    public static int getAngle(double current, double total){
        double percent = getPercent(current, total);
        return (int)(360 * percent);
    }
}
