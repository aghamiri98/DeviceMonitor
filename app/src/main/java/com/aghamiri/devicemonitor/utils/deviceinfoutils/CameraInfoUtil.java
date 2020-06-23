package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.aghamiri.devicemonitor.R;

import java.util.List;


public class CameraInfoUtil {
    // set layout cameras
    public static void setLayoutCameras (Activity activity, TextView checkCameraText, RelativeLayout checkCameraRelative, LinearLayout linearLayout) {
        int mNumberOfCameras = Camera.getNumberOfCameras();
        if (mNumberOfCameras == 0) {
            checkCameraText.setText(activity.getString(R.string.no_camera));
            checkCameraRelative.setVisibility(View.GONE);
        } else if (mNumberOfCameras == 1) {
            linearLayout.setVisibility(View.GONE);
        }
    }
    // check number of cameras
    public static int checkNumberOfCameras () {
        return Camera.getNumberOfCameras();
    }
    // default camera back
    @SuppressLint("SetTextI18n")
    public static void setDefaultCamera (Activity activity, int numberOfCameras, TextView... text) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(numberOfCameras, cameraInfo);
        text[0].setText((cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK ? activity.getString(R.string.camera_type_back) : activity.getString(R.string.camera_type_front)));
        text[1].setText("" + cameraInfo.orientation);

        Camera camera = Camera.open(numberOfCameras);
        Camera.Parameters parameters = camera.getParameters();
        text[2].setText("" + parameters.getAntibanding());
        text[3].setText("" + parameters.getAutoExposureLock());
        text[4].setText("" + parameters.getAutoWhiteBalanceLock());
        text[5].setText("" + parameters.getColorEffect());
        text[6].setText("" + parameters.getExposureCompensation());
        text[7].setText("" + parameters.getExposureCompensationStep());
        text[8].setText("" + parameters.getFlashMode());
        text[9].setText("" + parameters.getFocalLength());
        text[10].setText("" + parameters.getMaxNumFocusAreas());

        float[] focusDistance = new float[3];
        parameters.getFocusDistances(focusDistance);
        text[11].setText("" + focusDistance[Camera.Parameters.FOCUS_DISTANCE_NEAR_INDEX]);
        text[12].setText("" + focusDistance[Camera.Parameters.FOCUS_DISTANCE_FAR_INDEX]);
        text[13].setText("" + focusDistance[Camera.Parameters.FOCUS_DISTANCE_OPTIMAL_INDEX]);
        text[14].setText("" + parameters.getFocusMode());
        text[15].setText("" + parameters.getHorizontalViewAngle());
        text[16].setText("" + parameters.getJpegQuality());
        text[17].setText("" + parameters.getJpegThumbnailQuality());
        text[18].setText("" + cameraSizeToString(parameters.getJpegThumbnailSize()));
        text[19].setText("" + parameters.getMaxExposureCompensation());
        text[20].setText("" + parameters.getMaxNumDetectedFaces());
        text[21].setText("" + parameters.getMaxNumMeteringAreas());
        text[22].setText("" + parameters.getMaxZoom());
        text[23].setText("" + parameters.getMinExposureCompensation());
        text[24].setText("" + pictureFormatToString(parameters.getPictureFormat()));
        text[25].setText("" + cameraSizeToString(parameters.getPictureSize()));
        text[26].setText("" + cameraSizeToString(parameters.getPreferredPreviewSizeForVideo()));
        text[27].setText("" + pictureFormatToString(parameters.getPreviewFormat()));

        int[] previewFpsRange = new int[2];
        parameters.getPreviewFpsRange(previewFpsRange);
        text[28].setText("" + previewFpsRange[Camera.Parameters.PREVIEW_FPS_MIN_INDEX]);
        text[29].setText("" + previewFpsRange[Camera.Parameters.PREVIEW_FPS_MAX_INDEX]);
        text[30].setText("" + parameters.getPreviewFrameRate());
        text[31].setText("" + cameraSizeToString(parameters.getPreviewSize()));
        text[32].setText("" + parameters.getSceneMode());

        String strSupportedAntibanding = "";
        List<String> supportedAntibanding = parameters.getSupportedAntibanding();
        if (supportedAntibanding != null) {
            for (String antibanding : supportedAntibanding) {
                strSupportedAntibanding += "" + antibanding + "  ";
            }
        }
        text[33].setText(strSupportedAntibanding);

        String strSupportedColorEffects = "";
        List<String> supportedColorEffects = parameters.getSupportedColorEffects();
        if (supportedColorEffects != null) {
            for (String colorEffect : supportedColorEffects) {
                strSupportedColorEffects += "" + colorEffect + "  ";
            }
        }
        text[34].setText(strSupportedColorEffects);

        String strSupportedFlashModes = "";
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes != null) {
            for (String flashMode : supportedFlashModes) {
                strSupportedFlashModes += "" + flashMode + "  ";
            }
        }
        text[35].setText(strSupportedFlashModes);

        String strSupportedJpegThumbnailSizes = "";
        List<Camera.Size> supportedJpegThumbnailSizes = parameters.getSupportedJpegThumbnailSizes();
        if (supportedJpegThumbnailSizes != null) {
            for (Camera.Size size : supportedJpegThumbnailSizes) {
                strSupportedJpegThumbnailSizes += "" + cameraSizeToString(size) + "  ";
            }
        }
        text[36].append(strSupportedJpegThumbnailSizes);



        String strSupportedPictureFormat = "";
        List<Integer> supportedPictureFormat = parameters.getSupportedPictureFormats();
        if (supportedPictureFormat != null) {
            for (int pictureFormat : supportedPictureFormat) {
                strSupportedPictureFormat += "" + pictureFormatToString(pictureFormat) + "  ";
            }
        }
        text[37].setText(strSupportedPictureFormat);

        int maxWith = 0;
        int maxHeight = 0;
        String strSupportedPictureSizes = "";
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        if (supportedPictureSizes != null) {
            for (Camera.Size pictureSize : supportedPictureSizes) {
                if(pictureSize.width > maxWith)
                {
                    maxWith = pictureSize.width;
                }

                if(pictureSize.height > maxHeight)
                {
                    maxHeight = pictureSize.height;
                }
                strSupportedPictureSizes += "" + cameraSizeToString(pictureSize) + "  ";
            }
        }
        text[38].setText(strSupportedPictureSizes);
        // set Megapixel
        double mp = (Math.ceil((double)(maxHeight*maxWith/1000000D)));
        if(mp > 0)
        {
            text[0].setText((cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK ? activity.getString(R.string.camera_type_back) : activity.getString(R.string.camera_type_front)) + " - " + mp + " MP" + "(" + maxWith +"x" + maxHeight +")");
        }



        String strSupportedPreviewFormats = "";
        List<Integer> supportedPreviewFormats = parameters.getSupportedPreviewFormats();
        if (supportedPreviewFormats != null) {
            for (int previewFormat : supportedPreviewFormats) {
                strSupportedPreviewFormats += "" + pictureFormatToString(previewFormat) + "  ";
            }
        }
        text[39].setText(strSupportedPreviewFormats);

        String strSupportedPreviewFpsRange = "";
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange != null) {
            for (int[] fpsRange : supportedPreviewFpsRange) {
                strSupportedPreviewFpsRange += "[" + fpsRange[Camera.Parameters.PREVIEW_FPS_MIN_INDEX] + ", " + fpsRange[Camera.Parameters.PREVIEW_FPS_MAX_INDEX] + "]  ";
            }
        }
        text[40].setText(strSupportedPreviewFpsRange);

        String strSupportedPreviewFrameRate = "";
        List<Integer> supportedPreviewFrameRate = parameters.getSupportedPreviewFrameRates();
        if (supportedPreviewFrameRate != null) {
            for (int frameRate : supportedPreviewFrameRate) {
                strSupportedPreviewFrameRate += frameRate + "  ";
            }
        }
        text[41].setText(strSupportedPreviewFrameRate);

        String strSupportedPreviewSizes = "";
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes != null) {
            for (Camera.Size size : supportedPreviewSizes) {
                strSupportedPreviewSizes += cameraSizeToString(size) + "  ";
            }
        }
        text[42].setText(strSupportedPreviewSizes);

        String strSupportedSceneModes = "";
        List<String> supportedSceneModes = parameters.getSupportedSceneModes();
        if (supportedSceneModes != null) {
            for (String mode : supportedSceneModes) {
                strSupportedSceneModes += mode + "  ";
            }
        }
        text[43].setText(strSupportedSceneModes);

        String strSupportedVideoSizes = "";
        List<Camera.Size> supportedVideoSizes = parameters.getSupportedVideoSizes();
        if (supportedVideoSizes != null) {
            for (Camera.Size size : supportedVideoSizes) {
                strSupportedVideoSizes += cameraSizeToString(size) + "  ";
            }
        }
        text[44].setText(strSupportedVideoSizes);

        String strSupportedWhiteBalance = "";
        List<String> supportedWhiteBalance = parameters.getSupportedWhiteBalance();
        if (supportedWhiteBalance != supportedWhiteBalance) {
            for (String whiteBalance : supportedWhiteBalance) {
                strSupportedWhiteBalance += whiteBalance + "  ";
            }
        }
        text[45].setText(strSupportedWhiteBalance);

        text[46].setText("" + parameters.getVerticalViewAngle());
        text[47].setText("" + parameters.getVideoStabilization());
        text[48].setText("" + parameters.getWhiteBalance());
        text[49].setText("" + parameters.getZoom());

        String strZoomRatios = "";
        List<Integer> zoomRatios = parameters.getZoomRatios();
        if (zoomRatios != null) {
            for (int zoomRatio : zoomRatios) {
                strZoomRatios += zoomRatio + "  ";
            }
        }
        text[50].setText(strZoomRatios);

        text[51].setText("" + parameters.isAutoExposureLockSupported());
        text[52].setText("" + parameters.isAutoWhiteBalanceLockSupported());
        text[53].setText("" + parameters.isSmoothZoomSupported());
        text[54].setText("" + parameters.isVideoSnapshotSupported());
        text[55].setText("" + parameters.isVideoStabilizationSupported());
        text[56].setText("" + parameters.isZoomSupported());
        text[57].setText("" + parameters.flatten());

        camera.release();
    }
    private static String cameraSizeToString(Camera.Size size)
    {
        try
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" { " + size.width + "," + size.height + " } ");
            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            return "";
        }


    }
    private static String pictureFormatToString(int pictureFormat) {
        switch (pictureFormat) {
            case ImageFormat.JPEG:
                return "JPEG";
            case ImageFormat.NV16:
                return "NV16";
            case ImageFormat.NV21:
                return "NV21";
            case ImageFormat.RAW10:
                return "RAW10";
            case ImageFormat.RAW_SENSOR:
                return "RAW_SENSOR";
            case ImageFormat.RGB_565:
                return "RGB_565";
            case ImageFormat.UNKNOWN:
                return "UNKNOWN";
            case ImageFormat.YUV_420_888:
                return "YUV_420_888";
            case ImageFormat.YUY2:
                return "YUY2";
            case ImageFormat.YV12:
                return "YV12";
            default:
                return "There is no picture format";
        }
    }
}
