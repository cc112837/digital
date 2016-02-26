package com.cc.digital.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PENG on 2015/11/11 0011.
 */
public class CommunityHomeDB extends SQLiteOpenHelper {
    private static final String DATABASENAME = "CommunityHome.db";
    private static final int VERSION = 1;

    public CommunityHomeDB(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table communityhome(_id integer primary key autoincrement,name text,posts text)");

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "尾巴主版");
        contentValues.put("posts", "125694");
        if (contentValues == null){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }else{
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        }
//        db.insert("communityhome", null, contentValues);
//
//        ContentValues contentValues1 = new ContentValues();
//        contentValues.put("name", "甩甩尾巴");
//        contentValues.put("posts", "125694");
//        db.insert("communityhome", null, contentValues1);
//
//        ContentValues contentValues2 = new ContentValues();
//        contentValues.put("name", "尾巴认证");
//        contentValues.put("posts", "125694");
//        db.insert("communityhome", null, contentValues2);
//
//        ContentValues contentValues3 = new ContentValues();
//        contentValues.put("name", "尾巴良品");
//        contentValues.put("posts", "125694");
//        db.insert("communityhome", null, contentValues3);
//
//        ContentValues contentValues4 = new ContentValues();
//        contentValues.put("name", "纠结尾巴");
//        contentValues.put("posts", "125694");
//        db.insert("communityhome", null, contentValues4);
//
//        ContentValues contentValues5 = new ContentValues();
//        contentValues.put("name", "站务交流");
//        contentValues.put("posts", "125694");
//        db.insert("communityhome", null, contentValues5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
