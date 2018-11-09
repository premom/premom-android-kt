package com.kt.iot.mobile.push;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.kt.gigaiot_sdk.PushApi;
import com.kt.iot.mobile.ui.fragment.event.list.EventListFragment;
import com.kt.iot.mobile.ui.fragment.rawdata.RawdataGraphWebViewFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

/**
 * Created by ceoko on 15. 6. 19..
 */
public class GCMIntentService extends IntentService {

    private static final String TAG = GCMIntentService.class.getSimpleName();

    private static PowerManager.WakeLock sWakeLock;
    private static final Object LOCK = GCMIntentService.class;

    /**
     * Constructor
     */
    public GCMIntentService(){
        super("GigaIot");
    }

    /**
     * Constructor
     * @param name
     */
    public GCMIntentService(String name){
        super("GigaIot");
    }

    /**
     * IntentService 시작
     * @param context Android Application Context
     * @param intent Intent
     */
    static void runIntentInService(Context context, Intent intent){
        Log.d(TAG, "GCMIntentService.runIntentInService() GCM runIntentInService");
        synchronized (LOCK) {
            if(sWakeLock == null){
                PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                sWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Push_WakeLock");
            }
            sWakeLock.acquire();
            intent.setClassName(context, GCMIntentService.class.getName());
            context.startService(intent);
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "GCMIntentService.onHandleIntent()  GCM onHandleIntent");
        Log.d(TAG, "intent : " + intent.toString());
        Log.d(TAG, "intent : " + intent);

        try{
            final Context context = getApplicationContext();
            String action = intent.getAction();
            Log.d(TAG, "action : " + action);

            if(action.equals("com.google.android.c2dm.intent.REGISTRATION")){

                handleRegistration(context, intent);

            } else if(action.equals("com.google.android.c2dm.intent.RECEIVE")){


				/*new Handler(getMainLooper()).post(new Runnable() {

					@Override
					public void run() {

						Toast.makeText(GCMIntentService.this, "GCM push received!!", Toast.LENGTH_LONG).show();

					}
				});*/

                handleMessage(context, intent);
            }
        } finally{
            synchronized (LOCK) {
                sWakeLock.release();
            }
        }
    }

    /**
     * GCM Registration ID 수신
     * @param context Android Application Context
     * @param intent Intent
     */
    private void handleRegistration(Context context, Intent intent){
        Log.d(TAG, "GCMIntentService.handleRegistration()  GCM handleRegistration");

        String regirationId = intent.getStringExtra("registration_id");
        String unregistered = intent.getStringExtra("unregistered");
        String error = intent.getStringExtra("error");


        Log.i(TAG, "registerId : " + regirationId);
        Log.i(TAG, "unregistered : " + unregistered);
        Log.i(TAG, "error : " + error);

        if(regirationId != null && regirationId.length() > 0){

        }
        else if(unregistered != null && unregistered.length() > 0){

            Log.i(TAG, "unregistered = " + unregistered);

        }
        else if(error != null && error.length() > 0){

        }
        else{

        }
    }

    /**
     * 수신된 메시지를 처리
     * @param context Android Application Context
     * @param intent Intent
     */
    private void handleMessage(Context context, Intent intent){
        Log.d(TAG, "GCMIntentService.handleMessage()  GCM handleMessage");

        Set<String> keys = intent.getExtras().keySet();
        for(String key : keys){
            Log.d(TAG, "GCM Key : " + key);
            Log.d(TAG, "GCM Value : " + intent.getExtras().getString(key));
        }
        sendIntent(context, intent);
    }

    /**
     * @param context Android Application Context
     * @param intent Push 메시지를 수신한 Intent
     */
    private void sendIntent(Context context, Intent intent) {
        Log.d(TAG, "GCMIntentService.sendIntent()  #### GCM sendIntent");

        String recvMsg = intent.getStringExtra("message");

        String type = "";
        String message = "";

        try {
            JSONObject objJson = new JSONObject(recvMsg);

            type = objJson.getString("type");
            message = objJson.getString("message");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "GCMIntentService.sendIntent() type = " + type + " message = " + message);

        if(type.equals(PushApi.PUSH_MSG_TYPE_COLLECT)){

            Log.d(TAG, "GCMIntentService.sendIntent()  TYPE = PushApi.PUSH_MSG_TYPE_COLLECT message = " + message);

            Intent msgIntent = new Intent(RawdataGraphWebViewFragment.ACTION_RECIVER_RAWDATA);
            msgIntent.addCategory(RawdataGraphWebViewFragment.CATEGORY_RECIVER_RAWDATA);

            msgIntent.putExtra("msg", message);

            sendBroadcast(msgIntent);

        }else if(type.equals(PushApi.PUSH_MSG_TYPE_OUTBREAK)){

            Log.d(TAG, "GCMIntentServicef.sendIntent()  TYPE = PushApi.PUSH_MSG_TYPE_OUTBREAK message = " + message);

            Intent msgIntent = new Intent(EventListFragment.ACTION_RECIVER_EVENT);
            msgIntent.addCategory(EventListFragment.CATEGORY_RECIVER_EVENT);

            msgIntent.putExtra("msg", message);

            sendBroadcast(msgIntent);

        }


    }

}
