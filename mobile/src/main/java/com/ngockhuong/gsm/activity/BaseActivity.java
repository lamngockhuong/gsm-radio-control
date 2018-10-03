package com.ngockhuong.gsm.activity;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ngockhuong.gsm.R;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;

    protected void setupToolbar(int toolbarId, String title, @ColorRes int titleColor, @ColorRes int bgColor, @DrawableRes int burger) {
        toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setBackgroundColor(getResources().getColor(bgColor));
        setSupportActionBar(toolbar);
        TextView tvPageTitle = toolbar.findViewById(R.id.tv_title);
        tvPageTitle.setText(title);
        tvPageTitle.setTextColor(getResources().getColor(titleColor));
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(burger);
    }
}
