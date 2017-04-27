package com.jcoder.app.folink.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcoder.app.folink.R;
import com.jcoder.app.folink.activity.FolinkImageWebActivity;
import com.jcoder.app.folink.model.AbNewsItem;

import java.util.ArrayList;

/**
 * Created by Rory on 2016/10/8.
 */

public class FolinkImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<AbNewsItem> mNewsArrayList = new ArrayList<>();

    public FolinkImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FolinkImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fimage_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if(getItemViewType(position) == TYPE_HEADER) return;
        bindViewHolderNormal((FolinkImageViewHolder) holder, position);
    }

    private void bindViewHolderNormal(final FolinkImageViewHolder holder, int position) {

        final AbNewsItem fNews = mNewsArrayList.get(holder.getAdapterPosition());

        holder.textView.setText(fNews.getTitle());
        holder.sourceTextview.setText(fNews.getContent());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImageWebActivity(fNews , holder);
            }
        });

    }

    private void startImageWebActivity(AbNewsItem newsBeanItem,RecyclerView.ViewHolder holder) {

        Intent intent = new Intent(mContext, FolinkImageWebActivity.class);
        intent.putExtra(FolinkImageWebActivity.SERIALIZABLE_TAG, newsBeanItem);

            mContext.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return mNewsArrayList.size();
    }

    public void addNewsData(ArrayList<AbNewsItem> ns) {
        mNewsArrayList.addAll(ns);
        notifyDataSetChanged();
    }

    private class FolinkImageViewHolder extends RecyclerView.ViewHolder {

        final TextView textView;
        final LinearLayout linearLayout;
        final TextView sourceTextview;

        public FolinkImageViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item_text_id);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.zhihu_item_layout);
            sourceTextview= (TextView) itemView.findViewById(R.id.item_text_source_id);
        }
    }

}
