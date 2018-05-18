///COMMENTS FROM PRAVEEN KUMAR PANDEY
package com.labs.praveen.androidcontrollabsdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    public static final String EXTRA_MESSAGE = "Welcome";

    private Calendar mcalendar = Calendar.getInstance();
    private EditText mdob_et, editFName, editMName, editLName;
    private int day,month,year;
    ImageView imgView;
    Spinner spnView;

    String[] mobileArray = {"Android","IPhone","WindowsMobile"};
    String[] subjects = {"Engg", "Science", "Commerce"};
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4682B4")));

        setTitle("SAVE STUDENT DETAILS");

        editFName = (EditText)findViewById(R.id.fname);
        editMName = (EditText)findViewById(R.id.mname);
        editLName = (EditText)findViewById(R.id.lname);
        spnView = (Spinner) findViewById(R.id.studSpinner);


        editFName.setBackgroundResource(R.drawable.edittext_border);
        editMName.setBackgroundResource(R.drawable.edittext_border);
        editLName.setBackgroundResource(R.drawable.edittext_border);
        spnView.setBackgroundResource(R.drawable.edittext_border);


        ArrayAdapter lstAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, mobileArray);
        ArrayAdapter spinAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, subjects);

        mdob_et=(EditText)findViewById(R.id.dateDOB);

        ListView sView = (ListView) findViewById(R.id.lstView);
        Spinner sSpinner = (Spinner) findViewById(R.id.studSpinner);
        mdob_et.setOnClickListener(mClickListener);
        day=mcalendar.get(Calendar.DAY_OF_MONTH);
        year=mcalendar.get(Calendar.YEAR);
        month=mcalendar.get(Calendar.MONTH);
        sView.setAdapter(lstAdapter);
        sSpinner.setAdapter(spinAdapter);

        imgView = findViewById(R.id.studImage);
        imgView.setBackgroundResource(R.drawable.edittext_border);
        imgView.setOnClickListener(imgClickLister);

    }

        View.OnClickListener imgClickLister = new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent, CAMERA_REQUEST);

            }
        };



        View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            DateDialog();

        }
    };

    public void DateDialog(){


        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
            {
                mdob_et.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }};
        DatePickerDialog dpDialog=new DatePickerDialog(MainActivity.this, listener, year, month, day);
        dpDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CAMERA_REQUEST)
        {
            Bitmap studPhoto = (Bitmap)data.getExtras().get("data");
            imgView.setImageBitmap(studPhoto);
        }
    }


    public void saveStudent(View view)
    {
        EditText firstName = (EditText)findViewById(R.id.fname);
        EditText middleName = (EditText) findViewById(R.id.mname);
        EditText lastName = (EditText) findViewById(R.id.lname);

       if (firstName.getText().toString().isEmpty() || middleName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "please enter name details", Toast.LENGTH_LONG).show();
        }
        else {
            AlertDialog.Builder alertMsg = new AlertDialog.Builder(this);
            alertMsg.create();
            alertMsg.setMessage("Alert Dialog Example");
            alertMsg.setTitle("Alert Dialog");
            alertMsg.show();

            Intent intent = new Intent(this, StudentDetailsView.class);

            String strMsg = firstName.getText().toString() + " " + middleName.getText().toString() + " " + lastName.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, strMsg);

            startActivity(intent);
        }
    }


}
