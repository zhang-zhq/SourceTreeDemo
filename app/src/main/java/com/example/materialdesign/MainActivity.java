package com.example.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    DrawerLayout layout;
    NavigationView navigationView;
    List<Fruit> list = new ArrayList<>();
    private Fruit [] fruits = {new Fruit("apple",R.drawable.apple),
            new Fruit("watermelon",R.drawable.watermelon),
            new Fruit("pear",R.drawable.pear),
            new Fruit("mango",R.drawable.mango),
            new Fruit("grape",R.drawable.grape),
            new Fruit("strawberry",R.drawable.apple),
            new Fruit("apple",R.drawable.strawberry),
            new Fruit("banana",R.drawable.banana),
            new Fruit("cherry",R.drawable.cherry),};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "你点击了悬浮按钮", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "删除", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "你点击了悬浮按钮", Toast.LENGTH_SHORT).show();
                    }

                }).show();
            }
            });
        //在获取ActionBar实例
         ActionBar bar = getSupportActionBar();
         if(bar != null){
             bar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
             bar.setHomeAsUpIndicator(R.drawable.ic_menu);//改变导航栏图标样式
         }
         navigationView.setCheckedItem(R.id.navCall);//菜单电话选项设置为默认选中
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 layout.closeDrawers();//关闭滑动菜单
                 return true;
             }
         });
         initFruit();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this,2 );
        recyclerView.setLayoutManager(manager);
        FruitAdapter adpater = new FruitAdapter(list);
        recyclerView.setAdapter(adpater);
    }

    private void initFruit() {
        list.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            list.add(fruits[index]);
        }
    }

    //在给导航栏添加一个点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //点击后打开滑动菜单
                layout.openDrawer(GravityCompat.START);

        }
        return super.onOptionsItemSelected(item);
    }
}