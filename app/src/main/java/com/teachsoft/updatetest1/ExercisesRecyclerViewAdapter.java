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

class ExercisesRecyclerViewAdapter extends RecyclerView.Adapter<ExercisesRecyclerViewAdapter.ExerciseImageViewHolder> {
    private static final String TAG = "RecyclerViewAdapt";
    private List<Exercise> mExerciseList;
    private Context mContext;

    public ExercisesRecyclerViewAdapter(Context context, List<Exercise> exerciseList) {
        mContext = context;
        mExerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Called by the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_exercises_item, parent, false);
        return new ExerciseImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseImageViewHolder holder, int position) {
//      Called by the layout manager when it wants new data in an existing row
        if ((mExerciseList == null) || (mExerciseList.size() == 0)){
//            holder.thumbnail.setImageResource(R.drawable.placeholder);
            holder.title.setText("No exercise found");
        }
        else {
            Exercise exerciseItem = mExerciseList.get(position);
            Log.d(TAG, "onBindViewHolder: " + exerciseItem.getTitle() + " --> " + position);
            holder.title.setText(exerciseItem.getTitle());
        }
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: called");
        return ((mExerciseList != null) && (mExerciseList.size() != 0) ? mExerciseList.size() : 1);
    }

    void loadNewData(List<Exercise> newExercise) {
        mExerciseList = newExercise;
        notifyDataSetChanged();
    }

    public Exercise getExercise(int position) {
        return ((mExerciseList != null) && (mExerciseList.size() != 0) ? mExerciseList.get(position) : null);
    }

    static class ExerciseImageViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail = null;
        TextView title = null;

        public ExerciseImageViewHolder(View itemView) {
            super(itemView);
//            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.textViewTitle);
        }
    }
}
