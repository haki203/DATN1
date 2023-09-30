package com.example.ttdn1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;

public class FCMActivity extends AppCompatActivity {
    Button btn;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmactivity);
//        btn= findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // FCMNotifier để gửi API thông báo
//                FCMNotifier fcmNotifier = new FCMNotifier();
//                String deviceId = "DEVICE_ID_OF_THE_TARGET_DEVICE"; // Thay thế bằng định danh thiết bị cụ thể
//                String title = "Tiêu đề thông báo";
//                String message = "Nội dung thông báo: Đi học đi ông cháu ơi";
//
//                // lay token fcm
//                FirebaseMessaging.getInstance().getToken()
//                        .addOnCompleteListener(task -> {
//                            if (!task.isSuccessful()) {
//                                return;
//                            }
//                            String token = task.getResult();
//                            Log.d(">>>>>>>", "token ne:" + token);
//                            // Lấy token và gưi thông báo
//                            fcmNotifier.sendNotification(token, title, message);
//                        });
//
//
//
//                //createNotificationChannel2();
//            }
//        });
    }

}