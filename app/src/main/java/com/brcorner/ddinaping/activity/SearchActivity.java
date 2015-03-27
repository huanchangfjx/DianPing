package com.brcorner.ddinaping.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.brcorner.ddinaping.R;

public class SearchActivity extends BaseActivity {

    @Override
    public void initView() {

    }

    @Override
    public void changeView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
    }

    public void sheep(View view)
    {
        Intent intent = new Intent(this,SearchResultActivity.class);
        startActivity(intent);
    }

    public void coffee(View view)
    {
        Intent intent = new Intent(this,ItemActivity.class);
        startActivity(intent);
    }
}
