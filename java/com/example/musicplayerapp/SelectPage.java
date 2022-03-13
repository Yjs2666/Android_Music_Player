package com.example.musicplayerapp;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.adapter.BaseRecyclerAdapter;
import com.example.musicplayerapp.adapter.MusicAlbumAdapter;
import com.example.musicplayerapp.bean.MusicBean;
import com.example.musicplayerapp.utill.GetMusicUtil;
import com.example.musicplayerapp.utill.GlideUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SelectPage extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BaseRecyclerAdapter.onItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ImageView iv_bg;
    private NavigationView mNavView;
    private RecyclerView mMusicRecycler;
    private ArrayList<Music> musicList;
    private MusicAlbumAdapter mAlbumAdapter;
    private ArrayList<MusicBean> mMusicAlbumList;
    private boolean isDrawerOpen;
    private int i = 1;
    private boolean mBackKeyPressed;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_select_page;
    }

    @Override
    protected void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        iv_bg = findViewById(R.id.iv_bg);
        mNavView = findViewById(R.id.nav_view);
        mMusicRecycler = findViewById(R.id.music_recycler);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.icon_geng);


        mMusicAlbumList = new ArrayList<>();
        musicList = new GetMusicUtil().getMusic(getContentResolver());
        GlideUtils.loadBlurImage(getApplicationContext(), (Integer) AppPreferences.get(getApplicationContext(), "AppBg", R.drawable.default_bg), iv_bg);
        mNavView.setNavigationItemSelectedListener(this);
        mMusicRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mAlbumAdapter = new MusicAlbumAdapter(this);
        mMusicRecycler.setAdapter(mAlbumAdapter);
        mAlbumAdapter.setOnItemClickListener(this);

        //Monitor
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                Log.i("---", "Swiping");
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Log.i("---", "Open");
                isDrawerOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Log.i("---", "Close");
                isDrawerOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int i) {
                Log.i("---", "Status Changes");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (isDrawerOpen) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, MusicListDetailActivity.class);
        switch (item.getItemId()) {
            case R.id.item_music_library:
                switch (i) {
                    case 1:
                        i = 2;
                        mMusicAlbumList.clear();
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/d/db/Slime_%26_B_cover.jpg", "Go Crazy", "Chris Brown"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/f/f6/Meek_Mill_%E2%80%93_Championships.png", "Going Bad", "Drake"));
                        mMusicAlbumList.add(new MusicBean("https://images.genius.com/04b543b45bb91449afcf59d62ec55be8.1000x1000x1.jpg", "Gone", "ROSÉ"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/7/70/I_Knew_You_Were_Trouble.png", "I knew you were trouble", "Taylor Swift"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/f/f7/Lil_Nas_X_-_Industry_Baby.png", "Industry baby", "Lil Nas X / Jack Harlow"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/b/b7/Ariana_Grande_-_7_rings.png", "7 Rings", "Ariana Grande"));
                        mMusicAlbumList.add(new MusicBean("https://c-cl.cdn.smule.com/rs-s79/arr/10/ea/c80996cd-e71d-44cf-bd6a-098d7a1cd1f8_1024.jpg", "打上花火", "米津玄师"));
                        mMusicAlbumList.add(new MusicBean("https://img10.360buyimg.com/n1/jfs/t1216/262/1127735026/134310/4e72a81f/557802c0Ne5aab0d9.jpg", "绅士", "薛之谦"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/3/33/Alan_Walker_All_Falls_Down.jpg", "All falls down ", "Alan Walker, Noah Cyrus"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/thumb/1/17/AsLongAsYouLoveMe.jpg/220px-AsLongAsYouLoveMe.jpg", "As long as you love me", "Justin Bieber ft. Big Sean"));

                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/0/0d/Justin_Bieber_-_Anyone.png", "Anyone", "Justin Bieber"));
                        mMusicAlbumList.add(new MusicBean("https://images.genius.com/f3ca0e2c5a434c8a91758de3b453bbde.1000x1000x1.png", "Astronaut In Ocean", "Masked Wolf"));

                        break;
                    case 2:
                        i = 3;
                        mMusicAlbumList.clear();
                        mMusicAlbumList.add(new MusicBean("https://dynamicmedia.livenationinternational.com/Media/j/o/k/5c8c2823-335d-458e-8a07-d652b136ab7c.jpg", "Attention", "Charlie Puth"));
                        mMusicAlbumList.add(new MusicBean("https://hiphop-n-more.com/wp-content/uploads/2020/02/pop-smoke-dior-remix-feat-gunna.jpg", "Dior", "Pop Smoke"));
                        mMusicAlbumList.add(new MusicBean("https://images.genius.com/d0617dbe625456f4ead2cdd00fcff3cb.1000x1000x1.jpg", "Go Crazy", "Chris Brown"));
                        mMusicAlbumList.add(new MusicBean("https://i.pinimg.com/originals/12/1f/d6/121fd697c7f88ba29c71f621392edb86.jpg", "I'm the one", "Justin Bieber, Lil Wayne, Chance the Rapper, DJ Khaled, Quavo"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/a/a7/Shawn_Mendes_-_If_I_Can%27t_Have_You.png", "If I can't have you", "Shawn Mendes"));

                        mMusicAlbumList.add(new MusicBean("https://media1.popsugar-assets.com/files/thumbor/CpCU7ILbRByY4cUA-hYC9Z9VpEs/fit-in/2048xorig/filters:format_auto-!!-:strip_icc-!!-/2020/08/14/668/n/1922283/b7557a6d42cff9b1_kd/i/Kevin-Durant-in-Laugh-Now-Cry-Later-Music-Video.jpg", "Laugh now and cry later", "Drake ft. Lil Durk"));
                        mMusicAlbumList.add(new MusicBean("https://i.ytimg.com/vi/-7acBvpE2yk/maxresdefault.jpg", "Leave the door open", "Bruno Mars Anderson"));
                        mMusicAlbumList.add(new MusicBean("http://5b0988e595225.cdn.sohucs.com/images/20190213/b2dac69c4a6e481580c3a31efa87604f.jpeg", "Lemon", "米津玄师"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/d/d6/Lose_Yourself.jpg", "Lose yourself", "Eminem"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/0/01/Taylor_Swift_-_Love_Story.png", "Love story", "Taylor Swift"));

                        mMusicAlbumList.add(new MusicBean("https://i.ytimg.com/an/uLHqpjW3aDs/c5a7b135-02a4-4ab1-b633-89344a9a703d_mq.jpg?v=5e5996f5", "The Box", "Roddy Ricch"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/d/d9/Juice_Wrld_-_Righteous.png", "Righteous", "Juice WRLD"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/d/d0/Ros%C3%A9%E2%80%93On_the_Ground.jpg", "On The Ground", "Rose"));
                        break;
                    case 3:
                        i = 1;
                        mMusicAlbumList.add(new MusicBean("https://i.scdn.co/image/ab67616d0000b2734fe297c018e495a97662e5ac", "Attention", "Charlie Puth"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/e/e7/DesiignerPanda.jpg", "Panda", "Desiigner"));
                        mMusicAlbumList.add(new MusicBean("https://media.socastsrm.com/wordpress/wp-content/blogs.dir/2162/files/2020/05/doja-cat.jpg", "Say so", "Doja Cat"));
                        mMusicAlbumList.add(new MusicBean("https://d2p1nnn035jt22.cloudfront.net/spw/images/releases/2018/sickomode960.jpg", "Sicko Mode", "Travis Scott"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/a/a7/Shawn_Mendes_-_If_I_Can%27t_Have_You.png", "Stay", "Justin Bieber"));

                        mMusicAlbumList.add(new MusicBean("https://projectu.tv/wp-content/uploads/2015/02/d38574e0-9526-0132-0b9a-34b52f6f1279.jpg", "Style", "Taylor Swift"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/8/87/Jonas_Brothers_-_Sucker.png", "Suckers", "Jonas Brothers"));
                        mMusicAlbumList.add(new MusicBean("https://media.glamour.com/photos/583dd6f566d6535c6894ec55/6:7/w_785,h_916,c_limit/taylor%20swift.jpg", "You belong with me", "Taylor Swift"));
                        mMusicAlbumList.add(new MusicBean("https://www.bongminesentertainment.com/wp-content/uploads/2019/10/DaBaby-22KIRK22-cover.jpg", "Vibez", "DaBaby"));
                        mMusicAlbumList.add(new MusicBean("https://upload.wikimedia.org/wikipedia/en/3/38/Lupe-fiasco-show-goes-on.jpeg", "The Show Goes On", "Lupe Fiasco"));

                        mMusicAlbumList.add(new MusicBean("https://m.media-amazon.com/images/I/91WwPxqh+UL._SS500_.jpg", "Indigo", "88rising and Niki"));
                        mMusicAlbumList.add(new MusicBean("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/billie-eilish-lost-cause-skims-1622651247.jpg", "Lost Cause", "Bilie Ellish"));
                        mMusicAlbumList.add(new MusicBean("https://m.media-amazon.com/images/M/MV5BZWIzNWIzYzMtOWFlZi00ODlmLTk5Y2ItNzdiZDZmNDFmYWY2XkEyXkFqcGdeQXVyNjU1OTg4OTM@._V1_.jpg", "In Your Eyes", "The Weeknd"));

                        break;
                }
                mAlbumAdapter.replaceAllItems(mMusicAlbumList);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.item_local_songs:
                intent.putExtra("music", musicList);
                startActivity(intent);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.item_change_theme:
                Intent intent1 = new Intent(this, ThemActivity.class);
                intent1.putExtra("zhut", true);
                startActivity(intent1);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.item_change_background:
                startActivity(new Intent(this, BgActivity.class));
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.item_search_songs:
                intent.putExtra("search", true);
                intent.putExtra("music", musicList);
                startActivity(intent);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.item_logout:
                startActivity(new Intent(this, MainActivity.class));
                mDrawerLayout.closeDrawer(GravityCompat.START);
                finish();
                break;
            case R.id.shareButton:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                // type of the content to be shared
                sharingIntent.setType("SoundBoard is the best APP");
                // Body of the content
                String shareBody = "Download in CIS 400";
                // subject of the content. you can share anything
                String shareSubject = "SoundBoard invitation";

                // passing body of the content
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                // passing subject of the content
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(BaseRecyclerAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, MusicListDetailActivity.class);
        intent.putExtra("search", true);
        intent.putExtra("music", musicList);
        startActivity(intent);
    }

}
