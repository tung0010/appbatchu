package com.example.bai.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT*FROM question")
    List<Question> getAllQuestion();

    @Insert
    void insert(Question question);

}
