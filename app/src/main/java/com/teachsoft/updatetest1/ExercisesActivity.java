package com.teachsoft.updatetest1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends BaseActivity  implements ExercisesRecyclerItemClickListener.OnRecyclerClickListener {

//    private Button mButtonAddExercise;
//    private EditText mEditTextExerciseInput;
//
//    private List<Exercise> mExerciseList = null;
//
//    private Subject mCurrentSubject;
//    private Chapter mCurrentChapter;
//
//    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
//    DatabaseReference mDBReferenceCurrentChapter;
//
//    private ExercisesRecyclerViewAdapter mExercisesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

//        Intent intent = getIntent();
//        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);
//        mCurrentChapter = (Chapter) intent.getSerializableExtra(CURRENT_CHAPTER);
//
//        mButtonAddExercise = findViewById(R.id.buttonAddExercise);
//        mEditTextExerciseInput = findViewById(R.id.editTextExerciseInput);
//
//        RecyclerView recyclerViewExercises = findViewById(R.id.recyclerViewExercises);
//        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerViewExercises.addOnItemTouchListener(new ExercisesRecyclerItemClickListener(ExercisesActivity.this, recyclerViewExercises, ExercisesActivity.this));
//
//        mExercisesRecyclerViewAdapter = new ExercisesRecyclerViewAdapter(ExercisesActivity.this, new ArrayList<Exercise>());
//        recyclerViewExercises.setAdapter(mExercisesRecyclerViewAdapter);
//
//        String subjectCode = mCurrentSubject.getCode();
//        String subjectTitle = mCurrentSubject.getTitle();
//
//        String chapterCode = mCurrentChapter.getCode();
//
//        mExerciseList = mCurrentChapter.getExercises();

//        mDBReferenceCurrentChapter = mFirebaseDB.getReference(SUBJECTS_TITLE).child(subjectCode).child(CHAPTERS_TITLE).child(chapterCode);
//        DatabaseReference DBReferenceCurrentChapters = mFirebaseDB.getReference(SUBJECTS_TITLE).child(subjectCode).child(CHAPTERS_TITLE);
//        for (DatabaseReference dbrf : DBReferenceCurrentChapters.ge)

//        mDBReferenceCurrentChapter = mFirebaseDB.getReference(SUBJECTS_TITLE).child(subjectCode).child(CHAPTERS_TITLE).child("0").child(chapterCode);
//
//        mDBReferenceCurrentChapter.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                getData(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {}
//        });

//        mButtonAddExercise.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String code = mEditTextExerciseInput.getText().toString();
//                String title = code;
//
//                if (!title.equals("")) {
//                    DatabaseReference mDBReferenceSubject = mDBReferenceCurrentChapter.child(EXERCISES_TITLE);
//
//                    title = title + " Title";
//
//                    Exercise exercise = new Exercise();
//                    exercise.setCode(code);
//                    exercise.setTitle(title);
//
//                    if (!mExerciseList.contains(exercise)){
//                        mExerciseList.add(exercise);
//                    }
//
//                    mDBReferenceSubject.setValue(mExerciseList);
//                }
//            }
//        });

//        Toast.makeText(ExercisesActivity.this, "title: " + subjectTitle + ",\n code: " + subjectCode, Toast.LENGTH_LONG).show();
    }

//    private void getData(DataSnapshot dataSnapshot){
//        List<Exercise> exerciseList = new ArrayList<>();
//
//        DataSnapshot exercises = dataSnapshot.child(CHAPTERS_TITLE);
//
//        for (DataSnapshot ds : exercises.getChildren()){
//            Exercise exercise = ds.getValue(Exercise.class);
//            exerciseList.add(exercise);
//        }
//
//        mExercisesRecyclerViewAdapter.loadNewData(exerciseList);
//    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(ExercisesActivity.this, "Normal tap at position " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(ExercisesActivity.this, "Long tap at position " + position, Toast.LENGTH_SHORT).show();
    }
}
