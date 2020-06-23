package com.aghamiri.devicemonitor.utils.gpuutils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class GPUOpenGLRenderer implements GLSurfaceView.Renderer {
    private boolean checkOne = false;
    private boolean checkTwo = false;
    Context context;

    public GPUOpenGLRenderer (Context context) {
        this.context = context;
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo();
        checkOne = deviceConfigurationInfo.reqGlEsVersion >= 131072;
        checkTwo = deviceConfigurationInfo.reqGlEsVersion >= 196608;
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        String[] openGL;
        int number;
        IntBuffer allocate = IntBuffer.allocate(4);
        String glGetString = this.checkTwo ? "OpenGL ES 3.0" : this.checkOne ? "OpenGL ES 2.0" : gl.glGetString(7938);
        if (this.checkOne) {
            number = 15;
            openGL = new String[number];
            openGL[0] = gl.glGetString(7937);
            openGL[1] = gl.glGetString(7936);
            openGL[2] = checkTwo ? "OpenGL ES 3.0" : checkOne ? "OpenGL ES 2.0" : gl.glGetString(7938);
            gl.glGetIntegerv(3386, allocate);
            openGL[3] = allocate.get(0) + " x " + allocate.get(1);
            gl.glGetIntegerv(34024, allocate);
            openGL[4] = allocate.get(0) + " x " + allocate.get(0);
            gl.glGetIntegerv(34076, allocate);
            openGL[5] = allocate.get(0) + " x " + allocate.get(0);
            gl.glGetIntegerv(3379, allocate);
            openGL[6] = allocate.get(0) + " x " + allocate.get(0);
            gl.glGetIntegerv(34930, allocate);
            openGL[7] = "" + allocate.get(0);
            gl.glGetIntegerv(35660, allocate);
            openGL[8] = "" + allocate.get(0);
            gl.glGetIntegerv(35661, allocate);
            openGL[9] = "" + allocate.get(0);
            gl.glGetIntegerv(34921, allocate);
            openGL[10] = "" + allocate.get(0);
            gl.glGetIntegerv(36347, allocate);
            openGL[11] = "" + allocate.get(0);
            gl.glGetIntegerv(36348, allocate);
            openGL[12] = "" + allocate.get(0);
            gl.glGetIntegerv(36349, allocate);
            openGL[13] = "" + allocate.get(0);
            openGL[14] = gl.glGetString(7939);
        } else {
            number = 17;
            openGL = new String[number];
            openGL[0] = gl.glGetString(7937);
            openGL[1] = gl.glGetString(7936);
            openGL[2] = checkTwo ? "OpenGL ES 3.0" : checkOne ? "OpenGL ES 2.0" : gl.glGetString(7938);
            gl.glGetIntegerv(3386, allocate);
            openGL[3] = allocate.get(0) + " x " + allocate.get(0);
            gl.glGetIntegerv(3379, allocate);
            openGL[4] = allocate.get(0) + " x " + allocate.get(0);
            gl.glGetIntegerv(3385, allocate);
            openGL[5] = "" + allocate.get(0);
            gl.glGetIntegerv(34018, allocate);
            openGL[6] = "" + allocate.get(0);
            gl.glGetIntegerv(3414, allocate);
            openGL[7] = "" + allocate.get(0);
            gl.glGetIntegerv(256, allocate);
            openGL[8] = "" + allocate.get(0);
            gl.glGetIntegerv(6409, allocate);
            openGL[9] = "" + allocate.get(0);
            gl.glGetIntegerv(6410, allocate);
            openGL[10] = "" + allocate.get(0);
            gl.glGetIntegerv(3377, allocate);
            openGL[11] = "" + allocate.get(0);
            gl.glGetIntegerv(3382, allocate);
            openGL[12] = "" + allocate.get(0);
            gl.glGetIntegerv(3384, allocate);
            openGL[13] = "" + allocate.get(0);
            gl.glGetIntegerv(33001, allocate);
            openGL[14] = "" + allocate.get(0);
            gl.glGetIntegerv(33000, allocate);
            openGL[15] = "" + allocate.get(0);
            openGL[16] = gl.glGetString(7939);
        }
        Log.i("gl", glGetString);
        Intent openGLData = new Intent("OpenGL");
        openGLData.putExtra("OpenGLString", openGL);
        openGLData.putExtra("OpenGLNumber", number);
        LocalBroadcastManager.getInstance(context).sendBroadcast(openGLData);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
    }
}
