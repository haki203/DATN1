package com.example.ttdn1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ttdn1.adapter.AdapterViewPager;
import com.example.ttdn1.fragments.HomeFragment;
import com.example.ttdn1.fragments.LikeFragment;
import com.example.ttdn1.fragments.MusicFragment;
import com.example.ttdn1.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ArrayList<Fragment> arrayList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.pagerMain);
        bottomNavigationView = findViewById(R.id.bottomNavigate);
        arrayList.add(new HomeFragment());
        arrayList.add(new MusicFragment());
        arrayList.add(new LikeFragment());
        arrayList.add(new UserFragment());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, arrayList);

        //setAdapter
        viewPager2.setAdapter(adapterViewPager);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.music);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.tym);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.map);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.music:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.tym:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.map:
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
}