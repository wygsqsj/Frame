package com.xwh.frame.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xwh.frame.R;

/**
 * 笑话列表Holder
 * <p>
 * Created by XH on 2017/11/30.
 */

public class JokeHolder extends RecyclerView.ViewHolder {

    public TextView text;

    public JokeHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.item_joke_text);
    }
}
