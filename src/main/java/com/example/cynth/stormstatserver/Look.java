package com.example.cynth.stormstatserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Look extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        name = (EditText)this.findViewById(R.id.editText); // associates xml objects into java
        out = (TextView)this.findViewById(R.id.textView4);
    }
    EditText name;
    TextView out;

    public void look(View view) {
        String n = name.getText().toString().toUpperCase();
        // find and print out the storm in a string
        if (MainActivity.database.get(n) != null) {
            out.setText(MainActivity.database.get(n).toString());
        } else {
            out.setText("Storm not found.");
        }
    }

}
