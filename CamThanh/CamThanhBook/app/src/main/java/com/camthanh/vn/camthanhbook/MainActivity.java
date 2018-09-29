package com.camthanh.vn.camthanhbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.camthanh.vn.camthanhbook.category.CategoryFragment;
import com.camthanh.vn.camthanhbook.favorite.FavoriteFragment;
import com.camthanh.vn.camthanhbook.home.HomeFragment;
import com.camthanh.vn.camthanhbook.profile.ProfileFragment;
import com.camthanh.vn.camthanhbook.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    final FragmentManager fm = getSupportFragmentManager();
    final FavoriteFragment fragmentFavorite = new FavoriteFragment();
    final HomeFragment fragmentHome = new HomeFragment();
    final CategoryFragment fragmentCategory = new CategoryFragment();
    final ProfileFragment fragmentProfile = new ProfileFragment();
    Fragment active = fragmentFavorite;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_favorite:
                    mTextMessage.setText(R.string.title_favorite);
                    switchToFragment(fragmentFavorite);
//                    fm.beginTransaction().hide(active).show(fragmentFavorite).commit();
                    active = fragmentFavorite;
                    return true;
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    switchToFragment(fragmentHome);
//                    fm.beginTransaction().hide(active).show(fragmentHome).commit();
                    active = fragmentHome;
                    return true;
                case R.id.navigation_category:
                    mTextMessage.setText(R.string.title_category);
                    switchToFragment(fragmentCategory);
//                    fm.beginTransaction().hide(active).show(fragmentCategory).commit();
                    active = fragmentCategory;
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    switchToFragment(fragmentProfile);
//                    fm.beginTransaction().hide(active).show(fragmentProfile).commit();
                    active = fragmentProfile;
                    return true;
            }
            return false;
        }
    };

    public void switchToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_center_container, fragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        fm.beginTransaction().add(R.id.main_center_container, fragmentProfile, "4").hide(fragmentProfile).commit();
//        fm.beginTransaction().add(R.id.main_center_container, fragmentCategory, "3").hide(fragmentCategory).commit();
//        fm.beginTransaction().add(R.id.main_center_container, fragmentHome, "2").hide(fragmentHome).commit();
//        fm.beginTransaction().add(R.id.main_center_container, fragmentFavorite, "1").commit();
    }
}
