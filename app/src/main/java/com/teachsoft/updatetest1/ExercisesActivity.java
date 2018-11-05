package com.teachsoft.updatetest1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends BaseActivity implements ExercisesRecyclerItemClickListener.OnRecyclerClickListener {

    private Button mButtonAddExercise;

    private List<Exercise> mExerciseList = null;

    private Subject mCurrentSubject;
    private Chapter mCurrentChapter;

    private ExercisesRecyclerViewAdapter mExercisesRecyclerViewAdapter;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    DatabaseReference mDBReferenceExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

//        Read params passed by prior activity
        Intent intent = getIntent();
        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);
        String subjectCode = mCurrentSubject.getCode();
        mCurrentChapter = (Chapter) intent.getSerializableExtra(CURRENT_CHAPTER);
        String chapterCode = mCurrentChapter.getCode();

        mButtonAddExercise = findViewById(R.id.buttonAddExercise);

        RecyclerView recyclerViewExercises = findViewById(R.id.recyclerViewExercises);
        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExercises.addOnItemTouchListener(new ExercisesRecyclerItemClickListener(ExercisesActivity.this, recyclerViewExercises, ExercisesActivity.this));

        mExercisesRecyclerViewAdapter = new ExercisesRecyclerViewAdapter(ExercisesActivity.this, new ArrayList<Exercise>());
        recyclerViewExercises.setAdapter(mExercisesRecyclerViewAdapter);

        mDBReferenceExercises = mFirebaseDB.getReference(SUBJECTS_TITLE).child(subjectCode).child(CHAPTERS_TITLE).child(chapterCode).child(EXERCISES_TITLE);
        mDBReferenceExercises.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        mButtonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExercisesActivity.this, ExerciseConfigurationActivity.class);
                intent.putExtra(CURRENT_SUBJECT, mCurrentSubject);
                intent.putExtra(CURRENT_CHAPTER, mCurrentChapter);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onItemLongClick(View view, int position) {
//        Intent intent = new Intent(SubjectsActivity.this, ChaptersActivity.class);
//        intent.putExtra(CURRENT_SUBJECT, mSubjectsRecyclerViewAdapter.getSubject(position));
//        startActivity(intent);
    }

    private void getData(DataSnapshot dataSnapshot){
        mExerciseList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Exercise exercise = ds.getValue(Exercise.class);
            mExerciseList.add(exercise);
        }

        mExercisesRecyclerViewAdapter.loadNewData(mExerciseList);
    }
}