package com.labs.praveen.androidcontrollabsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import android.content.res.Resources;
import android.util.TypedValue;

import org.w3c.dom.Text;

public class JavaUserInterface extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RelativeLayout javaUILayout = new RelativeLayout(this);
        Button javaUIBtnOne = new Button(this);
        final EditText userName = new EditText(this);
        javaUIBtnOne.setId(1);
        userName.setId(2);

        javaUILayout.setBackgroundColor(Color.GRAY);
        javaUIBtnOne.setBackgroundColor(Color.LTGRAY);
        javaUIBtnOne.setText("Click Here");

        RelativeLayout.LayoutParams btnLayoutProperties =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

        btnLayoutProperties.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnLayoutProperties.addRule(RelativeLayout.CENTER_VERTICAL);



        RelativeLayout.LayoutParams userNameProperties =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
        userNameProperties.addRule(RelativeLayout.ABOVE,javaUIBtnOne.getId());
        userNameProperties.addRule(RelativeLayout.CENTER_HORIZONTAL);
        userNameProperties.setMargins(0,0,0, 50);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());

        userName.setWidth(px);





        javaUILayout.addView(javaUIBtnOne, btnLayoutProperties);
        javaUILayout.addView(userName, userNameProperties);
        setContentView(javaUILayout);


        javaUIBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().isEmpty())
                {
                    Snackbar sbar = Snackbar
                            .make(javaUILayout, "FIELD IS EMPTY", Snackbar.LENGTH_LONG);

                    sbar.setActionTextColor(Color.WHITE);

                    View sview = sbar.getView();
                    TextView txtView = (TextView) sview.findViewById(R.id.snackbar_text);
                    txtView.setTextColor(Color.MAGENTA);
                    txtView.setBackgroundColor(Color.BLUE);
                    sbar.show();

                }
                else {

                    //fragUserInterface
                    Intent fragmentIntent = new Intent(JavaUserInterface.this, fragUserInterface.class);
                    fragmentIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(fragmentIntent);
                    finish();
                }
            }
        });


    }
}
