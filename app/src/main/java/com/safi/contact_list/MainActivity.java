package com.safi.contact_list;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbhelper;
    private EditText mEt1,mEt2;
    private Button mAdd,mShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new DatabaseHelper(this);
        mEt1=findViewById(R.id.ETName);
        mEt2=findViewById(R.id.ETPhn);
        mAdd=findViewById(R.id.addbtn);
        mShow=findViewById(R.id.showbtn);

        mAdd.setOnClickListener(this);
        mShow.setOnClickListener(this);
        SQLiteDatabase sqLiteDatabase=dbhelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        String name=mEt1.getText().toString().trim();
        String phn=mEt2.getText().toString().trim();
        if(v.getId()==R.id.addbtn){
            if(name.equals("") && (phn.equals(""))){
                Toast.makeText(getApplicationContext(), "plz enter person name", Toast.LENGTH_SHORT).show();
            }else {
                long rownumber=dbhelper.savedata(name,phn);
                if(rownumber>-1){
                    Toast.makeText(getApplicationContext(), "data is inserted successfully", Toast.LENGTH_SHORT).show();
                    mEt1.setText(" ");
                    mEt2.setText(" ");
                }
            }
        }else if(v.getId()==R.id.showbtn){
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
    }
}
