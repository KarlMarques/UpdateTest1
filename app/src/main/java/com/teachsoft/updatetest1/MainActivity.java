package com.teachsoft.updatetest1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SubjectsRecyclerItemClickListener.OnRecyclerClickListener {

    private Button mButtonSendData;
    private SubjectsRecyclerViewAdapter mSubjectsRecyclerViewAdapter;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    private List<ClassSubject> mClassSubjectList = null;
    DatabaseReference mDBReferenceSubjects = mFirebaseDB.getReference("Subjects");

    private EditText mEditTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSendData = findViewById(R.id.buttonSendData);
        mEditTextInput = findViewById(R.id.editTextInput);

        RecyclerView recyclerViewSubjects = findViewById(R.id.recyclerViewSubjects);
        recyclerViewSubjects.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewSubjects.addOnItemTouchListener(new SubjectsRecyclerItemClickListener(MainActivity.this, recyclerViewSubjects, MainActivity.this));

        mSubjectsRecyclerViewAdapter = new SubjectsRecyclerViewAdapter(MainActivity.this, new ArrayList<ClassSubject>());
        recyclerViewSubjects.setAdapter(mSubjectsRecyclerViewAdapter);

        mButtonSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEditTextInput.getText().toString();

                if (!title.equals("")) {
                    DatabaseReference mDBReferenceSubject = mDBReferenceSubjects.child(title);
                    title = title + " Title";
                    ClassSubject classSubject = new ClassSubject(title);
                    mDBReferenceSubject.setValue(classSubject);
                }
            }
        });

        mDBReferenceSubjects.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }

    @Override
    public void onItemClick(View view, int position) {
//        Log.d(TAG, "onItemClick: starts");
        Toast.makeText(MainActivity.this, "Normal tap at position " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
//        Log.d(TAG, "onItemLongClick: starts");
        Toast.makeText(MainActivity.this, "Long tap at position " + position, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MySubjectsActivity.this, CurrentSubjectActivity.class);
//        intent.putExtra(CURRENT_SUBJECT, mMySubjectsRecyclerViewAdapter.getSubject(position));
//        startActivity(intent);
    }


    private void getData(DataSnapshot dataSnapshot){
        mClassSubjectList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()){
            String title = ds.getValue(ClassSubject.class).getTitle();

            ClassSubject classSubject = new ClassSubject();
            classSubject.setTitle(title);

            mClassSubjectList.add(classSubject);
        }

        mSubjectsRecyclerViewAdapter.loadNewData(mClassSubjectList);
        Toast.makeText(MainActivity.this, "ClassSubjectList size: " + mClassSubjectList.size(), Toast.LENGTH_SHORT).show();
    }
}
