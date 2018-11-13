package com.teachsoft.updatetest1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class ChapterPerformanceActivity extends BaseActivity {
    private GraphView mGraphView;
    private Subject mCurrentSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_performance);

        Intent intent = getIntent();
        mCurrentSubject = (Subject) intent.getSerializableExtra(CURRENT_SUBJECT);
        String subjectCode = mCurrentSubject.getCode();

        mGraphView = findViewById(R.id.graphViewChapter);
        initGraph();
    }

    void initGraph() {
        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
        });
        series1.setSpacing(30);
        series1.setAnimated(true);
        mGraphView.addSeries(series1);

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, -5),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 4),
                new DataPoint(4, 1),
        });
        series2.setColor(Color.RED);
        series2.setSpacing(30);
        series2.setAnimated(true);
        mGraphView.addSeries(series2);

        mGraphView.getViewport().setXAxisBoundsManual(true);
        mGraphView.getViewport().setMinX(-2);
        mGraphView.getViewport().setMaxX(6);

        // legend
        series1.setTitle("foo");
        series2.setTitle("bar");
        mGraphView.getLegendRenderer().setVisible(true);
        mGraphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }
}
