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

public class ChaptersActivity extends BaseActivity  implements ChaptersRecyclerItemClickListener.OnRecyclerClickListener {

    private Button mButtonAddChapter;
    private EditText mEditTextChapterInput;

    private List<Chapter> mChapterList = null;
    private Subject mCurrentSubject;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    DatabaseReference mDBReferenceCurrentSubject;

    private ChaptersRecyclerViewAdapter mChaptersRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        Intent intent = getIntent();
        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);

        mButtonAddChapter = findViewById(R.id.buttonAddChapter);
        mEditTextChapterInput = findViewById(R.id.editTextChapterInput);

        RecyclerView recyclerViewChapters = findViewById(R.id.recyclerViewChapters);
        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewChapters.addOnItemTouchListener(new ChaptersRecyclerItemClickListener(ChaptersActivity.this, recyclerViewChapters, ChaptersActivity.this));

        mChaptersRecyclerViewAdapter = new ChaptersRecyclerViewAdapter(ChaptersActivity.this, new ArrayList<Chapter>());
        recyclerViewChapters.setAdapter(mChaptersRecyclerViewAdapter);

        String subjectCode = mCurrentSubject.getCode();
        String subjectTitle = mCurrentSubject.getTitle();

        mChapterList = mCurrentSubject.getChapters();

        mDBReferenceCurrentSubject = mFirebaseDB.getReference(SUBJECTS_TITLE).child(subjectCode);
        mDBReferenceCurrentSubject.addValueEventListener(new ValueEventListener() {
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
                    DatabaseReference mDBReferenceSubject = mDBReferenceCurrentSubject.child(CHAPTERS_TITLE);

                    title = title + " Title";

                    Chapter chapter = new Chapter();
                    chapter.setCode(code);
                    chapter.setTitle(title);

                    if (!mChapterList.contains(chapter)){
                        mChapterList.add(chapter);
                    }

                    mDBReferenceSubject.setValue(mChapterList);
                }
            }
        });

        Toast.makeText(ChaptersActivity.this, "title: " + subjectTitle + ",\n code: " + subjectCode, Toast.LENGTH_LONG).show();
    }

    private void getData(DataSnapshot dataSnapshot){
        List<Chapter> chapterList = new ArrayList<>();

        DataSnapshot chapters = dataSnapshot.child(CHAPTERS_TITLE);

        for (DataSnapshot ds : chapters.getChildren()){
            Chapter chapter = ds.getValue(Chapter.class);
            chapterList.add(chapter);
        }

        mChaptersRecyclerViewAdapter.loadNewData(chapterList);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(ChaptersActivity.this, "Normal tap at position " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(ChaptersActivity.this, "Long tap at position " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChaptersActivity.this, ExercisesActivity.class);
//        intent.putExtra(CURRENT_SUBJECT, mCurrentSubject);
//        intent.putExtra(CURRENT_CHAPTER, mChaptersRecyclerViewAdapter.getChapter(position));
        startActivity(intent);
    }
}
