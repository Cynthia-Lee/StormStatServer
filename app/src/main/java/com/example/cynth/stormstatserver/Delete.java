package com.example.cynth.stormstatserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        name = (EditText)this.findViewById(R.id.editText2); // associates xml objects into java
        out = (TextView)this.findViewById(R.id.textView5);
    }
    EditText name;
    TextView out;

    public void delete(View view) {
        String n = name.getText().toString().toUpperCase();
        if (MainActivity.database.get(n) != null) {
            MainActivity.database.remove(n);
            out.setText("Storm " + n + " has been deleted.\n");
        } else {
            out.setText("Storm not found, cannot delete.");

        }
    }
}
