package com.example.ttdn1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingService";
    private static final String CHANNEL_ID = "CHANNEL_ID";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Xử lý thông báo ở đây
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Log.d(TAG, "Notification Message Title: " + remoteMessage.getNotification().getTitle());
        String title=remoteMessage.getNotification().getTitle();
        String content=remoteMessage.getNotification().getBody();
        createNotificationChannel2(title,content);
    }
    private void createNotificationChannel2(String title,String content) {
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
                .setContentTitle(title) // Tiêu đề của thông báo
                .setContentText(content) // Nội dung của thông báo
                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Đặt mức độ ưu tiên là HIGH
                .setFullScreenIntent(fullScreenPendingIntent, true);

        // Hiển thị thông báo
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(/* ID thông báo */ 1, builder.build());
    }

}
