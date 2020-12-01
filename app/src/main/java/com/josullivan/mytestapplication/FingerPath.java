package com.josullivan.mytestapplication;

import android.graphics.Path;


public class FingerPath{

    public int color;
    public boolean emboss;
    public boolean blur;
    public int strokeWidth;
    public Path path;
    public float x;
    public float y;
    public float xEnd, yEnd;

    public FingerPath(int color, boolean emboss, boolean blur, int strokeWidth, Path path, float x, float y) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.path = path;
        this.x = x;
        this.y = y;

    }


}
