package com.teachsoft.updatetest1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class ChaptersRecyclerViewAdapter extends RecyclerView.Adapter<ChaptersRecyclerViewAdapter.ChapterImageViewHolder> {
    private static final String TAG = "RecyclerViewAdapt";
    private List<Chapter> mChapterList;
    private Context mContext;

    public ChaptersRecyclerViewAdapter(Context context, List<Chapter> chapterList) {
        mContext = context;
        mChapterList = chapterList;
    }

    @NonNull
    @Override
    public ChapterImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Called by the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_chapters_item, parent, false);
        return new ChapterImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterImageViewHolder holder, int position) {
//      Called by the layout manager when it wants new data in an existing row
        if ((mChapterList == null) || (mChapterList.size() == 0)){
//            holder.thumbnail.setImageResource(R.drawable.placeholder);
            holder.title.setText("No Chapter found");
        }
        else {
            Chapter chapterItem = mChapterList.get(position);
            Log.d(TAG, "onBindViewHolder: " + chapterItem.getTitle() + " --> " + position);
            holder.title.setText(chapterItem.getTitle());
        }
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: called");
        return ((mChapterList != null) && (mChapterList.size() != 0) ? mChapterList.size() : 1);
    }

    void loadNewData(List<Chapter> newChapter) {
        mChapterList = newChapter;
        notifyDataSetChanged();
    }

    public Chapter getChapter(int position) {
        return ((mChapterList != null) && (mChapterList.size() != 0) ? mChapterList.get(position) : null);
    }

    static class ChapterImageViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail = null;
        TextView title = null;

        public ChapterImageViewHolder(View itemView) {
            super(itemView);
//            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.textViewTitle);
        }
    }
}
