package com.example.raj.bookapicapstone;

public class BookModel {

    String tit,img;

    public BookModel(String tit, String img) {
        this.tit = tit;
        this.img = img;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
