package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class GPUInfoUtil {
    private String[] openGLString;
    private int number;
    // gpu
    private TextView mGPURenderer;
    private TextView mGPUVendor;
    private TextView mGPUVersion;
    // high
    private LinearLayout high;
    private TextView mGPUViewportSize;
    private TextView mGPURenderbufferSize;
    private TextView mGPUCubemapSize;
    private TextView mGPUTextureSize;
    private TextView mGPUTextureUnits;
    private TextView mGPUVertexTextures;
    private TextView mGPUCombinedTextures;
    private TextView mGPUVertexAttributes;
    private TextView mGPUVertexUniforms;
    private TextView mGPUVaryingVectors;
    private TextView mGPUFragmentUniformVectors;
    // low
    private LinearLayout low;
    private TextView mGPUViewportDimension;
    private TextView mGPUTextureSize2;
    private TextView mGPUTextureStackDepth;
    private TextView mGPUTextureUnits2;
    private TextView mGOUDepthBits;
    private TextView mGPUDepthBufferBit;
    private TextView mGPULuminance;
    private TextView mGPULuminanceAlpha;
    private TextView mGPUMaxLights;
    private TextView mGPUModelviewStackDepth;
    private TextView mGPUProjectionStackDepth;
    private TextView mGPUElementsIndices;
    private TextView mGPUElementsVertices;
    // extensions
    private TextView mGPUExtensions;

    public GPUInfoUtil (Activity activity, LinearLayout high, LinearLayout low, TextView... text) {
        this.mGPURenderer = text[0];
        this.mGPUVendor = text[1];
        this.mGPUVersion = text[2];
        //high
        this.high = high;
        this.mGPUViewportSize = text[3];
        this.mGPURenderbufferSize = text[4];
        this.mGPUCubemapSize = text[5];
        this.mGPUTextureSize = text[6];
        this.mGPUTextureUnits = text[7];
        this.mGPUVertexTextures = text[8];
        this.mGPUCombinedTextures = text[9];
        this.mGPUVertexAttributes = text[10];
        this.mGPUVertexUniforms = text[11];
        this.mGPUVaryingVectors = text[12];
        this.mGPUFragmentUniformVectors = text[13];
        // low
        this.low = low;
        mGPUViewportDimension = text[14];
        mGPUTextureSize2 = text[15];
        mGPUTextureStackDepth = text[16];
        mGPUTextureUnits2 = text[17];
        mGOUDepthBits = text[18];
        mGPUDepthBufferBit = text[19];
        mGPULuminance = text[20];
        mGPULuminanceAlpha = text[21];
        mGPUMaxLights = text[22];
        mGPUModelviewStackDepth = text[23];
        mGPUProjectionStackDepth = text[24];
        mGPUElementsIndices = text[25];
        mGPUElementsVertices = text[26];
        // extensions
        this.mGPUExtensions = text[27];
        LocalBroadcastManager.getInstance(activity).registerReceiver(openGL, new IntentFilter("OpenGL"));
    }

    BroadcastReceiver openGL = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            openGLString = intent.getStringArrayExtra("OpenGLString");
            number = intent.getIntExtra("OpenGLNumber", 0);
            mGPURenderer.setText(openGLString[0]);
            mGPUVendor.setText(openGLString[1]);
            mGPUVersion.setText(openGLString[2]);
            if (number == 15) {
                low.setVisibility(View.GONE);
                mGPUViewportSize.setText(openGLString[3]);
                mGPURenderbufferSize.setText(openGLString[4]);
                mGPUCubemapSize.setText(openGLString[5]);
                mGPUTextureSize.setText(openGLString[6]);
                mGPUTextureUnits.setText(openGLString[7]);
                mGPUVertexTextures.setText(openGLString[8]);
                mGPUCombinedTextures.setText(openGLString[9]);
                mGPUVertexAttributes.setText(openGLString[10]);
                mGPUVertexUniforms.setText(openGLString[11]);
                mGPUVaryingVectors.setText(openGLString[12]);
                mGPUFragmentUniformVectors.setText(openGLString[13]);
                mGPUExtensions.setText(openGLString[14]);
            } else if (number == 17) {
                high.setVisibility(View.GONE);
                mGPUViewportDimension.setText(openGLString[3]);
                mGPUTextureSize2.setText(openGLString[4]);
                mGPUTextureStackDepth.setText(openGLString[5]);
                mGPUTextureUnits2.setText(openGLString[6]);
                mGOUDepthBits.setText(openGLString[7]);
                mGPUDepthBufferBit.setText(openGLString[8]);
                mGPULuminance.setText(openGLString[9]);
                mGPULuminanceAlpha.setText(openGLString[10]);
                mGPUMaxLights.setText(openGLString[11]);
                mGPUModelviewStackDepth.setText(openGLString[12]);
                mGPUProjectionStackDepth.setText(openGLString[13]);
                mGPUElementsIndices.setText(openGLString[14]);
                mGPUElementsVertices.setText(openGLString[15]);
                mGPUExtensions.setText(openGLString[16]);
            }
        }
    };
}