package com.yongki.movielist.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yongki.movielist.R;
import com.yongki.movielist.model.Result;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private OnItemListener mOnItemListener;
    private List<Result> albumList = new ArrayList<>();


    public void setData(List<Result> data) {
        albumList = data;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView date;
        ImageView img;
        CardView cardView;
        OnItemListener onItemListener;

        public MyViewHolder(View v, OnItemListener onItemListener) {
            super(v);

            name = (TextView) v.findViewById(R.id.name);
            date = (TextView) v.findViewById(R.id.date);
            img = (ImageView) v.findViewById(R.id.image);
            cardView = (CardView) v.findViewById(R.id.rc_cardview);

            this.onItemListener = onItemListener;

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public RecyclerAdapter(Context mContext, OnItemListener onItemListener) {
        this.mContext = mContext;
        this.mOnItemListener = onItemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false);

        return new MyViewHolder(itemView, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

//        DateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        final Result album = albumList.get(position);
//        long milliSeconds= Long.parseLong(album.date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliSeconds);
//        String date = formatter.format(calendar.getTime());

        holder.name.setText(album.getTitle());
        holder.date.setText(album.getReleaseDate());
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500".concat(album.getPosterPath())).into(holder.img);
        Log.d("foto","http://image.tmdb.org/t/p/w500".concat(album.getPosterPath()));
//        holder.img.setText(album.getPosterPath());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}
