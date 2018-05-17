package com.mohit.program.custom.button;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Author @ Mohit Soni on 16-05-2018 02:09 PM.
 */

public class CustomDrawable extends Drawable {
    Paint mPaint;
    int startColor, endColor, mBorderWidth, mBorderRadius;
    RectF mRect;
    Path mPath;

    public CustomDrawable(int startColor, int endColor, int borderWidth, int borderRadius) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);

        mRect = new RectF();
        this.startColor = startColor;
        this.endColor = endColor;

        mBorderWidth = borderWidth;
        mBorderRadius = borderRadius;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mPath.reset();

        // out rect
        mRect.set(bounds.left + mBorderWidth, bounds.top + mBorderWidth, bounds.right - mBorderWidth, bounds.bottom - mBorderWidth);
        mPath.addRoundRect(mRect, mBorderRadius, mBorderRadius, Path.Direction.CW);

        // inner rect
        mRect.set(bounds.left + 5, bounds.top + 5, bounds.right - 5, bounds.bottom - 5);
        mPath.addRoundRect(mRect, mBorderRadius, mBorderRadius, Path.Direction.CW);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        // stock
        mPaint.setShader(new LinearGradient(0, 0, 0, 100, startColor, endColor, Shader.TileMode.MIRROR));
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
