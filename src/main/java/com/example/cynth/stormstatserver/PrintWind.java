package com.example.cynth.stormstatserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class PrintWind extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_wind);
        pw = (TextView)this.findViewById(R.id.PW); // associates xml objects into java
        pw.setText(printWindAndroid());
    }
    TextView pw;

    public String printWindAndroid() {
        ArrayList<Storm> stormList = new ArrayList<Storm>(MainActivity.database.values());
        Collections.sort(stormList, new WindSpeedComparator());
        String s = "";
        s += String.format("%-28s %-22s %s%n", "Name", "Windspeed", "RainFall");
        // System.out.format("%-20s %-14s %s%n", "Name", "Windspeed", "RainFall");
        // System.out.println("Name\tWindspeed\tRainfall");
        s += "-------------------------------------------------------------------------------\n";
        for (int i = 0; i < stormList.size(); i++) {
            // System.out.println(stormList.get(i).getName() + "\t" +
            // stormList.get(i).getWindspeed() + "\t"
            // + stormList.get(i).getPrecipitation());
            s = s + String.format("%-34s %-30s %s%n", stormList.get(i).getName(),
                    stormList.get(i).getWindspeed(), stormList.get(i).getPrecipitation());
        }
        return s;
    }
}
