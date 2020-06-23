package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import com.aghamiri.devicemonitor.R;


public class SensorInfoUtil {
    private Activity mActivity;
    private SensorManager mSensorManager;
    // sensor textview
    private TextView textSAcc;
    private TextView textSAll;
    private TextView textSAT;
    private TextView textSGameRV;
    private TextView textSGeoRV;
    private TextView textSGravity;
    private TextView textSGyroscope;
    private TextView textSGU;
    private TextView textSHR;
    private TextView textSL;
    private TextView textSLA;
    private TextView textSMF;
    private TextView textSMFU;
    private TextView textSO;
    private TextView textSPressure;
    private TextView textSProximity;
    private TextView textSRH;
    private TextView textSRV;
    private TextView textSSM;
    private TextView textSSC;
    private TextView textSSD;
    private TextView textST;

    public SensorInfoUtil(Activity activity, TextView... text) {
        this.mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        this.mActivity = activity;
        this.textSAcc = text[0];
        this.textSAll = text[1];
        this.textSAT = text[2];
        this.textSGameRV = text[3];
        this.textSGeoRV = text[4];
        this.textSGravity = text[5];
        this.textSGyroscope = text[6];
        this.textSGU = text[7];
        this.textSHR = text[8];
        this.textSL = text[9];
        this.textSLA = text[10];
        this.textSMF = text[11];
        this.textSMFU  = text[12];
        this.textSO = text[13];
        this.textSPressure = text[14];
        this.textSProximity = text[15];
        this.textSRH = text[16];
        this.textSRV = text[17];
        this.textSSM = text[18];
        this.textSSC = text[19];
        this.textSSD = text[20];
        this.textST = text[21];
    }

    // sensor accelerometer
    private SensorEventListener mSAccListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            try
            {
                if(event != null && event.values.length == 3)
                {
                    textSAcc.setText("x = " + (Math.floor(event.values[0] * 10) / 10) + " m/s² " +
                            "| y = " + (Math.floor(event.values[1] * 10) / 10) + " m/s² " +
                            "| z = " + (Math.floor(event.values[2] * 10) + " m/s²") + "\n");
                }
                else if(event != null && event.values.length == 2)
                {
                    textSAcc.setText("x = " + (Math.floor(event.values[0] * 10) / 10) + " m/s² " +
                            "| y = " + (Math.floor(event.values[1] * 10) / 10) + " m/s² " + "\n" );

                }
                else if(event != null && event.values.length == 1)
                {
                    textSAcc.setText("x = " + (Math.floor(event.values[0] * 10) / 10) + " m/s² " + "\n" );
                }
                else
                {
                    textSAcc.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
                }
            }
            catch (Exception e)
            {

            }
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorAccelerometer () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            mSensorManager.registerListener(mSAccListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSAcc.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor all
    private SensorEventListener mSAllListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSAll.setText("x = " + (Math.floor(event.values[0] * 10) / 10) + " m/s² " +
                    "| y = " + (Math.floor(event.values[1] * 10) / 10) + " m/s² " +
                    "| z = " + (Math.floor(event.values[2] * 10) + " m/s²") + "\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorAll () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ALL);
        if (sensor != null) {
            mSensorManager.registerListener(mSAllListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSAll.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor ambient temperature
    private SensorEventListener mSATListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSAT.setText((Math.floor(event.values[0] * 10) / 10) + " °C\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorAmbientTemperature () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sensor != null) {
            mSensorManager.registerListener(mSATListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSAT.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor game rotation vector
    private SensorEventListener mSGameRVListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSGameRV.setText((Math.floor(event.values[0] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[1] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[2] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[3] * 10) / 10) + " rad\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorGameRotationVector () {
        Sensor sensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        }
        if (sensor != null) {
            mSensorManager.registerListener(mSGameRVListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSGameRV.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor geomagnetic rotation vector
    private SensorEventListener mSGeoRVListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSGeoRV.setText((Math.floor(event.values[0] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[1] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[2] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[3] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[4] * 10) / 10) + " rad\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorGeomagneticRotationVector () {
        Sensor sensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        }
        if (sensor != null) {
            mSensorManager.registerListener(mSGeoRVListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSGeoRV.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor gravity
    private SensorEventListener mSGravityListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSGravity.setText((Math.floor(event.values[0] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[1] * 10) / 10) + " rad | " +
                    (Math.floor(event.values[2] * 10) / 10) + " rad\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorGravity () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (sensor != null) {
            mSensorManager.registerListener(mSGravityListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSGravity.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor gyroscope
    private SensorEventListener mSGyroscopeListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSGyroscope.setText("x = " + (Math.floor(event.values[0] * 10) / 10) + " rad/s " +
                    "| y = " + (Math.floor(event.values[1] * 10) / 10) + " rad/s " +
                    "| z = " + (Math.floor(event.values[2] * 10) / 10) + " rad/s\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorGyroscope () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (sensor != null) {
            mSensorManager.registerListener(mSGyroscopeListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSGyroscope.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor gyroscope uncalibrated
    private SensorEventListener mSGUListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSGU.setText("x = " + (Math.floor(event.values[0] * 10) / 10) + " rad/s " +
                    "| y = " + (Math.floor(event.values[1] * 10) / 10) + " rad/s " +
                    "| z = " + (Math.floor(event.values[2] * 10) / 10) + " rad/s\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorGyroscopeUncalibrated () {
        Sensor sensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        }
        if (sensor != null) {
            mSensorManager.registerListener(mSGUListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSGU.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor heart rate
    private SensorEventListener mSHRListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSHR.setText("x = " + (Math.floor(event.values[0] * 10) / 10) + " rad/s " +
                    "| y = " + (Math.floor(event.values[1] * 10) / 10) + " rad/s " +
                    "| z = " + (Math.floor(event.values[2] * 10) / 10) + " rad/s\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorHeartRate () {
        Sensor sensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        }
        if (sensor != null) {
            mSensorManager.registerListener(mSHRListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSHR.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor light
    private SensorEventListener mSLListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSL.setText((Math.floor(event.values[0] * 10) / 10) + " lux\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorLight () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sensor != null) {
            mSensorManager.registerListener(mSLListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSL.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor linear acceleration
    private SensorEventListener mSLAListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSLA.setText((Math.floor(event.values[0] * 10) / 10) + " ° | " +
                    (Math.floor(event.values[1] * 10) / 10) + " ° | " +
                    (Math.floor(event.values[2] * 10) / 10) + " °\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorLinearAcceleration () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (sensor != null) {
            mSensorManager.registerListener(mSLAListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSL.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor magnetic field
    private SensorEventListener mSMFListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSMF.setText((Math.floor(event.values[0] * 10) / 10) + " µT | " +
                    (Math.floor(event.values[0] * 10) / 10) + " µT | " +
                    (Math.floor(event.values[0] * 10) / 10) + " µT\n");
        }
    };
    public void getSensorMagneticField () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (sensor != null) {
            mSensorManager.registerListener(mSMFListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSMF.setText(mActivity.getString(R.string.sensor_that_device_not_support));
        }
    }
    // sensor magnetic field uncalibrated
    private SensorEventListener mSMFUListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSMFU.setText((Math.floor(event.values[0] * 10) / 10) + " µT | " +
                    (Math.floor(event.values[1] * 10) / 10) + " µT | " +
                    (Math.floor(event.values[2] * 10) / 10) + " µT\n");
        }
    };
    public void getSensorMagneticFieldUncalibrated () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor != null) {
            mSensorManager.registerListener(mSMFUListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSMFU.setText(mActivity.getString(R.string.sensor_that_device_not_support));
        }
    }
    // sensor orientation
    private SensorEventListener mSOListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSO.setText((Math.floor(event.values[0] * 10) / 10) + " ° | " +
                    (Math.floor(event.values[1] * 10) / 10) + " ° | " +
                    (Math.floor(event.values[2] * 10) / 10) + " °\n");
        }
    };
    public void getSensorOrientation () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor != null) {
            mSensorManager.registerListener(mSOListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSO.setText(mActivity.getString(R.string.sensor_that_device_not_support));
        }
    }
    // sensor pressure
    private SensorEventListener mSPressureListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSPressure.setText((Math.floor(event.values[0] * 10) / 10) + " | " +
                    (Math.floor(event.values[1] * 10) / 10) + " | " +
                    (Math.floor(event.values[2] * 10) / 10) + "\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorPressure () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (sensor != null) {
            mSensorManager.registerListener(mSPressureListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSPressure.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor proximity - tiem can
    private SensorEventListener mSProximityListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSProximity.setText((Math.floor(event.values[0] * 10) / 10) + " cm | " +
                    (Math.floor(event.values[1] * 10) / 10) + " cm | " +
                    (Math.floor(event.values[2] * 10) / 10) + " cm\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorProximity () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (sensor != null) {
            mSensorManager.registerListener(mSProximityListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSProximity.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor relative humidity
    private SensorEventListener mSRHListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSRH.setText((Math.floor(event.values[0] * 10) / 10) + " | " +
                    (Math.floor(event.values[1] * 10) / 10) + " | " +
                    (Math.floor(event.values[2] * 10) / 10) + "\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorRelativeHumidity () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (sensor != null) {
            mSensorManager.registerListener(mSRHListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSRH.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor rotation vector
    private SensorEventListener mSRVListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSRV.setText((Math.floor(event.values[0] * 10) / 10) + " | " +
                    (Math.floor(event.values[1] * 10) / 10) + " | " +
                    (Math.floor(event.values[2] * 10) / 10) + "\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorRotationVector () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (sensor != null) {
            mSensorManager.registerListener(mSRVListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSRV.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor significant motion
    private SensorEventListener mSSMListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSSM.setText((Math.floor(event.values[0] * 10) / 10) + " m/s² | " +
                    (Math.floor(event.values[1] * 10) / 10) + " m/s² | " +
                    (Math.floor(event.values[2] * 10) / 10) + " m/s²\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorSignificantMotion () {
        Sensor sensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        }
        if (sensor != null) {
            mSensorManager.registerListener(mSSMListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSSM.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor step counter
    private SensorEventListener mSSCListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSSC.setText((Math.floor(event.values[0] * 10) / 10) + " step");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorStepCounter () {
        Sensor sensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
        if (sensor != null) {
            mSensorManager.registerListener(mSSCListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSSC.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor step detector
    private SensorEventListener mSSDListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textSSD.setText((Math.floor(event.values[0] * 10) / 10) + " | " +
                    (Math.floor(event.values[1] * 10) / 10) + " | " +
                    (Math.floor(event.values[2] * 10) / 10) + "\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorStepDetector () {
        Sensor sensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        }
        if (sensor != null) {
            mSensorManager.registerListener(mSSDListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textSSD.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
    // sensor temperature
    private SensorEventListener mSTListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            textST.setText((Math.floor(event.values[0] * 10) / 10) + " | " +
                    (Math.floor(event.values[1] * 10) / 10) + " | " +
                    (Math.floor(event.values[2] * 10) / 10) + "\n");
        }
    };
    @SuppressLint("SetTextI18n")
    public void getSensorTemperature () {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        if (sensor != null) {
            mSensorManager.registerListener(mSTListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textST.setText(mActivity.getString(R.string.sensor_that_device_not_support) + "\n");
        }
    }
}
