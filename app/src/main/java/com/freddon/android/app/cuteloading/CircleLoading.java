package com.freddon.android.app.cuteloading;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by fred on 2016/9/9.
 */
public class CircleLoading extends View {

    /**
     * the foreground progress
     */
    private float progress = 0;


    /**
     * the foreground progress stroke line-width
     */
    private float lineWidth = 3f;

    /**
     * the foreground progress stroke line-color
     */
    private int color = Color.BLACK;

    /**
     * the background progress stroke line-color
     */
    private int backgroundColor = Color.GRAY;

    /**
     * default start angle
     */
    private final static int START_ANGLE = -90;

    private RectF rectF;
    private Paint backgroundPaint;
    private Paint foregroundPaint;


    private ObjectAnimator objectAnimator;
    private float progressRadius;

    public CircleLoading(Context context) {
        this(context, null);
    }

    public CircleLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rectF = new RectF();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleLoading, 0, 0);
        //Reading values from the XML layout
        try {
            // Value
            progress = typedArray.getFloat(R.styleable.CircleLoading_cl_progress, progress);
            progressRadius = typedArray.getDimension(R.styleable.CircleLoading_cl_ball_radius, progressRadius);
            lineWidth = typedArray.getDimension(R.styleable.CircleLoading_cl_line_width, lineWidth);
            // Color
            color = typedArray.getInt(R.styleable.CircleLoading_cl_ball_color, color);
            backgroundColor = typedArray.getInt(R.styleable.CircleLoading_cl_line_color, backgroundColor);
        } finally {
            typedArray.recycle();
        }

        // Init Background
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(lineWidth);

        // Init Foreground
        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(color);
        foregroundPaint.setStyle(Paint.Style.FILL);
        foregroundPaint.setStrokeWidth(0);
        setProgress(progress);
    }
    //endregion

    //region Draw Method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(rectF, backgroundPaint);
        float angle = 360 * progress / 100;
        //画环上的小球
        //圆心x,y ===>  (x-a)²+（y-b)²=R²
        //x=a+rcost y=b+rsint
        final float radius = Math.abs(rectF.left - rectF.right) / 2F;
        float centX = rectF.centerX() + (float) (radius * Math.cos(Math.toRadians(angle + START_ANGLE)));
        float centY = rectF.centerY() + (float) (radius * Math.sin(Math.toRadians(angle + START_ANGLE)));
        canvas.drawCircle(centX, centY, progressRadius, foregroundPaint);
    }
    //endregion

    //region Mesure Method
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        final float offset = progressRadius > lineWidth ? progressRadius : lineWidth;
        rectF.set(0 + offset, 0 + offset, min - offset, min - offset);
    }
    //endregion

    //region Method Get/Set
    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = (progress <= 100) ? progress : 100;
        invalidate();
    }


    private void removeAnimation() {
        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator = null;
        }
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        backgroundPaint.setStrokeWidth(lineWidth);
        requestLayout();
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setColor(int color) {
        this.color = color;
        foregroundPaint.setColor(color);
        invalidate();
        requestLayout();
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        backgroundPaint.setColor(backgroundColor);
        invalidate();
        requestLayout();
    }
    //endregion

    //region Other Method


    /**
     * Set the loading animation
     *
     * @param duration
     */
    public ObjectAnimator start(int duration) {
        removeAnimation();
        objectAnimator = ObjectAnimator.ofFloat(this, "progress", 360F);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setRepeatCount(300);
        return objectAnimator;
    }
    //endregion

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAnimation();
    }
}
