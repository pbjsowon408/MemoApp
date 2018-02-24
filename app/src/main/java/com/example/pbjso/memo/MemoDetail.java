package com.example.pbjso.memo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MemoDetail extends Activity implements android.view.View.OnClickListener{

        Button btnDelete, btnSave, btnClose;

        EditText editTextTitle;
        EditText editTextContent;
        EditText editTextDate;

        private int _Memo_Id = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_memo_detail);

            btnDelete = (Button) findViewById(R.id.btnDelete);
            btnSave = (Button) findViewById(R.id.btnSave);
            btnClose = (Button) findViewById(R.id.btnClose);

            editTextTitle = (EditText) findViewById(R.id.editTextTitle);
            editTextContent = (EditText) findViewById(R.id.editTextContent);
            editTextDate = (EditText) findViewById(R.id.editTextDate);

            btnDelete.setOnClickListener(this);
            btnSave.setOnClickListener(this);
            btnClose.setOnClickListener(this);

            _Memo_Id = 0;
            Intent intent = getIntent();
            _Memo_Id =intent.getIntExtra("memo_Id", 0);
            MemoReposit reposit = new MemoReposit(this);
            Memo memo = new Memo();
            memo = reposit.getMemoById(_Memo_Id);

            editTextTitle.setText(memo.title);
            editTextContent.setText(memo.content);
            editTextDate.setText(memo.date);
        }

        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            if (view == findViewById(R.id.btnSave)){
                MemoReposit reposit = new MemoReposit(this);
                Memo memo = new Memo();

                memo.title=editTextTitle.getText().toString();
                memo.content=editTextContent.getText().toString();
                memo.date= editTextDate.getText().toString();

                memo.memo_ID=_Memo_Id;

                if (_Memo_Id==0)
                {
                    _Memo_Id = reposit.insert(memo);

                    Toast.makeText(this,"New Memo Insert",Toast.LENGTH_SHORT).show();
                }else
                {
                    reposit.update(memo);
                    Toast.makeText(this,"Memo updated",Toast.LENGTH_SHORT).show();
                }
            }

            else if (view == findViewById(R.id.btnDelete))
            {
                MemoReposit reposit = new MemoReposit(this);
                reposit.delete(_Memo_Id);
                Toast.makeText(this, "Memo Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }

            else if (view== findViewById(R.id.btnClose))
            {
                finish();
            }
        }
}
