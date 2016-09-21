package com.github.hiveimage;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.squareup.picasso.Transformation;

/**
 * Created by mason on 2016/9/22 0022.
 */

public class HexagonTransFormer implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        float l = (float) (size / 2);
        float h = (float) (Math.sqrt(3)*l);
        float top = (size - h) / 2  ;
        Path mPath =  new Path();
        mPath.reset();
        mPath.moveTo(l/2,top);
        mPath.lineTo(0,h/2+top);
        mPath.lineTo(l/2,h+top);
        mPath.lineTo((float) (l*1.5),h+top);
        mPath.lineTo(2*l,h/2+top);
        mPath.lineTo((float) (l*1.5),top);
        mPath.lineTo(l/2,top);
        mPath.close();
        canvas.drawPath(mPath, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "hive";

    }
}
