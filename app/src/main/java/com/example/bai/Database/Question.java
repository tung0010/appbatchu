package com.example.bai.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;
    String image;
    String keyword;
    String audiogoiy;
    String dapan;
    String audioketqua;
    String audiogioithieu;

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }

    public String getAudiogoiy() {
        return audiogoiy;
    }

    public void setAudiogoiy(String audiogoiy) {
        this.audiogoiy = audiogoiy;
    }

    public String getAudioketqua() {
        return audioketqua;
    }

    public void setAudioketqua(String audioketqua) {
        this.audioketqua = audioketqua;
    }

    public String getAudiogioithieu() {
        return audiogioithieu;
    }

    public void setAudiogioithieu(String audiogioithieu) {
        this.audiogioithieu = audiogioithieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
