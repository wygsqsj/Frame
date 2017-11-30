package com.xwh.frame.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xwh.frame.R;
import com.xwh.frame.adapter.holder.JokeHolder;
import com.xwh.frame.mvp.model.bean.Joke;

import java.util.ArrayList;
import java.util.List;

/**
 * 笑话列表页适配器
 * <p>
 * Created by XH on 2017/11/30.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeHolder> {

    private List<Joke.DataBean> mDatas;

    public JokeAdapter() {
        this.mDatas = new ArrayList<>();
    }

    @Override
    public JokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = View.inflate(parent.getContext(), R.layout.item_joke, parent);
        return new JokeHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_joke, parent, false));
    }

    @Override
    public void onBindViewHolder(JokeHolder holder, int position) {
        holder.text.setText(mDatas.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void refresh(List<Joke.DataBean> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void loadMore(List<Joke.DataBean> listBeen) {
        mDatas.addAll(mDatas.size(), listBeen);
        notifyDataSetChanged();
    }

    public void loadNew(List<Joke.DataBean> listBeen) {
        mDatas.addAll(0, listBeen);
        notifyDataSetChanged();
    }
}
