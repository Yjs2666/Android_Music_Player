package com.example.musicplayerapp.bean;

public class MusicBean {

    private String imgUrl;
    private String albumName;
    private String albumIntroduce;

    public MusicBean(String imgUrl, String albumName, String albumIntroduce) {
        this.imgUrl = imgUrl;
        this.albumName = albumName;
        this.albumIntroduce = albumIntroduce;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumIntroduce() {
        return albumIntroduce;
    }

    public void setAlbumIntroduce(String albumIntroduce) {
        this.albumIntroduce = albumIntroduce;
    }
}
