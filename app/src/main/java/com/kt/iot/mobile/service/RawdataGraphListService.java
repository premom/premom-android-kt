package com.kt.iot.mobile.service;

import android.support.v4.app.Fragment;

import com.kt.gigaiot_sdk.TagStrmApi;
import com.kt.gigaiot_sdk.data.Device;
import com.kt.gigaiot_sdk.data.Log;
import com.kt.gigaiot_sdk.data.TagStrm;
import com.kt.gigaiot_sdk.data.TagStrmApiResponse;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.iot.mobile.data.LogStream;
import com.kt.iot.mobile.utils.ApplicationPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP1014425901 on 2015-03-12.
 */
public class RawdataGraphListService {

    private static final String TAG = RawdataGraphListService.class.getSimpleName();

    public interface UpdateListener {
        public void onUpdate(List<LogStream> list);
    }

    private List<UpdateListener> listeners;
    private Fragment fragment;

    public RawdataGraphListService(Fragment fragment) {
        this.fragment = fragment;
        this.listeners = new ArrayList<UpdateListener>();
    }

    public void addListener(UpdateListener listener) {
        if (listeners != null && listener != null) {
            listeners.add(listener);
        }
    }

    public void removeListener(UpdateListener listener) {
        if (listeners != null && listener != null) {
            listeners.remove(listener);
        }
    }

    public void updateGraph(List<LogStream> list) {
        if (listeners != null) {
            for (UpdateListener listener : listeners) {
                listener.onUpdate(list);
            }
        }
    }

    private LogPollingThread logPollingThread;

    public void startPolling(Device device) {
        if (logPollingThread != null) {
            logPollingThread.stopThread();
        }
        logPollingThread = new LogPollingThread(device);
        logPollingThread.startThread();
    }

    public void stopPolling() {
        if (logPollingThread != null) {
            logPollingThread.stopThread();
        }
    }

    class LogPollingThread extends Thread {

        private boolean isRunning = false;

        private Device device;

        LogPollingThread(Device device) {
            this.device = device;
        }

        public void startThread() {
            isRunning = true;
            this.start();
        }

        public void stopThread() {
            isRunning = false;
        }

        @Override
        public void run() {

            if (device != null ) {

                if (device.getTagStrmList() != null && device.getTagStrmList().size() > 0) {

                    TagStrmApi tagStrmApi = new TagStrmApi(ApplicationPreference.getInstance().getPrefAccessToken());

                    //while(isRunning) {

                        List<LogStream> logStreams = new ArrayList<LogStream>();

                        try {


                            TagStrmApiResponse response = tagStrmApi.getTagStrmLog(device.getSpotDevId(), "100", "5");

                            if (response.getResponseCode().equals(ApiConstants.CODE_OK)) {

                                for (TagStrm tag : device.getTagStrmList()) {

                                    if (tag.getTagStrmPrpsTypeCd().equals(TagStrmApi.TAGSTRM_DATA)) {


                                        LogStream logStream = new LogStream();
                                        logStream.setTag(tag);

                                        if (response.getLogs() != null) {

                                            ArrayList<Log> logs = new ArrayList<>();


                                            for(Log tmp : response.getLogs()){

                                                if(tmp.getAttributes().get(tag.getTagStrmId()) != null){
                                                    logs.add(tmp);
                                                }

                                            }

                                            if(logs.size() > 0){            //혆재 태그스트림의 데이터가 있어야 logStreams에 추가한다.

                                                /*for(Log tmp : logs) {

                                                    android.util.Log.w(TAG, "TAG ID = " + tag.getTagStrmId() + " | value = " + tmp.getAttributes().get(tag.getTagStrmId()));
                                                }*/

                                                logStream.setLogList(logs);
                                            }
                                            logStreams.add(logStream);

                                        }


                                    }

                                }


                                updateGraph(logStreams);

                            }

                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    //}
                }
            }

        }
    }
}
