package com.safi.contact_list;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ListView mList;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mList=findViewById(R.id.list);
        databaseHelper=new DatabaseHelper(this);
        loaddata();
    }
    public void loaddata(){
        ArrayList<String> list=new ArrayList<>();
        Cursor cursor=databaseHelper.showalldata();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "no data is availbale", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                list.add(cursor.getString(0)+"\t"+cursor.getString(1));
            }
        }
        final ArrayAdapter<String> adapter=new ArrayAdapter<String >(this,R.layout.list_item,R.id.textBox,list);
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedvalue=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "selected value : "+selectedvalue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
