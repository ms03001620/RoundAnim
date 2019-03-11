package org.mazn.roundanim;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

public class RoundedBackgroundSpan extends ReplacementSpan {
    private int mPadding;
    private int widthBuff;
    private int heightBuff;

    public RoundedBackgroundSpan(int c) {
        mPadding = 2;
        widthBuff = 40;
        heightBuff = 40;
    }

    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int oldColor = paint.getColor();
        paint.setColor(oldColor);
        String[] strings = text.toString().split("/");

        if (strings != null && strings.length > 1) {

            float xxx = x + fixTextWidth(paint, text, start, end)/2 - paint.measureText("/",0,1)/2;
            canvas.drawText("/", xxx, y+10, paint);
            canvas.drawText(strings[0], xxx-paint.measureText(strings[0])/2, y-33 +10, paint);
            paint.setTextSize(paint.getTextSize()-20);
            canvas.drawText(strings[1], xxx+10, y+15+10, paint);
        }
    }

    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(MeasureText(paint, text, start, end));
    }

    private float MeasureText(Paint paint, CharSequence text, int start, int end) {
        return fixTextWidth(paint, text, start, end);
    }

    /**
     * 修正字符宽度
     *
     * @param paint
     * @param text
     * @param start
     * @param end
     * @return
     */
    protected float fixTextWidth(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end) + widthBuff;
    }

}
