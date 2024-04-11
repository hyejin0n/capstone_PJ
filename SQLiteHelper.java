// #### SQLiteHelper.java ####
// DB를 생성 오픈, 버전 업데이트에 대한 내용을 편리하게 도와주는 클래스

package com.itkim.exam.sqliteexam.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper{

    // 저는 나중에 수정할 때를 대비하여 final 선언을 하였지만 굳이 이렇게 안하셔도 괜찮습니다.
    public final String TABLE_NAME = "userinfo";
    public final String User_id = "User_id"; //아이디 PK
    public final String User_name = "User_name"; //이름
    public final String User_phone = "User_phone"; //핸드폰 번호
    public final String User_nick = "User_nick"; //닉네임
    public final String User_pass = "User_pass"; //비밀번호

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Table 생성하는 Query
        // ( if not exists ) 만약 존재하지 않으면 생성
        String create_query = "create table if not exists " + TABLE_NAME + "("
                + User_id + " text primary key, "
                + User_name + " text not null, " // not null을 쓰시지 않으실 때는 이렇게 선언합니다.
                + User_phone + " text not null"
                + User_nick + " text not null"
                + User_pass + "text not null);"; // primary key는+ U 이렇게 선언합니다.

        // 위 Create Query로 Table을 생성해줍니다.
        sqLiteDatabase.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // SQLite에 대해 설정한 버전을 올렸을 때

        // 기존 테이블 Drop 해준 후
        String drop_query = "drop table " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(drop_query);

        // onCreate를 호출해서 Table 다시 생성
        onCreate(sqLiteDatabase);
    }
}
