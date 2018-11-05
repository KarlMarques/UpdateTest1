package com.teachsoft.updatetest1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChapterConfigurationActivity extends BaseActivity {

    private Button mButtonAddChapter;
    private EditText mEditTextCodeInput;
    private EditText mEditTextTitleInput;

    private Subject mCurrentSubject;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_configuration);

        Intent intent = getIntent();
        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);

        mButtonAddChapter = findViewById(R.id.buttonAddChapter);
        mEditTextCodeInput = findViewById(R.id.editTextCodeInput);
        mEditTextTitleInput = findViewById(R.id.editTextTitleInput);

        mButtonAddChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = mEditTextCodeInput.getText().toString();
                String title = mEditTextTitleInput.getText().toString();

                if (!code.equals("") && !title.equals("")){
                    DatabaseReference dbReferenceChapter = mFirebaseDB.getReference(SUBJECTS_TITLE).child(mCurrentSubject.getCode());

                    Chapter chapter = new Chapter();
                    chapter.setCode(code);
                    chapter.setTitle(title);

                    mCurrentSubject.setChapter(code, chapter);

                    dbReferenceChapter.setValue(mCurrentSubject);
                    Toast.makeText(ChapterConfigurationActivity.this, "Chapter created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
