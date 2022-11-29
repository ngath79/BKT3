package com.example.baikt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Nuoc> items = new ArrayList<>();
    Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listPhepTinh);

        items.add(new Nuoc("Nước Anh",R.drawable.anh)) ;
        items.add(new Nuoc("Nước Việt Nam",R.drawable.vietnam)) ;
        items.add(new Nuoc("Nước Pháp",R.drawable.phap)) ;
        items.add(new Nuoc("Nước Nga",R.drawable.nga)) ;
        items.add(new Nuoc("Nước Nhật Bản",R.drawable.nhat));
        items.add(new Nuoc("Nước Hàn Quốc",R.drawable.hanquoc));
        items.add(new Nuoc("Nước Thái Lan",R.drawable.thai));


        adapter = new Adapter(MainActivity.this,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Detail.class);
                intent.putExtra("thuchien",i);
                startActivity(intent);
            }
        });

    }
}