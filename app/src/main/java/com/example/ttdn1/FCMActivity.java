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
        btn= findViewById(R.id.btn);


        /*
            1: đăng ký trên console.firebase.google.com (enable cloud messaging để lấy server key)
            2: thêm thư viện và google.json và apply
            3: thêm thư vien implementation ("com.google.firebase:firebase-messaging:22.0.0")
            4: tạo FCMNotifierd để gửi api
            5: tao service để nhận kq từ api
            6: xu ly thong bao

        */


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // FCMNotifier để gửi API thông báo
                FCMNotifier fcmNotifier = new FCMNotifier();
                String deviceId = "DEVICE_ID_OF_THE_TARGET_DEVICE"; // Thay thế bằng định danh thiết bị cụ thể
                String title = "Tiêu đề thông báo";
                String message = "Nội dung thông báo: Đi học đi ông cháu ơi";

                // lay token fcm
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                return;
                            }
                            String token = task.getResult();
                            Log.d(">>>>>>>", "token ne:" + token);
                            // Lấy token và gưi thông báo
                            fcmNotifier.sendNotification(token, title, message);
                        });



                //createNotificationChannel2();
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Thông báo";
            String description = "Thông báo";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("\uD83D\uDCE3\uD83D\uDCE3\uD83D\uDCE3 Đi học nào kẻo kẹt xe!!!");
        bigTextStyle.bigText("Môn học Android Networking sẽ bắt đầu vào CA 5. Bạn hãy nhớ nhé!");
        // Tạo thông báo bằng cách sử dụng NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_none_24) // Icon cho thông báo
                .setContentTitle("\uD83D\uDCE3\uD83D\uDCE3\uD83D\uDCE3 Đi học nào kẻo kẹt xe!!!") // Tiêu đề của thông báo
                .setContentText("Môn học Android Networking sẽ bắt đầu vào CA 5. Bạn hãy nhớ nhé!") // Nội dung của thông báo
                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Hiển thị thông báo
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(/* ID thông báo */ 1, builder.build());
    }
    private void createNotificationChannel2() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Thông báo";
            String description = "Thông báo";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Intent cho màn hình đầy đủ (full screen) khi thông báo được bật lên
        Intent fullScreenIntent = new Intent(this, FirebaseStorageActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("\uD83D\uDCE3\uD83D\uDCE3\uD83D\uDCE3 Đi học nào kẻo kẹt xe!!!");
        bigTextStyle.bigText("Môn học Android Networking sẽ bắt đầu vào CA 5. Bạn hãy nhớ nhé!");

        // Tạo thông báo bằng cách sử dụng NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_none_24) // Icon cho thông báo
                .setContentTitle("\uD83D\uDCE3\uD83D\uDCE3\uD83D\uDCE3 Đi học nào kẻo kẹt xe!!!") // Tiêu đề của thông báo
                .setContentText("Môn học Android Networking sẽ bắt đầu vào CA 5. Bạn hãy nhớ nhé!") // Nội dung của thông báo
                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Đặt mức độ ưu tiên là HIGH
                .setFullScreenIntent(fullScreenPendingIntent, true);

        // Hiển thị thông báo
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(/* ID thông báo */ 1, builder.build());
    }

}