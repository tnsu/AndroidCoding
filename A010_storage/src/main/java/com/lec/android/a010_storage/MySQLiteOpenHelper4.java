package com.lec.android.a010_storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//SQLiteOpenHelper
// 안드로이드에서 SQLite3 데이터베이스를 좀더 쉽게(?) 사용할수 있도록 제공되는 클래스
public class MySQLiteOpenHelper4 extends SQLiteOpenHelper {
    public MySQLiteOpenHelper4(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("myapp","SQLiteOpenHelper 생성");
    }

    // 최초의 데이터베이스가 없는경우 데이터베이스 생성을 위해 호출되는 콜백
    // 주로 DDL등 테이블을 생성하는 코드 작성
    // 앱종료하고 다시가동하면 onCreate 호출하지 않음
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("myapp", "SQLiteOpenHelper] onCreate() 호출");

        String sql = "CREATE TABLE student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, age INTEGER, address TEXT)";

        db.execSQL(sql);

    }//end onCreate

    // 데이터베이스의 버전이 바뀌였을때 호출되는 콜백 메소드
    // '버전'이 바뀌었을때 기존에 설치 운영되고 있는 데이터베이스로 어떻게 변경할 것인지 작성
    // 각 버전의 변경 내용물들을 버전마다 작성해야함
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("myapp","SQLiteOpenHelper] onUpgrade 호출:"+ oldVersion + "->" + newVersion);

        // 기존의 테이블 없에고 다시만들기 (예를들어)
        String sql = "DROP TABLE IF EXISTS student"; // 기존 테이블 삭제
        // IF EXISTS(테이블이 있으면 삭제해라) 오라클에서는 사용x 마이에스큐엘 가능
        db.execSQL(sql);
        onCreate(db); // 테이블 다시 생성

    }//end onUpgrade
}
