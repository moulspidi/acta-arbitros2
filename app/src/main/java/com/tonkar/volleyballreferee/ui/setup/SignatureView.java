package com.tonkar.volleyballreferee.ui.setup;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SignatureView extends View {
    private Path path = new Path();
    private Paint paint = new Paint();

    public SignatureView(Context c, AttributeSet a) {
        super(c, a);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6f);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX(), y = e.getY();
        if (e.getAction() == MotionEvent.ACTION_DOWN) path.moveTo(x, y);
        else if (e.getAction() == MotionEvent.ACTION_MOVE) path.lineTo(x, y);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas c) {
        c.drawColor(Color.WHITE);
        c.drawPath(path, paint);
    }

    public Bitmap getBitmap() {
        Bitmap b = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        draw(c);
        return b;
    }
}
