package com.example.derik.newtecdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.derik.newtecdemo.adapter.ItemDividerHorizontal;
import com.example.derik.newtecdemo.R;
import com.example.derik.newtecdemo.adapter.RecycleViewAdapter;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private Intent targetIntent;
    private ActionBar actionBar;
    private String[] targetNames = new String[]{
            "Storage",
            "Views",
            "MultiMedia",
            "Net",
            "Natives",
            "Others",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);//设置到BaseActivity中的content中
        setTitle("开源框架使用示例");//设置标题
        setBackArrow();//设置返回按钮和点击事件

        ToggleButton actionBarEnable = (ToggleButton) findViewById(R.id.main_action_bar_toggle);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBarEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    actionBar.show();
                } else {
                    actionBar.hide();
                }
            }
        });
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        // 线性排列
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        // 设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDividerHorizontal().setDividerColor(Color.GRAY).setDividerSize(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecycleViewAdapter adapter = new RecycleViewAdapter(targetNames, null);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        targetIntent = new Intent(getBaseContext(), OkHttpTestActivity.class);
                        startActivity(targetIntent);

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == resultCode) {
            Logger.d("a", "b");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Logger.d("home");
                break;
            case R.id.action_edit:
                Logger.d("action_edit");
                break;
            case R.id.action_share:
                Logger.d("action_share");
                break;
            case R.id.action_settings:
                Logger.d("action_settings");
                break;
            default:
                break;
        }
        if (item.getItemId() == android.R.id.home) {
            Logger.d("finish");
            finish();
        }
        return true;
    }

}
