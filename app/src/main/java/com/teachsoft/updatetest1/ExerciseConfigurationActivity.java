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

import java.util.HashMap;

//public class ExerciseConfigurationActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_exercise_configuration);
//    }
//}

public class ExerciseConfigurationActivity extends BaseActivity {

    private EditText mEditTextCodeInput;
    private EditText mEditTextTitleInput;
    private EditText mEditTextQuestionInput;
    private EditText mEditTextAltAInput;
    private EditText mEditTextAltBInput;
    private EditText mEditTextAltCInput;
    private EditText mEditTextAltDInput;
    private EditText mEditTextAltEInput;
    private EditText mEditTextAnswerInput;

    private Button mButtonAddExercise;

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

        mEditTextCodeInput = findViewById(R.id.editTextCodeInput);
        mEditTextTitleInput = findViewById(R.id.editTextTitleInput);
        mEditTextQuestionInput = findViewById(R.id.editTextQuestionInput);
        mEditTextAltAInput = findViewById(R.id.editTextAltAInput);
        mEditTextAltBInput = findViewById(R.id.editTextAltBInput);
        mEditTextAltCInput = findViewById(R.id.editTextAltCInput);
        mEditTextAltDInput = findViewById(R.id.editTextAltDInput);
        mEditTextAltEInput = findViewById(R.id.editTextAltEInput);
        mEditTextAnswerInput = findViewById(R.id.editTextAnswerInput);

        mButtonAddExercise = findViewById(R.id.buttonAddExercise);

        mButtonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = mEditTextCodeInput.getText().toString();
                String title = mEditTextTitleInput.getText().toString();
                String question = mEditTextQuestionInput.getText().toString();
                String altA = mEditTextAltAInput.getText().toString();
                String altB = mEditTextAltBInput.getText().toString();
                String altC = mEditTextAltCInput.getText().toString();
                String altD = mEditTextAltDInput.getText().toString();
                String altE = mEditTextAltEInput.getText().toString();
                String answer = mEditTextAnswerInput.getText().toString();

                Boolean fieldsComplete = !code.isEmpty() &&
                        !question.isEmpty() &&
                        !altA.isEmpty() &&
                        !altB.isEmpty() &&
                        !altC.isEmpty() &&
                        !altD.isEmpty() &&
                        !altE.isEmpty() &&
                        !answer.isEmpty();

                if (fieldsComplete){
                    DatabaseReference dbReferenceCurrentSubject = mFirebaseDB.getReference(SUBJECTS_TITLE).child(mCurrentSubject.getCode());

                    Exercise exercise = new Exercise();
                    exercise.setCode(code);
                    exercise.setTitle(title);
                    exercise.setQuestion(question);
                    exercise.setAnswer(answer);

                    HashMap<String, String> alternatives = new HashMap<>();
                    alternatives.put("a", altA);
                    alternatives.put("b", altB);
                    alternatives.put("c", altC);
                    alternatives.put("d", altD);
                    alternatives.put("e", altE);
                    exercise.setAlternatives(alternatives);

                    mCurrentChapter.setExercise(code, exercise);
                    mCurrentSubject.setChapter(mCurrentChapter.getCode(), mCurrentChapter);

                    dbReferenceCurrentSubject.setValue(mCurrentSubject);
                    Toast.makeText(ExerciseConfigurationActivity.this, "Exercise created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
