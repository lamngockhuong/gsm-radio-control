package com.ngockhuong.gsm.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ngockhuong.gsm.constant.SettingConstant;

public class SharedPreferencesUtil {
    public void load(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SettingConstant.SETTING_FILE, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            String phone = sharedPreferences.getString(SettingConstant.PHONE, "");
        }
    }
}
