package com.kt.iot.mobile.ui.custom;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.kt.iot.mobile.utils.ApplicationPreference;
import com.nostra13.universalimageloader.core.assist.ContentLengthInputStream;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.IoUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ceoko on 15. 5. 26..
 */
public class GiGaIotImageDownloader extends BaseImageDownloader {

    private final String TAG = GiGaIotImageDownloader.class.getSimpleName();

    public GiGaIotImageDownloader(Context context) {
        super(context);
    }

    @Override
    protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {

        Log.d(TAG, "getStreamFromNetwork imageUri = " + imageUri);

        imageUri = imageUri.substring(0, imageUri.lastIndexOf("/"));

        Log.d(TAG, "getStreamFromNetwork after substring imageUri ??????= " + imageUri);

        HttpURLConnection conn = createConnection(imageUri, extra);

        int redirectCount = 0;
        while (conn.getResponseCode() / 100 == 3 && redirectCount < MAX_REDIRECT_COUNT) {
            Log.d("JJJJJJ","JJJJ:"+redirectCount);
            conn = createConnection(conn.getHeaderField("Location"), extra);
            redirectCount++;
        }
            conn.setRequestProperty("Authorization","Bearer "+ApplicationPreference.getInstance().getPrefAccessToken());
        InputStream imageStream;
        try {
            imageStream = conn.getInputStream();
        } catch (IOException e) {
            // Read all data to allow reuse connection (http://bit.ly/1ad35PY)
            IoUtils.readAndCloseStream(conn.getErrorStream());
            throw e;
        }
        return new ContentLengthInputStream(new BufferedInputStream(imageStream, BUFFER_SIZE), conn.getContentLength());
        //return super.getStreamFromNetwork(imageUri, extra);
    }
}
