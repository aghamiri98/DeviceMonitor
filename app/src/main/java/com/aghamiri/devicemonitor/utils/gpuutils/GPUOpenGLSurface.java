package com.aghamiri.devicemonitor.utils.gpuutils;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;


public class GPUOpenGLSurface extends GLSurfaceView {
    private final GPUOpenGLRenderer renderer;

    public GPUOpenGLSurface(Context context) {
        super(context);
        Log.i("rendertest", "RTSurface constructor - Default Form");
        renderer = new GPUOpenGLRenderer(context);
        setRenderer(renderer);
    }

    public GPUOpenGLSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("rendertest", "RTSurface constructor - Layout Form");
        renderer = new GPUOpenGLRenderer(context);
        setRenderer(renderer);
    }
}
