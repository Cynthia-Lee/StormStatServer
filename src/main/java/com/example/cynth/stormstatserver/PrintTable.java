package com.example.cynth.stormstatserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class PrintTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_table);
        print = (TextView)this.findViewById(R.id.textView15);
    }
    TextView print;

    public void showbyWind(View view) {
        ArrayList<Storm> stormList = new ArrayList<Storm>(MainActivity.database.values());
        Collections.sort(stormList, new WindSpeedComparator());
        String s = "";
        //s += String.format("%-28s %-22s %s%n", "Name", "Windspeed", "RainFall");
        // System.out.format("%-20s %-14s %s%n", "Name", "Windspeed", "RainFall");
        // System.out.println("Name\tWindspeed\tRainfall");
        s += "----------------------------------------------------------------------------------------------\n";
        for (int i = 0; i < stormList.size(); i++) {
            // System.out.println(stormList.get(i).getName() + "\t" +
            // stormList.get(i).getWindspeed() + "\t"
            // + stormList.get(i).getPrecipitation());
            s = s + String.format("%-34s %-30s %s%n", stormList.get(i).getName(),
                    stormList.get(i).getWindspeed(), stormList.get(i).getPrecipitation());
        }
        print.setText(s);
    }

    public void showByRain(View view) {
        ArrayList<Storm> stormList = new ArrayList<Storm>(MainActivity.database.values());
        Collections.sort(stormList, new PrecipitationComparator());
        String s = "";
        //s += String.format("%-28s %-22s %s%n", "Name", "Windspeed", "RainFall");
        // System.out.format("%-20s %-14s %s%n", "Name", "Windspeed", "RainFall");
        // System.out.println("Name\tWindspeed\tRainfall");
        s += "----------------------------------------------------------------------------------------------\n";
        for (int i = 0; i < stormList.size(); i++) {
            // System.out.println(stormList.get(i).getName() + "\t" +
            // stormList.get(i).getWindspeed() + "\t"
            // + stormList.get(i).getPrecipitation());
            s = s + String.format("%-34s %-30s %s%n", stormList.get(i).getName(),
                    stormList.get(i).getWindspeed(), stormList.get(i).getPrecipitation());
        }
        print.setText(s);
    }


}
