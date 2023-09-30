package com.example.ttdn1;

import static com.example.ttdn1.MSapp.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private final IBinder binder = new LocalBinder();
    private int currentPosition = 0;

    public class LocalBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendNotification();
        if (!isPlaying) {
            startMusic();
        } else {
            // Tạm dừng hoặc tiếp tục phát nhạc
            if (mediaPlayer.isPlaying()) {
                pauseMusic();
            } else {
                resumeMusic();
            }
        }
        return START_STICKY;
    }

    private void startMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.musicc);
        mediaPlayer.start();
        isPlaying = true;
    }

    private void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            currentPosition = mediaPlayer.getCurrentPosition();
            stopForeground(true);
        }
    }

    private void resumeMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(currentPosition);
            mediaPlayer.start();
        }
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rectangle19);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this, "tag");
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music)
                .setContentTitle("Ophelia by Steven")
                .setContentText("Ophelia")
                .setSubText("Music App")
                .setLargeIcon(bitmap)
                .addAction(R.drawable.baseline_skip_previous_24,"previous",null)
                .addAction(R.drawable.pause,"play",null)
                .addAction(R.drawable.baseline_skip_next_24,"next",null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1))
                       // .setMediaSession(mediaSessionCompat.getSessionToken()))
                .build();
        startForeground(1, notification);
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    public int getCurrentPosition() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            isPlaying = false;
        }
    }
}
