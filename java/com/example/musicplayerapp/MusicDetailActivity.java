package com.example.musicplayerapp;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayerapp.utill.GlideUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//Music player page
public class MusicDetailActivity extends BaseActivity implements View.OnClickListener {


    private TextView music_title;
    private ImageView btn_play;
    private ImageView mc_Cover;

    private ArrayList<Music> mMusicList = new ArrayList<>();
    private Music mMusicBean;
    private int mPosition;
    private SeekBar seekBar;
    private TextView music_time, time_music;

    private SharedPreferences sp;
    private ArrayList<Music> shouCangList;
    private MediaPlayer mp;
    private Timer timer;

    private boolean isPlay = true;
    private int[] photos= {R.drawable.anime1, R.drawable.anime2,R.drawable.anime3,R.drawable.anime5, R.drawable.anime6,R.drawable.anime7, R.drawable.anime8,R.drawable.anime9};
    private boolean isSeekbarChaning;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_music_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        mMusicList = getIntent().getParcelableArrayListExtra("musicBean");
        mPosition = getIntent().getIntExtra("position", 0);
        mMusicBean = mMusicList.get(mPosition);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        shouCangList = new Gson().fromJson(sp.getString("shou_cang_music", ""), new TypeToken<List<Music>>() {
        }.getType());
        if (shouCangList == null) {
            shouCangList = new ArrayList<>();
        }
        mp = new MediaPlayer();
        music_title = findViewById(R.id.music_title);
        btn_play = findViewById(R.id.btn_play);
        seekBar = findViewById(R.id.music_seek);
        music_time = findViewById(R.id.music_time);
        time_music = findViewById(R.id.time_music);
        mc_Cover = findViewById(R.id.iv_cover);

        music_title.setText(mMusicBean.getTitle());

        //Randomize album covers
        Random ran = new Random();

        int i = ran.nextInt(photos.length);
        mc_Cover.setImageResource(photos[i]);

        findViewById(R.id.btn_last).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        btn_play.setOnClickListener(this);

        plaMusic();
        btn_play.setImageResource(isPlay ? R.drawable.music_suspend : R.drawable.music_play);

        //Set Listener to the seekbar based on the process of a song
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int duration2 = mp.getDuration() / 1000;//Get duration of songs in ms
                int position = mp.getCurrentPosition();//Get current music position
                music_time.setText(calculateTime(position / 1000));//Start time
                time_music.setText(calculateTime(duration2));//Total time
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekbarChaning = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isSeekbarChaning = false;
                mp.seekTo(seekBar.getProgress());//在当前位置播放
                time_music.setText(calculateTime(mp.getCurrentPosition() / 1000));
            }
        });
    }

    //Calculate the music time
    public String calculateTime(int time) {
        int minute;
        int second;
        if (time >= 60) {
            minute = time / 60;
            second = time % 60;
            //Music must be less than 10 mins
            if (minute < 10) {
                //Calculate second
                if (second < 10) {
                    return "0" + minute + ":" + "0" + second;
                } else {
                    return "0" + minute + ":" + second;
                }
            } else {
                //Re-check when min > 10
                if (second < 10) {
                    return minute + ":" + "0" + second;
                } else {
                    return minute + ":" + second;
                }
            }
        } else {
            second = time;
            if (second >= 0 && second < 10) {
                return "00:" + "0" + second;
            } else {
                return "00:" + second;
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                isPlayMusic(!isPlay);
                break;
            case R.id.btn_last:
                if (mPosition == 0) {
                    Toast.makeText(this, "Don't have previous song", Toast.LENGTH_SHORT).show();
                    return;
                }

                Random ran = new Random();
                int i = ran.nextInt(photos.length);
                mc_Cover.setImageResource(photos[i]);

                mPosition = mPosition - 1;
                mMusicBean = mMusicList.get(mPosition);
                music_title.setText(mMusicBean.getTitle());
                btn_play.setImageResource(R.drawable.music_suspend);
                plaMusic();
                break;
            case R.id.btn_next:
                if (mPosition == mMusicList.size()) {
                    Toast.makeText(this, "No song in next", Toast.LENGTH_SHORT).show();
                    return;
                }

                Random ran2 = new Random();
                int j = ran2.nextInt(photos.length);
                mc_Cover.setImageResource(photos[j]);

                mPosition = mPosition + 1;
                mMusicBean = mMusicList.get(mPosition);
                music_title.setText(mMusicBean.getTitle());
                btn_play.setImageResource(R.drawable.music_suspend);
                plaMusic();
                break;
        }
    }


    private void isPlayMusic(boolean isPlay) {
        this.isPlay = isPlay;
        btn_play.setImageResource(isPlay ? R.drawable.music_suspend : R.drawable.music_play);

        if (isPlay) {
            if (mp != null) {
                mp.start();
            }
        } else {
            if (mp != null) {
                mp.pause();
            }
        }

    }

    private void plaMusic() {
        mp.reset();
        try {
            String url = mMusicBean.getUrl();
            mp.setDataSource(url);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int duration2 = mp.getDuration() / 1000;
        int position = mp.getCurrentPosition();
        music_time.setText(calculateTime(position / 1000));
        time_music.setText(calculateTime(duration2));

        int duration = mp.getDuration();//Get music duratoin
        seekBar.setMax(duration);//Set max to seekbar value
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isSeekbarChaning) {
                    seekBar.setProgress(mp.getCurrentPosition());
                }
            }
        }, 0, 50);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.reset();
        }
    }
}
