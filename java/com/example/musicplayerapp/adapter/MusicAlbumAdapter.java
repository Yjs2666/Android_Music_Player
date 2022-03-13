package com.example.musicplayerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.musicplayerapp.R;
import com.example.musicplayerapp.bean.MusicBean;
import com.example.musicplayerapp.utill.GlideUtils;

public class MusicAlbumAdapter extends BaseRecyclerAdapter<MusicBean, MusicAlbumAdapter.MusicAlbumHolder>
        implements View.OnClickListener {

    public MusicAlbumAdapter(Context context) {
        super(context);
    }

    @Override
    protected MusicAlbumHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        //Fidn the item layout
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_music_album, parent, false);
        view.setOnClickListener(this);
        return new MusicAlbumHolder(view); //Return item to the holder
    }

    @Override
    protected void onBindBaseViewHolder(MusicAlbumHolder holder, int position) {
        holder.bindData(getItem(position));
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        //Go back when click
        mItemClickListener.onItemClick(MusicAlbumAdapter.this, view, (Integer) view.getTag());

    }


    public class MusicAlbumHolder extends BaseRecyclerHolder<MusicBean> {

        private ImageView mIvAlbum;
        private TextView album_name, album_introduce;

        public MusicAlbumHolder(View itemView) {
            super(itemView);
            mIvAlbum = itemView.findViewById(R.id.iv_album);
            album_name = itemView.findViewById(R.id.album_name);
            album_introduce = itemView.findViewById(R.id.album_introduce);
        }

        @Override
        public void bindData(MusicBean data) {
            Glide.with(getContext()).load(data.getImgUrl()).into(mIvAlbum);
            album_name.setText(data.getAlbumName());
            album_introduce.setText(data.getAlbumIntroduce());
        }
    }
}
