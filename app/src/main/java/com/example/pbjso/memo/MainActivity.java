package com.example.pbjso.memo;

import android.app.ListActivity;
import android.content.Intent;


import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity  implements android.view.View.OnClickListener{

    TextView memo_Id;
    Button btnWrite, btnList;
    Button btnBack;


    @Override
    public void onClick(View view) {

        if (view== findViewById(R.id.btnWrite)){

            Intent intent = new Intent(this,MemoDetail.class);
            intent.putExtra("memo_Id",0);
            startActivity(intent);


        }

        else if (view == findViewById(R.id.btnList))
            {
            MemoReposit reposit = new MemoReposit(this);

            ArrayList<HashMap<String, String>> memoList = reposit.getMemoList();
            if(memoList.size()!=0) {
                ListView lw = getListView();
                lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        memo_Id = (TextView) view.findViewById(R.id.memo_Id);
                        String memoId = memo_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),MemoDetail.class);
                        objIndent.putExtra("memo_Id", Integer.parseInt( memoId));
                        startActivity(objIndent);

                    }
                });
                ListAdapter adapter = new SimpleAdapter( MainActivity.this,memoList, R.layout.view_memo,
                        new String[] { "id","title","date"}, new int[] {R.id.memo_Id, R.id.memo_title, R.id.memo_date});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No Memo!",Toast.LENGTH_SHORT).show();
            }

        }

        else if (view == findViewById(R.id.btnBack))
        {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(this);

        btnList = (Button) findViewById(R.id.btnList);
        btnList.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

    }


}