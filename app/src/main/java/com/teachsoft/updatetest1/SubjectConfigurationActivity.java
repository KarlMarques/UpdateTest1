package com.teachsoft.updatetest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubjectConfigurationActivity extends BaseActivity {

    private Button mButtonAddSubject;
    private EditText mEditTextCodeInput;
    private EditText mEditTextTitleInput;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_configuration);

        mButtonAddSubject = findViewById(R.id.buttonAddSubject);
        mEditTextCodeInput = findViewById(R.id.editTextCodeInput);
        mEditTextTitleInput = findViewById(R.id.editTextTitleInput);

        mButtonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = mEditTextCodeInput.getText().toString();
                String title = mEditTextTitleInput.getText().toString();

                if (!code.equals("") && !title.equals("")){
                    DatabaseReference dbReferenceCurrentSubject = mFirebaseDB.getReference(SUBJECTS_TITLE).child(code);

                    Subject subject = new Subject();
                    subject.setCode(code);
                    subject.setTitle(title);

                    dbReferenceCurrentSubject.setValue(subject);
                    Toast.makeText(SubjectConfigurationActivity.this, "Subject created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
