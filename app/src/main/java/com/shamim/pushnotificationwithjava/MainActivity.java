package com.shamim.pushnotificationwithjava;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askNotificationPermission();

        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }

                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        String title;
        String massage;
        String url;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            massage = extras.getString("massage");
            url = extras.getString("image");
            Toast.makeText(this, ""+title+"--------"+massage, Toast.LENGTH_SHORT).show();


            Log.d("Data",""+title+"--------"+massage);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String title;
        String massage;
        String url;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            massage = extras.getString("massage");
            url = extras.getString("image");
            Toast.makeText(this, ""+title+"--------"+massage, Toast.LENGTH_SHORT).show();


            Log.d("Data",""+title+"--------"+massage);
        }
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
}
//AAAAlFL25DI:APA91bFMX6MtUkkB45yahNIzY2xQ4rMz93oWZwVkG4cAAKdHUPaSgw_IAYbK6p8_g2HZBzua_WbXqrZxxchAtxdWHWNqGpE7gSuef6MZo5NxvR9UydrtwP--ikaDGg_n_OxXLOGabRa9