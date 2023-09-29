package com.example.ttdn1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imgToggleMusic;
    private boolean isMusicPlaying = false;
    private SeekBar seekBar;
    private MusicService musicService; // Đối tượng dịch vụ để truy cập thời gian nhạc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgToggleMusic = findViewById(R.id.btnToggleMusic);
        seekBar = findViewById(R.id.seekBar);

        // Bắt sự kiện khi người dùng nhấn vào ImageView
        imgToggleMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMusicPlaying) {
                    // Bật nhạc
                    Toast.makeText(MainActivity.this, "Bật Nhạc", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    startService(intent);
                    isMusicPlaying = true;
                    imgToggleMusic.setImageResource(R.drawable.pause); // Thay thế bằng biểu tượng bật nhạc
                } else {
                    // Tắt nhạc
                    Toast.makeText(MainActivity.this, "Tắt Nhạc", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    stopService(intent);
                    isMusicPlaying = false;
                    imgToggleMusic.setImageResource(R.drawable.play); // Thay thế bằng biểu tượng tắt nhạc
                }
            }
        });

        // Thiết lập sự kiện thay đổi của SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (musicService != null && fromUser) {
                    int newPosition = (musicService.getDuration() * progress) / 100;
                    musicService.seekTo(newPosition);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Người dùng bắt đầu kéo SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Người dùng kết thúc kéo SeekBar
            }
        });
    }

    // Phương thức này được gọi từ Service để kết nối với SeekBar
    public void setMusicService(MusicService service) {
        musicService = service;
        if (musicService != null) {
            seekBar.setMax(musicService.getDuration());
        }
    }
}
