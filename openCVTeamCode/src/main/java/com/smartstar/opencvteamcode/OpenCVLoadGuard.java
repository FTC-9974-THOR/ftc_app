package com.smartstar.opencvteamcode;

import android.annotation.SuppressLint;
import android.content.Context;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

/**
 * Created by christopher.johnson on 10/9/17.
 */

public class OpenCVLoadGuard {

    public static boolean openCVReady = false;
    public static boolean safeInit() {
        if (!openCVReady) {
            openCVReady = OpenCVLoader.initDebug();
        }
        return openCVReady;
    }
}
