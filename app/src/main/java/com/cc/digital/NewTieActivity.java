package com.cc.digital;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.cc.digital.db.MySqliteOpenHelper;
import com.cc.digital.inter.FinalValue;

public class NewTieActivity extends AppCompatActivity {
    private MySqliteOpenHelper helper;
    private SQLiteDatabase database;
    private EditText et_tiecontentid;
    private String tag;
    private Intent intent;
    private String content;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tie);
        et_tiecontentid = ((EditText) findViewById(R.id.tv_tiecontentid));
        initDB();
        intent = getIntent();
        if(intent !=null){
            tag = intent.getStringExtra("tag");
            if(tag.equals("2")){
                position = intent.getIntExtra("position", 0);
                content = intent.getStringExtra("content");
                et_tiecontentid.setText(content);
            }
        }
    }


    @Override
    public void onBackPressed() {
        ContentValues values = new ContentValues();
            if(tag.equals("1")){
                values.put("content", et_tiecontentid.getText().toString());
                database.insert(FinalValue.TB_TIE, null, values);
            }
            if(tag.equals("2")) {
                values.put("content", et_tiecontentid.getText().toString());
                database.update(FinalValue.TB_TIE, values, "content=?",new String[]{content});
            }
        Intent intent=new Intent();
        intent.putExtra("tag",tag);
        intent.putExtra("position",position);
        intent.putExtra("name",et_tiecontentid.getText().toString());
        setResult(200,intent);
        finish();
        super.onBackPressed();
    }

    private void initDB() {
        helper = new MySqliteOpenHelper(this, FinalValue.DB_NAME, null,
                FinalValue.DB_VERSION);
        database = helper.getReadableDatabase();
    }
}
