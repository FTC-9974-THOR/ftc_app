package com.smartstar.opencvteamcode;

import android.annotation.SuppressLint;
import android.content.Context;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;

/**
 * Created by christopher.johnson on 10/9/17.
 */

public class OpenCVLoader {

    public static boolean openCVReady = false;

    private static boolean allowUnsafeInit = false;

    //private BaseLoaderCallback callback;

    public void safeInit(Context context) {
        /*callback = new BaseLoaderCallback(context) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                        openCVReady = true;
                        break;
                    case LoaderCallbackInterface.INSTALL_CANCELED:
                        RobotLog.setGlobalErrorMsg("OpenCV initialisation failed: User canceled");
                        break;
                    case LoaderCallbackInterface.INIT_FAILED:
                        RobotLog.setGlobalErrorMsg("OpenCV initialisation failed: INIT_FAILED");
                        break;
                    case LoaderCallbackInterface.INCOMPATIBLE_MANAGER_VERSION:
                        RobotLog.setGlobalErrorMsg("OpenCV initialisation failed: Manager incompatible");
                        break;
                    case LoaderCallbackInterface.MARKET_ERROR:
                        RobotLog.setGlobalErrorMsg("OpenCV initialisation failed: MARKET_ERROR");
                        break;
                }
            }
        };*/
        openCVReady = org.opencv.android.OpenCVLoader.initDebug ();
        //(org.opencv.android.OpenCVLoader.OPENCV_VERSION, context, callback);
    }
}
