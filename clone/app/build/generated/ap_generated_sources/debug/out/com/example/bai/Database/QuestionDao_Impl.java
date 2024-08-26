package com.example.bai.Database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class QuestionDao_Impl implements QuestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Question> __insertionAdapterOfQuestion;

  public QuestionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestion = new EntityInsertionAdapter<Question>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Question` (`id`,`image`,`keyword`,`audiogoiy`,`dapan`,`audioketqua`,`audiogioithieu`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Question value) {
        stmt.bindLong(1, value.getId());
        if (value.getImage() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getImage());
        }
        if (value.getKeyword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getKeyword());
        }
        if (value.getAudiogoiy() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAudiogoiy());
        }
        if (value.getDapan() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDapan());
        }
        if (value.getAudioketqua() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAudioketqua());
        }
        if (value.getAudiogioithieu() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAudiogioithieu());
        }
      }
    };
  }

  @Override
  public void insert(final Question question) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestion.insert(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Question> getAllQuestion() {
    final String _sql = "SELECT*FROM question";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final int _cursorIndexOfKeyword = CursorUtil.getColumnIndexOrThrow(_cursor, "keyword");
      final int _cursorIndexOfAudiogoiy = CursorUtil.getColumnIndexOrThrow(_cursor, "audiogoiy");
      final int _cursorIndexOfDapan = CursorUtil.getColumnIndexOrThrow(_cursor, "dapan");
      final int _cursorIndexOfAudioketqua = CursorUtil.getColumnIndexOrThrow(_cursor, "audioketqua");
      final int _cursorIndexOfAudiogioithieu = CursorUtil.getColumnIndexOrThrow(_cursor, "audiogioithieu");
      final List<Question> _result = new ArrayList<Question>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Question _item;
        _item = new Question();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpImage;
        if (_cursor.isNull(_cursorIndexOfImage)) {
          _tmpImage = null;
        } else {
          _tmpImage = _cursor.getString(_cursorIndexOfImage);
        }
        _item.setImage(_tmpImage);
        final String _tmpKeyword;
        if (_cursor.isNull(_cursorIndexOfKeyword)) {
          _tmpKeyword = null;
        } else {
          _tmpKeyword = _cursor.getString(_cursorIndexOfKeyword);
        }
        _item.setKeyword(_tmpKeyword);
        final String _tmpAudiogoiy;
        if (_cursor.isNull(_cursorIndexOfAudiogoiy)) {
          _tmpAudiogoiy = null;
        } else {
          _tmpAudiogoiy = _cursor.getString(_cursorIndexOfAudiogoiy);
        }
        _item.setAudiogoiy(_tmpAudiogoiy);
        final String _tmpDapan;
        if (_cursor.isNull(_cursorIndexOfDapan)) {
          _tmpDapan = null;
        } else {
          _tmpDapan = _cursor.getString(_cursorIndexOfDapan);
        }
        _item.setDapan(_tmpDapan);
        final String _tmpAudioketqua;
        if (_cursor.isNull(_cursorIndexOfAudioketqua)) {
          _tmpAudioketqua = null;
        } else {
          _tmpAudioketqua = _cursor.getString(_cursorIndexOfAudioketqua);
        }
        _item.setAudioketqua(_tmpAudioketqua);
        final String _tmpAudiogioithieu;
        if (_cursor.isNull(_cursorIndexOfAudiogioithieu)) {
          _tmpAudiogioithieu = null;
        } else {
          _tmpAudiogioithieu = _cursor.getString(_cursorIndexOfAudiogioithieu);
        }
        _item.setAudiogioithieu(_tmpAudiogioithieu);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
