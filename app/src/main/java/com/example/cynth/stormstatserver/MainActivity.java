package com.example.cynth.stormstatserver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) this.findViewById(R.id.tv); // associates xml objects into java
        try {
            //FileInputStream file = new FileInputStream("hurricane.ser");
            //ObjectInputStream inStream = new ObjectInputStream(file);
            FileInputStream f = openFileInput("hurricane.ser");
            ObjectInputStream inStream = new ObjectInputStream(f);

            // hurricane.ser exists, LOAD from file
            try {
                database = (HashMap<String, Storm>) inStream.readObject();
                tv.setText("hurricane.ser was found and loaded.");
            } catch (ClassNotFoundException e) {
                tv.setText("Class could not be found. ClassNotFoundError.");
            }
            inStream.close();
        } catch (FileNotFoundException ex) { // create a new HashMap to use
            tv.setText("No previous data found.");
            database = new HashMap<String, Storm>();
        } catch (IOException ex) {
            tv.setText("File has input and/or output error.");
        }
    }
    TextView tv;
    public static HashMap<String, Storm> database;
    /*
    {
        try {
            FileInputStream file = new FileInputStream("hurricane.ser");
            ObjectInputStream inStream = new ObjectInputStream(file);
            // hurricane.ser exists, LOAD from file
            try {
                database = (HashMap<String, Storm>) inStream.readObject();
                tv.setText("hurricane.ser was found and loaded.");
            } catch (ClassNotFoundException e) {
                tv.setText("Class could not be found. ClassNotFoundError.");
            }
            inStream.close();
        } catch (FileNotFoundException ex) { // create a new HashMap to use
            tv.setText("No previous data found.");
            database = new HashMap<String, Storm>();
        } catch (IOException ex) {
            tv.setText("File has input and/or output error.");
        }
    }
    */

    public void switchToAdd(View view) {
        Intent i = new Intent(getApplicationContext(),Add.class);
        startActivity(i);
        tv.setText("");
    }

    public void switchToPrintRain(View view) {
        Intent i = new Intent(getApplicationContext(),PrintRain.class);
        startActivity(i);
        tv.setText("");
    }

    public void switchToLook(View view) {
        Intent i = new Intent(getApplicationContext(),Look.class);
        startActivity(i);
        tv.setText("");
    }

    public void switchToDelete(View view) {
        Intent i = new Intent(getApplicationContext(),Delete.class);
        startActivity(i);
        tv.setText("");
    }

    public void switchToEdit(View view) {
        Intent i = new Intent(getApplicationContext(),Edit.class);
        startActivity(i);
        tv.setText("");
    }

    public void switchToPrintWind(View view) {
        Intent i = new Intent(getApplicationContext(),PrintWind.class);
        startActivity(i);
        tv.setText("");
    }

    public void switchToPrintTable(View view) {
        Intent i = new Intent(getApplicationContext(),PrintTable.class);
        startActivity(i);
        tv.setText("");
    }

    public void saveAndQuit(View view) {
        try {
            //FileOutputStream file = new FileOutputStream("hurricane.ser");
            FileOutputStream file = getApplicationContext().openFileOutput("hurricane.ser", Context.MODE_PRIVATE);

            ObjectOutputStream outStream = new ObjectOutputStream(file);
            // the following line will save the object in the file
            outStream.writeObject(database);
            outStream.flush();
            outStream.close();
            tv.setText(
                    "File saved to hurricane.ser; feel free to use the weather channel in the meantime.\nApp will close in 2 seconds.");
            //repeat = false;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // Actions to do after 10 seconds
                    System.exit(0);
                }
            }, 2000);
        } catch (FileNotFoundException ex) {
            tv.setText("Could not find the file.");
        } catch (IOException ex) {
            tv.setText("File input and/or output error.");
        }
    }

    public void quitAndDelete(View view) {
        //File f = new File("hurricane.ser");
        //f.delete();
        deleteFile("hurricane.ser");
        tv.setText(
                "Goodbye, it's hard to hold an (electric) candle in the cold November (and April) rain!\nApp will close in 2 seconds.");
        //repeat = false;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                System.exit(0);
            }
        }, 2000);

    }

}
