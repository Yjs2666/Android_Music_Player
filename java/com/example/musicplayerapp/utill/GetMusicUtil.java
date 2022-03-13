package com.example.musicplayerapp.utill;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.musicplayerapp.Music;

import java.util.ArrayList;

//获取手机中音乐的工具类
public class GetMusicUtil {

    public ArrayList<Music> getMusic(ContentResolver resolver) {
        ArrayList<Music> mlist = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Music music = new Music();
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                int ismusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));

                if (ismusic != 0) {
                    music.setId(id);
                    music.setTitle(title);
                    music.setArtist(artist);
                    music.setAlbum(album);
                    music.setUrl(url);
                    music.setDuration(duration);
                    music.setSize(size);

                    mlist.add(music);
                }
                cursor.moveToNext();
            }
        }

        mlist.add(new Music("The Way I Still Love You - Reynard Silva", "https://lvdiimg.dbpdq.cn/Reynard%20Silva%20-%20The%20Way%20I%20Still%20Love%20You.mp3"));
        mlist.add(new Music("Dior - Pop Smoke", "https://lvdiimg.dbpdq.cn/a.mp3"));
        mlist.add(new Music("Don't Start Now - Duo Lipa", "https://lvdiimg.dbpdq.cn/b.mp3"));
        mlist.add(new Music("Drop the world - Eminem, Lil Wayne", "https://lvdiimg.dbpdq.cn/c.mp3"));
        mlist.add(new Music("Faded - Alan Walker", "https://lvdiimg.dbpdq.cn/d.mp3"));

        return mlist;
    }

    public static ArrayList<Music> getSong(ContentResolver resolver) {
        ArrayList<Music> mlist = new ArrayList<>();
        mlist.addAll(new GetMusicUtil().getMusic(resolver));
        mlist.add(new Music("Go Crazy — Chris Brown", "https://lvdiimg.dbpdq.cn/e.mp3"));
        mlist.add(new Music("Going Bad — Drake", "https://lvdiimg.dbpdq.cn/f.mp3"));
        mlist.add(new Music("Gone-ROSÉ", "https://lvdiimg.dbpdq.cn/g.mp3"));
        mlist.add(new Music("I knew you were trouble - Taylor Swift", "https://lvdiimg.dbpdq.cn/h.mp3"));
        mlist.add(new Music("Industry Baby — Lil Nas X / Jack Harlow", "https://lvdiimg.dbpdq.cn/i.mp3"));
        mlist.add(new Music("Going Bad — Drake", "https://lvdiimg.dbpdq.cn/j.mp3"));
        mlist.add(new Music("7 Rings - Ariana Grande", "https://lvdiimg.dbpdq.cn/k.mp3"));
        mlist.add(new Music("打上花火 — 米津玄师", "https://lvdiimg.dbpdq.cn/l.mp3"));
        mlist.add(new Music("绅士 - 薛之谦", "https://lvdiimg.dbpdq.cn/m.mp3"));
        mlist.add(new Music("All falls down - Alan Walker, Noah Cyrus", "https://lvdiimg.dbpdq.cn/n.mp3"));
        mlist.add(new Music("As long as you love me - Justin Bieber ft. Big Sean", "https://lvdiimg.dbpdq.cn/o.mp3"));
        mlist.add(new Music("Attention - Charlie Puth", "https://lvdiimg.dbpdq.cn/p.mp3"));
        mlist.add(new Music("Dior — Pop Smoke ", "https://lvdiimg.dbpdq.cn/q.mp3"));
        mlist.add(new Music("Go Crazy — Chris Brown", "https://lvdiimg.dbpdq.cn/r.mp3"));
        mlist.add(new Music("I'm the one - Justin Bieber, Lil Wayne, Chance the Rapper, DJ Khaled, Quavo", "https://lvdiimg.dbpdq.cn/s.mp3"));
        mlist.add(new Music("If I can't have you - Shawn Mendes", "https://lvdiimg.dbpdq.cn/t.mp3"));
        mlist.add(new Music("Industry Baby - Lil Nas X ft. Jack Harlow", "https://lvdiimg.dbpdq.cn/u.mp3"));
        mlist.add(new Music("Laugh now and cry late - Drake ft. Lil Durk", "https://lvdiimg.dbpdq.cn/v.mp3"));
        mlist.add(new Music("Leave the door open - Bruno Mars Anderson", "https://lvdiimg.dbpdq.cn/w.mp3"));
        mlist.add(new Music("Lemon -  米津玄师", "https://lvdiimg.dbpdq.cn/x.mp3"));
        mlist.add(new Music("Lose yourself - Eminem", "https://lvdiimg.dbpdq.cn/y.mp3"));
        mlist.add(new Music("Love story - Taylor Swift", "https://lvdiimg.dbpdq.cn/z.mp3"));
        mlist.add(new Music("One call away - Charlie Puth", "https://lvdiimg.dbpdq.cn/za.mp3"));
        mlist.add(new Music("Panda - Desiigner", "https://lvdiimg.dbpdq.cn/zb.mp3"));
        mlist.add(new Music("Say so - Doja Cat", "https://lvdiimg.dbpdq.cn/zc.mp3"));
        mlist.add(new Music("Sicko Mode - Travis Scott", "https://lvdiimg.dbpdq.cn/zd.mp3"));
        mlist.add(new Music("Stay - Justin Bieber, The Kid LAROI", "https://lvdiimg.dbpdq.cn/ze.mp3"));
        mlist.add(new Music("Style - Taylor Swift", "https://lvdiimg.dbpdq.cn/zf.mp3"));
        mlist.add(new Music("Suckers - Jonas Brothers", "https://lvdiimg.dbpdq.cn/zg.mp3"));
        mlist.add(new Music("You belong with me - Taylor Swift", "https://lvdiimg.dbpdq.cn/zh.mp3"));
        mlist.add(new Music("Vibez - DaBaby","https://lvdiimg.dbpdq.cn/zr.mp3"));
        mlist.add(new Music("The Show Goes On  — Lupe Fiasco","https://lvdiimg.dbpdq.cn/zq.mp3"));
        mlist.add(new Music("In Your Eyes - The Weeknd","https://lvdiimg.dbpdq.cn/zk.mp3"));
        mlist.add(new Music("Indigo - 88rising and Niki","https://lvdiimg.dbpdq.cn/zl.mp3"));
        mlist.add(new Music("Lost Cause - Bilie Ellish","https://lvdiimg.dbpdq.cn/zm.mp3"));
        mlist.add(new Music("On The Ground - Rose","https://lvdiimg.dbpdq.cn/zn.mp3"));
        mlist.add(new Music("Righteous — Juice WRLD","https://lvdiimg.dbpdq.cn/zo.mp3"));
        mlist.add(new Music("The Box - Roddy Ricch","https://lvdiimg.dbpdq.cn/zp.mp3"));
        mlist.add(new Music("Astronaut In Ocean - Masked Wolf","https://lvdiimg.dbpdq.cn/zj.mp3"));
        mlist.add(new Music("Anyone - Justin Bieber","https://lvdiimg.dbpdq.cn/zi.mp3"));
        return mlist;
    }

}
