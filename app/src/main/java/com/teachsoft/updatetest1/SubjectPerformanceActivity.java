package com.teachsoft.updatetest1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class SubjectPerformanceActivity extends BaseActivity {

    private GraphView mGraphView;
    private Subject mCurrentSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_performance);

        Intent intent = getIntent();
        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);
        String subjectCode = mCurrentSubject.getCode();

        mGraphView = findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        mGraphView.addSeries(series);

        // titles
        mGraphView.setTitle("Chart Title");
        mGraphView.getGridLabelRenderer().setVerticalAxisTitle("Vertical Axis");
        mGraphView.getGridLabelRenderer().setHorizontalAxisTitle("Horizontal Axis");

        // optional styles
        //mGraphView.setTitleTextSize(40);
        //mGraphView.setTitleColor(Color.BLUE);
        //mGraphView.getGridLabelRenderer().setVerticalAxisTitleTextSize(40);
        mGraphView.getGridLabelRenderer().setVerticalAxisTitleColor(Color.BLUE);
        //mGraphView.getGridLabelRenderer().setHorizontalAxisTitleTextSize(40);
        mGraphView.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.BLUE);
    }
}
