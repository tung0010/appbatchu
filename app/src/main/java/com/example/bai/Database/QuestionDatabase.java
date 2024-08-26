package com.example.bai.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Question.class},version=1)
public abstract class QuestionDatabase extends RoomDatabase {
public abstract QuestionDao questionDao();
public static QuestionDatabase getInstance(Context context)
{
    return Room.databaseBuilder(context,QuestionDatabase.class,"my-database").allowMainThreadQueries().build();
}
}
