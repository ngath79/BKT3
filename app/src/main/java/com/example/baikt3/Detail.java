package com.example.baikt3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Detail extends AppCompatActivity {

    int thuchien;
    CardView btnTinh,btnLuu;
    EditText so1, so2, kqua, thongtin;
    TextView tt;
    int s1;
    long kq,kq2;
    float kq1;
    boolean check=false;
    String loai;
    SharedPreferences sharedPreferences;
    Database db;
    List<LuuViDu> dsPhepTinh= new ArrayList<LuuViDu>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnTinh=(CardView)findViewById(R.id.btnTinh);
        so1=(EditText)findViewById(R.id.soThuNhat);
        kqua=(EditText)findViewById(R.id.ketqua);
        tt=(TextView)findViewById(R.id.thongtin);
        btnLuu=(CardView) findViewById(R.id.btnLuu);
        sharedPreferences = getSharedPreferences("SaveData",MODE_PRIVATE);

        db = new Database(this,"NUOC.sqlite",null,1);
        //db.QueryData("drop table if exists luuPhepTinh");
        db.QueryData("create table IF NOT EXISTS luuViDu(loai nVARCHAR(100),loaichu nVARCHAR(100),so1 nVARCHAR(100),ketqua nvarchar(100))");

        ListView lvLop = (ListView) findViewById(R.id.lvLuuPhepTinh);
        getDuLieu();
        ArrayAdapter<LuuViDu> lopArrayAdapter = new ArrayAdapter<>(Detail.this, android.R.layout.simple_list_item_1,dsPhepTinh);
        lvLop.setAdapter(lopArrayAdapter);

        thuchien=getIntent().getIntExtra("thuchien",0);
        switch (thuchien){
            case 0:
                loai="anh";
                break;
            case 1:
                loai="vietnam";
                break;
            case 2:
                loai="phap";
                break;
            case 3:
                loai="nga";
                break;
            case 4:
                loai="nh???t";
                break;
            case 5:
                loai="hanquoc";
                break;
            case 6:
                loai="Thailan";
                break;
        }
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!check){
                    Toast.makeText(Detail.this, "Nh??p tr?????c l??u sau", Toast.LENGTH_SHORT).show();
                }else{
                    if (thuchien!=3 && thuchien != 4 && thuchien !=6)
                        db.QueryData("insert into luuViDu(loai,loaichu,so1,ketqua) values('"+thuchien+"','"+loai+"','"+Integer.parseInt(so1.getText().toString())+"','"+kq+"')");
                    else
                        db.QueryData("insert into luuViDu(loai,loaichu,so1,so2,ketqua) values('"+thuchien+"','"+loai+"','"+Integer.parseInt(so1.getText().toString())+"','"+0+"','"+kq1+"')");
                    getDuLieu();
                    lopArrayAdapter.notifyDataSetChanged();
                    check=false;
                }
            }
        });

        if (thuchien!=4 && thuchien != 6){
            btnTinh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (so1.getText().toString().isEmpty() || so2.getText().toString().isEmpty()){
                        Toast.makeText(Detail.this, "Vui l??ng nh???p ????? th??ng tin", Toast.LENGTH_SHORT).show();
                    }else{
                        check=true;
                        s1= Integer.parseInt(so1.getText().toString());
                        switch (thuchien){
                            case 0:
                                tt.setText("N?????c Anh");
                                kq=s1;
                                break;
                            case 1:
                                tt.setText("N?????c Vi???t Nam");
                                kq=s1;
                                break;
                            case 2:
                                tt.setText("N?????c Ph??p");
                                kq=s1;
                                break;
                            case 3:
                                tt.setText("N?????c Nga");
                                kq= s1;
                                break;
                            case 4:
                                break;
                            case 5:
                                tt.setText("N?????c Nh???t B???n");
                                kq=s1;
                                break;
                            default: break;

                        }
                    }
                }
            });
        }else{
            so2.setVisibility(View.GONE);
            btnTinh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (so1.getText().toString().isEmpty()){
                        Toast.makeText(Detail.this, "Vui l??ng nh???p ????? th??ng tin", Toast.LENGTH_SHORT).show();
                    }else{
                        check=true;
                        if (thuchien==4){
                            tt.setText("N?????c H??n Qu???c");
                            s1= Integer.parseInt(so1.getText().toString());
                            System.out.println(s1);
                            kq1= (float) Math.log(s1);
                        }else{
                            tt.setText("N?????c Th??i Lan");
                            s1= Integer.parseInt(so1.getText().toString());
                            System.out.println(s1);
                            kq1= (float) Math.sqrt(s1);
                        }

                    }

                }
            });
        }
    }
    @SuppressLint("Range")
    private void getDuLieu() {
        dsPhepTinh.clear();
        Cursor cursor = db.GetData("select * from luuViDu");
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                int loai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("loai")));
                String loaichu= cursor.getString(cursor.getColumnIndex("loaichu"));
                int so1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex("so1")));
                double kq= Double.parseDouble(cursor.getString(cursor.getColumnIndex("ketqua")));
                dsPhepTinh.add(new LuuViDu(loai,loaichu,so1,kq));
            }
        }
    }
}