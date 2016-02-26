package com.cc.digital;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cc.digital.db.MySqliteOpenHelper;
import com.cc.digital.inter.FinalValue;
import com.cc.digital.utils.MySildeOnTouchListenew;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TieActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView notes_list;
    private Button btn_new_note;
    private MySqliteOpenHelper helper;// 数据库连接对象
    private SQLiteDatabase database;// 数据库
    private Cursor cursor;// 游标
    private List<HashMap<String, String>> data;// 数据源
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tie);
        init();//初始化
        initdata();//初始化数据库
        aboutList();
        if (!cursor.moveToFirst()) {
        } else {
            dataDB();
        }

    }
    private void dataDB() {
        cursor = database.query(FinalValue.TB_TIE, null, null, null, null,
                null, null);
        while (cursor.moveToNext()) {
            String content = cursor.getString(cursor.getColumnIndex("content"));
            HashMap<String, String> map = new HashMap<>();
            map.put("content", content);
            data.add(map);
        }
        adapter.notifyDataSetChanged();
    }

    private void init() {
        LinearLayout ll_tieid=(LinearLayout) findViewById(R.id.ll_tieid);
        ll_tieid.setOnTouchListener(new MySildeOnTouchListenew(this));
        notes_list = (ListView) findViewById(R.id.notes_list);
        btn_new_note=(Button) findViewById(R.id.btn_new_note);
        ImageView iv_draftguideid=(ImageView) findViewById(R.id.iv_draftguideid);
        iv_draftguideid.setOnClickListener(this);
        btn_new_note.setOnClickListener(this);
    }

    private void aboutList() {
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this, data, R.layout.tie_item,
                new String[] { "content" }, new int[] {
                R.id.tv_item_tie });
        notes_list.setAdapter(adapter);// 绑定适配器
        notes_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> itemcontent = data.get(position);
                String content = itemcontent.get("content");
                Intent intent=new Intent(TieActivity.this,NewTieActivity.class);
                intent.putExtra("tag","2");
                intent.putExtra("position",position);
                intent.putExtra("content", content);
                startActivityForResult(intent, 200);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
        notes_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TieActivity.this);
                builder.setTitle("删除内容");// 对话框标题
                builder.setMessage("你确定要删除吗");// 对话框内容
                DialogButtonOnClickListener listener = new DialogButtonOnClickListener(position);
                builder.setNegativeButton("取消", listener);
                builder.setPositiveButton("确定", listener);
                builder.create().show();
                return true;
            }
        });
    }

    private class DialogButtonOnClickListener implements DialogInterface.OnClickListener {
        private int position;
        public DialogButtonOnClickListener(int position){
            this.position=position;
        }
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    HashMap<String, String> map = data.get(position);
                    String content = map.get("content");
                    database.delete(FinalValue.TB_TIE, "content=?", new String[] {
                            content + "" });
                    data.remove(position);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
    /**
     * 初始化数据库
     */
    private void initdata() {
        helper = new MySqliteOpenHelper(this, FinalValue.DB_NAME, null,
                FinalValue.DB_VERSION);
        database = helper.getReadableDatabase();
        cursor = database.query(FinalValue.TB_TIE, null, null, null, null,
                null, null);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_note:
            Intent intent = new Intent(TieActivity.this, NewTieActivity.class);
            intent.putExtra("tag", "1");
            startActivityForResult(intent, 200);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.iv_draftguideid:
                break;
        }}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent d) {
            String string = d.getStringExtra("name");
             String tag = d.getStringExtra("tag");
             HashMap<String,String> map = new HashMap<>();
            map.put("content", string);
        if(tag.equals("1")){data.add(map);}
            if(tag.equals("2")){
                int pos = d.getIntExtra("position",0);
                data.set(pos,map);
            }
            adapter.notifyDataSetChanged();
    }
}
