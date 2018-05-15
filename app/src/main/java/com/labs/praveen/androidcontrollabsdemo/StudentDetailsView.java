package com.labs.praveen.androidcontrollabsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentDetailsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_view);
        setTitle("Student Details View");
        Intent i = getIntent();
        String message = i.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView strView = findViewById(R.id.txtView);
        strView.setText(message);

    }
}
