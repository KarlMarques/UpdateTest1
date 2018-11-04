package com.teachsoft.updatetest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class ChaptersActivity extends BaseActivity implements ChaptersRecyclerItemClickListener.OnRecyclerClickListener {

    private Button mButtonAddChapter;
    private EditText mEditTextChapterInput;

    private List<Chapter> mChapterList = null;

    private Subject mCurrentSubject;

    private ChaptersRecyclerViewAdapter mChaptersRecyclerViewAdapter;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    DatabaseReference mDBReferenceChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

//        Read params passed by prior activity
        Intent intent = getIntent();
        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);
        String subjectCode = mCurrentSubject.getCode();

        mButtonAddChapter = findViewById(R.id.buttonAddChapter);
        mEditTextChapterInput = findViewById(R.id.editTextChapterInput);

        RecyclerView recyclerViewChapters = findViewById(R.id.recyclerViewChapters);
        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChapters.addOnItemTouchListener(new ChaptersRecyclerItemClickListener(ChaptersActivity.this, recyclerViewChapters, ChaptersActivity.this));

        mChaptersRecyclerViewAdapter = new ChaptersRecyclerViewAdapter(ChaptersActivity.this, new ArrayList<Chapter>());
        recyclerViewChapters.setAdapter(mChaptersRecyclerViewAdapter);

        mDBReferenceChapters = mFirebaseDB.getReference(SUBJECTS_TITLE).child(subjectCode).child(CHAPTERS_TITLE);
        mDBReferenceChapters.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        mButtonAddChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String code = mEditTextChapterInput.getText().toString();
            String title = code;

            if (!title.equals("")) {
                DatabaseReference mDBReferenceChapter = mDBReferenceChapters.child(code);

                title = title + " Title";

                Chapter chapter = new Chapter();
                chapter.setCode(code);
                chapter.setTitle(title);

                mDBReferenceChapter.setValue(chapter);
            }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Intent intent = new Intent(ChaptersActivity.this, ExercisesActivity.class);
        intent.putExtra(CURRENT_SUBJECT, mCurrentSubject);
        intent.putExtra(CURRENT_CHAPTER, mChaptersRecyclerViewAdapter.getChapter(position));
        startActivity(intent);
    }

    private void getData(DataSnapshot dataSnapshot){
        mChapterList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Chapter chapter = ds.getValue(Chapter.class);
            mChapterList.add(chapter);
        }

        mChaptersRecyclerViewAdapter.loadNewData(mChapterList);
    }
}
