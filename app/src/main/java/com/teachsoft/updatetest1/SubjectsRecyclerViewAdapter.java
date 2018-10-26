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

//import com.squareup.picasso.Picasso;

import java.util.List;

class SubjectsRecyclerViewAdapter extends RecyclerView.Adapter<SubjectsRecyclerViewAdapter.SubjectImageViewHolder> {
    private static final String TAG = "RecyclerViewAdapt";
    private List<ClassSubject> mSubjectList;
    private Context mContext;

    public SubjectsRecyclerViewAdapter(Context context, List<ClassSubject> subjectList) {
        mContext = context;
        mSubjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Called by the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_subjects_item, parent, false);
        return new SubjectImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectImageViewHolder holder, int position) {
//      Called by the layout manager when it wants new data in an existing row
        if ((mSubjectList == null) || (mSubjectList.size() == 0)){
//            holder.thumbnail.setImageResource(R.drawable.placeholder);
            holder.title.setText("No subject found");
        }
        else {
            ClassSubject subjectItem = mSubjectList.get(position);
            Log.d(TAG, "onBindViewHolder: " + subjectItem.getTitle() + " --> " + position);
            holder.title.setText(subjectItem.getTitle());
        }
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: called");
        return ((mSubjectList != null) && (mSubjectList.size() != 0) ? mSubjectList.size() : 1);
    }

    void loadNewData(List<ClassSubject> newSubject) {
        mSubjectList = newSubject;
        notifyDataSetChanged();
    }

    public ClassSubject getSubject(int position) {
        return ((mSubjectList != null) && (mSubjectList.size() != 0) ? mSubjectList.get(position) : null);
    }

    static class SubjectImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "SubjectImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public SubjectImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "SubjectImageViewHolder: starts");
//            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.textViewTitle);
        }
    }
}

