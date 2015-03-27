package com.brcorner.ddinaping.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Auser on 2015/3/9.
 */
public class ImageUtils {
    /**
     * 缩放图片大小
     * @param bm
     * @param newWidth
     * @return
     */
    public static Bitmap zoomImgByWidth(Bitmap bm, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
                true);
    }
}
