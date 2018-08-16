package com.tianfeng.bootstartdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StartDemo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_demo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start_demo, menu);
        return true;
    }
}
