package com.bats.admin;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bats.admin.fragments.Contatos;
import com.bats.admin.fragments.Home;
import com.bats.admin.fragments.Settings;
import com.bats.admin.fragments.Share;
import com.bats.admin.fragments.Songs;
import com.bats.admin.fragments.Video;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private final boolean doubleBackToExitPressedOnce = false;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!checkPermission()) {
            requestPermision();
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, new Home())
                .commit();
        initComponents();
        openMenu();
    }

    public void initComponents() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.tollbar);
        navigationView = findViewById(R.id.navigation_view);
    }

    public void openMenu() {
        toolbar.setNavigationOnClickListener((View) -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.home:
                        replaceFragment(new Home());
                        break;
                    case R.id.video:
                        replaceFragment(new Video());
                        break;
                    case R.id.song:
                        replaceFragment(new Songs());
                        break;
                    case R.id.share:
                        replaceFragment(new Share());
                        break;
                    case R.id.contatos:
                        replaceFragment(new Contatos());
                        break;
                    case R.id.settings:
                        replaceFragment(new Settings());
                        break;
                    case R.id.exit:
                        exitApp();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int inter = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        return result == PackageManager.PERMISSION_GRANTED && inter == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermision() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getBaseContext(), "permissao necessaria para utilizar o app", Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }

    private void exitApp(){
        SharedPreferences preferences = getSharedPreferences(getString(R.string.key_login), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("username");
        editor.remove("pass");
        editor.commit();
        finish();
    }

//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//        this.doubleBackToExitPressedOnce = true;
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce = false;
//            }
//        }, 2000);
//    }
}