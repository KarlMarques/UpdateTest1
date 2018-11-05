package com.teachsoft.updatetest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//public class ExerciseConfigurationActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_exercise_configuration);
//    }
//}

public class ExerciseConfigurationActivity extends BaseActivity {

    private Button mButtonAddExercise;
    private EditText mEditTextCodeInput;
    private EditText mEditTextTitleInput;

    private Subject mCurrentSubject;
    private Chapter mCurrentChapter;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_configuration);

        Intent intent = getIntent();
        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);
        mCurrentChapter = (Chapter) intent.getSerializableExtra(CURRENT_CHAPTER);

        mButtonAddExercise = findViewById(R.id.buttonAddExercise);
        mEditTextCodeInput = findViewById(R.id.editTextCodeInput);
        mEditTextTitleInput = findViewById(R.id.editTextTitleInput);

        mButtonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = mEditTextCodeInput.getText().toString();
                String title = mEditTextTitleInput.getText().toString();

                if (!code.equals("") && !title.equals("")){
                    DatabaseReference dbReferenceCurrentSubject = mFirebaseDB.getReference(SUBJECTS_TITLE).child(mCurrentSubject.getCode());

                    Exercise exercise = new Exercise();
                    exercise.setCode(code);
                    exercise.setTitle(title);

                    mCurrentChapter.setExercise(code, exercise);
                    mCurrentSubject.setChapter(mCurrentChapter.getCode(), mCurrentChapter);

                    dbReferenceCurrentSubject.setValue(mCurrentSubject);
                    Toast.makeText(ExerciseConfigurationActivity.this, "Exercise created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
