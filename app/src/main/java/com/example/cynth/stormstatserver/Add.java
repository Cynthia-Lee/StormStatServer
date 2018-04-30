package com.example.cynth.stormstatserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = (EditText)this.findViewById(R.id.name); // associates xml objects into java
        date = (EditText)this.findViewById(R.id.date); // associates xml objects into java
        precip = (EditText)this.findViewById(R.id.editText5); // associates xml objects into java
        ws = (EditText)this.findViewById(R.id.editText6); // associates xml objects into java
        out = (TextView) this.findViewById(R.id.textView3); // associates xml objects into java
    }
    EditText name;
    EditText date;
    EditText precip;
    EditText ws;
    TextView out;

    public void add (View view) {
        String n = name.getText().toString().toUpperCase(); // need to make upper case
        // CHECK FORMAT FOR DATE
        String d = date.getText().toString();
        if (StormStatServer.checkDate(d) == true) {
            double pre = Double.parseDouble(precip.getText().toString());
            if (pre < 0) {
                out.setText("Precipitation cannot be a negative number!");
            } else {
                // System.out.print("Please enter windspeed (km/h): ");
                double speed = Double.parseDouble(ws.getText().toString());
                if (speed < 0) {
                    out.append("Windspeed should not be a negative number!");
                } else {
                    Storm st = new Storm(n, pre, speed, d);
                    MainActivity.database.put(st.getName(), st);
                    out.setText(st.getName() + " added.");
                }
            }
        } else {
            out.setText("The date entered was in an invalid format.");
        }




    }

}
