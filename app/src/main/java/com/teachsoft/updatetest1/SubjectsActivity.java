package com.teachsoft.updatetest1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends BaseActivity implements SubjectsRecyclerItemClickListener.OnRecyclerClickListener {

    private Button mButtonAddSubject;

    private List<Subject> mSubjectList = null;

    private SubjectsRecyclerViewAdapter mSubjectsRecyclerViewAdapter;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    DatabaseReference mDBReferenceSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        mButtonAddSubject = findViewById(R.id.buttonAddSubject);

        RecyclerView recyclerViewSubjects = findViewById(R.id.recyclerViewSubjects);
        recyclerViewSubjects.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSubjects.addOnItemTouchListener(new SubjectsRecyclerItemClickListener(SubjectsActivity.this, recyclerViewSubjects, SubjectsActivity.this));

        mSubjectsRecyclerViewAdapter = new SubjectsRecyclerViewAdapter(SubjectsActivity.this, new ArrayList<Subject>());
        recyclerViewSubjects.setAdapter(mSubjectsRecyclerViewAdapter);

        mDBReferenceSubjects = mFirebaseDB.getReference(SUBJECTS_TITLE);
        mDBReferenceSubjects.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        mButtonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectsActivity.this, SubjectConfigurationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Intent intent = new Intent(SubjectsActivity.this, ChaptersActivity.class);
        intent.putExtra(CURRENT_SUBJECT, mSubjectsRecyclerViewAdapter.getSubject(position));
        startActivity(intent);
    }

    private void getData(DataSnapshot dataSnapshot){
        mSubjectList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Subject subject = ds.getValue(Subject.class);
            mSubjectList.add(subject);
        }

        mSubjectsRecyclerViewAdapter.loadNewData(mSubjectList);
    }
}
