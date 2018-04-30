package com.example.cynth.stormstatserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        n = (EditText)this.findViewById(R.id.editText3); // associates xml objects into java
        d = (EditText)this.findViewById(R.id.editText8);
        p = (EditText)this.findViewById(R.id.editText4); // associates xml objects into java
        w = (EditText)this.findViewById(R.id.editText7); // associates xml objects into java
        out = (TextView)this.findViewById(R.id.textView7);
    }
    EditText n;
    EditText d;
    EditText p;
    EditText w;
    TextView out;

    public void edit (View view) {
        boolean valid = false;
        // System.out.print("Please enter name: ");
        String name = n.getText().toString().toUpperCase();
        if (MainActivity.database.get(name) != null) {
            // System.out.print("Please enter date: ");
            String date = d.getText().toString();
            if (date.equals("")) {
                valid = true;
            } else if (StormStatServer.checkDate(date) == false) {
                out.setText("Invalid date format\n");
                valid = false;
            } else {
                MainActivity.database.get(name).setDate(date);
                valid = true;
            }
            if (valid) {
                //System.out.print("Please enter precipitation (cm): ");
                String sPre = p.getText().toString();
                if (!sPre.equals("")) {
                    Double pre = Double.parseDouble(sPre);
                    if (pre < 0) {
                        out.append("Precipitation cannot be a negative number!\n");
                        valid = false;
                    } else {
                        MainActivity.database.get(name).setPrecipitation(pre);
                        valid = true;
                    }
                }
            }
            if (valid) {
                //System.out.print("Please enter windspeed (km/h): ");
                String sSpeed = w.getText().toString();
                if (!sSpeed.equals("")) {
                    Double speed = Double.parseDouble(sSpeed);
                    if (speed < 0) {
                        out.append("Windspeed cannot be a negative number!\n");
                        valid = false;
                    } else {
                        MainActivity.database.get(name).setWindspeed(speed);
                        valid = true;
                    }
                }
            }
            if (valid) {
                out.setText(name + " added.");
            }

        } else {
            out.setText("Key not found.\n");
        }
    }

}
