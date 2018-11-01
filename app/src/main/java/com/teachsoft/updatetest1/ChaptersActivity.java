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
import java.util.HashMap;
import java.util.List;

public class ChaptersActivity extends BaseActivity  implements ChaptersRecyclerItemClickListener.OnRecyclerClickListener {

    private Button mButtonAddChapter;
    private EditText mEditTextChapterInput;

//    private List<Chapter> mChapterList = null;
    private HashMap<String, Chapter> mChapterHashMap = null;

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    DatabaseReference mDBReferenceCurrentSubject;

    private ChaptersRecyclerViewAdapter mChaptersRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        Intent intent = getIntent();
        Subject subject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);

        mButtonAddChapter = findViewById(R.id.buttonAddChapter);
        mEditTextChapterInput = findViewById(R.id.editTextChapterInput);

        RecyclerView recyclerViewChapters = findViewById(R.id.recyclerViewChapters);
        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewChapters.addOnItemTouchListener(new ChaptersRecyclerItemClickListener(ChaptersActivity.this, recyclerViewChapters, ChaptersActivity.this));

        mChaptersRecyclerViewAdapter = new ChaptersRecyclerViewAdapter(ChaptersActivity.this, new ArrayList<Chapter>());
        recyclerViewChapters.setAdapter(mChaptersRecyclerViewAdapter);

        String code = subject.getCode();
        String title = subject.getTitle();

        mChapterHashMap = subject.getChapters();

        mDBReferenceCurrentSubject = mFirebaseDB.getReference("Subjects/" + code);
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

//                    if (!mChapterHashMap.contains(chapter)){
                        mChapterHashMap.put(chapter.getCode(), chapter);
//                    }

                    mDBReferenceSubject.setValue(mChapterHashMap);
                }
            }
        });

        Toast.makeText(ChaptersActivity.this, "title: " + title + ",\n code: " + code, Toast.LENGTH_LONG).show();
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
    }
}
