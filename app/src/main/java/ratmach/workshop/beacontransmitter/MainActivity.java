package ratmach.workshop.beacontransmitter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity{
    protected static final String TAG = "MonitoringActivity";
    private BeaconManager beaconManager;
    TextView status = null;
    private Region region;

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    private HashSet<String> discoveredMacs = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        status = (TextView) findViewById(R.id.status);
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                StringBuffer b = new StringBuffer();
                b.append("სულ:");
                b.append(String.valueOf(list.size()));
                for (Beacon beacon: list){
                    b.append("\n - \n");
                    b.append(beacon.getProximityUUID());
                    b.append("\n");
                    b.append(beacon.getMacAddress());

                    b.append("\n ~ ");
                    b.append(Math.pow(10, (beacon.getMeasuredPower() - beacon.getRssi()) / 20.0f));
                    b.append("მეტრში \n");
                    if(!discoveredMacs.contains(beacon.getMacAddress().toHexString())){
                        StringBuffer c = new StringBuffer();
                        c.append("თქვენგან ");
                        c.append(beacon.getRssi() / beacon.getMeasuredPower());
                        c.append(" მეტრში აღმოჩენილია ბიქონი");
                        showNotification("ვხედავ ბიქონს", c.toString());
                        discoveredMacs.add(beacon.getMacAddress().toHexString());
                    }
                }
                status.setText(b.toString());
                Log.d("discovered", region.toString());
            }
        });
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
        beaconManager.startRanging(region);
    }

    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Override
    public void onResume(){
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);

        super.onPause();
    }
}
