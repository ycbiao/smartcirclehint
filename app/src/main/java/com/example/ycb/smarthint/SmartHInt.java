package com.example.ycb.smarthint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ycb on 2015/11/9.
 */
public class SmartHInt extends TextView{
    Context context;
    private int mBackColor = 0xFFFFFFFF;

    public SmartHInt(Context context) {
        super(context);
        this.context = context;
    }

    public SmartHInt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setCustomAttributes(attrs);
    }

    public SmartHInt(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setCustomAttributes(attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int rad = widthMeasureSpec > heightMeasureSpec ? widthMeasureSpec : heightMeasureSpec; ;
        super.onMeasure(rad, rad);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int w = getWidth(),h = getHeight();

        int rad  = w > h ? w/2 : h/2;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mBackColor);
        paint.setFakeBoldText(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(rad, rad, rad, paint);

        paint.setColor(getTextColors().getDefaultColor());
        paint.setTextSize(getTextSize());
        paint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        float newY = rad + offY;
        float newX = (getWidth())/2-2;

        canvas.drawText(getText().toString(),newX, newY,paint);
    }

    public void setCustomAttributes(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SmartHInt);
        mBackColor = a.getColor(R.styleable.SmartHInt_back_color, mBackColor);

    }
}
