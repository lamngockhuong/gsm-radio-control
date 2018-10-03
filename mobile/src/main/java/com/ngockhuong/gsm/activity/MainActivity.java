package com.ngockhuong.gsm.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;

import com.ngockhuong.gsm.R;
import com.ngockhuong.gsm.exception.PermissionException;
import com.ngockhuong.gsm.fragment.HomeFragment;
import com.ngockhuong.gsm.util.Dialog;
import com.ngockhuong.gsm.util.PermissionsUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // show alert dialog explaining the user why the SMS permission is going to be requests
        Dialog.getInstance().showRequestPermissionsInfoDialog(this,
                R.string.permission_request_permissions_info_dialog_title,
                R.string.permission_request_permissions_info_dialog_message);

        init();
        getWidgets();
        setWidgets();
    }

    private void init() {
        fragmentInit();
    }

    private void fragmentInit() {
        // set the fragment initially
        HomeFragment homeFragment = HomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
    }

    private void setWidgets() {
        // toolbar
        setupToolbar(R.id.toolbar, getResources().getString(R.string.app_name), R.color.colorPink, R.color.colorWhiteTrans, R.drawable.ic_burger);

        // action bar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void getWidgets() {
        drawer = findViewById(R.id.drawer_layout);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionsUtil.PERMISSION_ALL: {
                if (grantResults.length > 0) {
                    List<Integer> indexesOfPermissionsNeededToShow = new ArrayList<>();

                    for(int i = 0; i < permissions.length; ++i) {
                        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                            indexesOfPermissionsNeededToShow.add(i);
                        }
                    }

                    int size = indexesOfPermissionsNeededToShow.size();
                    if(size != 0) {
                        int i = 0;
                        boolean isPermissionGranted = true;

                        while(i < size && isPermissionGranted) {
                            isPermissionGranted = grantResults[indexesOfPermissionsNeededToShow.get(i)] == PackageManager.PERMISSION_GRANTED;
                            i++;
                        }

                        if(!isPermissionGranted) {
                            Dialog.getInstance().showRequestPermissionsConfirmationDialog(this,
                                R.string.permission_request_permissions_confirmation_dialog_title,
                                R.string.permission_request_permissions_confirmation_dialog_message);
                        }
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
