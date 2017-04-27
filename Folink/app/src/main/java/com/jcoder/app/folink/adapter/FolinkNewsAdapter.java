package com.jcoder.app.folink.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jcoder.app.folink.R;
import com.jcoder.app.folink.activity.FolinkWebActivity;
import com.jcoder.app.folink.model.FNews;

import java.util.ArrayList;

/**
 * Created by Rory on 2016/10/8.
 */

public class FolinkNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private Context mContext;
//    private View mHeaderView;
    private ArrayList<FNews> mNewsArrayList = new ArrayList<>();

    public FolinkNewsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        if(mHeaderView != null && viewType == TYPE_HEADER) return new FolinkHeaderViewHolder(mHeaderView);

        return new FolinkNewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fnews_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if(getItemViewType(position) == TYPE_HEADER) return;
        bindViewHolderNormal((FolinkNewsViewHolder) holder, position);
    }

    private void bindViewHolderNormal(final FolinkNewsViewHolder holder, int position) {

        final FNews fNews = mNewsArrayList.get(holder.getAdapterPosition());

        holder.textView.setText(fNews.getTitle());
        holder.sourceTextview.setText(fNews.getSource());

        Glide.with(mContext).load(fNews.getImgsrc()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop().into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebActivity(fNews , holder);
            }
        });

    }

    private void startWebActivity(FNews newsBeanItem,RecyclerView.ViewHolder holder) {

        Intent intent = new Intent(mContext, FolinkWebActivity.class);
//        intent.putExtra("docid", newsBeanItem.getDocid());
//        intent.putExtra("title", newsBeanItem.getTitle());
//        intent.putExtra("image", newsBeanItem.getImgsrc());
        intent.putExtra(FolinkWebActivity.SERIALIZABLE_TAG, newsBeanItem);
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            final android.support.v4.util.Pair<View, String>[] pairs = Help.createSafeTransitionParticipants
//                    ((Activity) mContext, false,new android.support.v4.util.Pair<>(((FolinkNewsViewHolder)holder).imageView, mContext.getString(R.string.transition_topnew)),
//                            new android.support.v4.util.Pair<>(((FolinkNewsViewHolder)holder).linearLayout, mContext.getString(R.string.transition_topnew_linear)));
//            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, pairs);
//            mContext.startActivity(intent, options.toBundle());
//        }else {

//            ((BaseActivity)mContext).startActivityWithAnim(intent);
        mContext.startActivity(intent);

//        }
    }

//    public void setHeaderView(View headerView) {
//        mHeaderView = headerView;
//        notifyItemInserted(0);
//    }
//    public View getHeaderView() {
//        return mHeaderView;
//    }

    @Override
    public int getItemViewType(int position) {
//        if(mHeaderView == null) return TYPE_NORMAL;
//        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mNewsArrayList.size();
    }

    public void addNewsData(ArrayList<FNews> ns) {
        mNewsArrayList.addAll(ns);
        notifyDataSetChanged();
    }

    private class FolinkNewsViewHolder extends RecyclerView.ViewHolder {

        final TextView textView;
        final LinearLayout linearLayout;
        final TextView sourceTextview;
        ImageView imageView;

        public FolinkNewsViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.item_image_id);
            textView = (TextView) itemView.findViewById(R.id.item_text_id);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.zhihu_item_layout);
            sourceTextview= (TextView) itemView.findViewById(R.id.item_text_source_id);
        }
    }

    private class FolinkHeaderViewHolder extends RecyclerView.ViewHolder {

        final TextView textView;
        final LinearLayout linearLayout;

        public FolinkHeaderViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.header_folink_title);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.header_folink_search_bg);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startSearchActivity();
                }
            });

        }
    }

    private void startSearchActivity() {

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

}
