package com.example.liu.androidtest;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



/**
 * Created by liu on 15/11/17.
 */

public class Screen2 extends Activity{
    private ArrayList<Talk> remmaningTalks = new ArrayList<>();
    private ArrayList<Talk> used = new ArrayList<>();
    private ArrayList<Talk> talks60 = new ArrayList<>();
    private ArrayList<Talk> talks45 = new ArrayList<>();
    private ArrayList<Talk> talks30 = new ArrayList<>();
    private ArrayList<Talk> talks5 = new ArrayList<>();
    private Integer hour1;
    private Integer hour2;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.layout);
        TextView textView = (TextView) findViewById(R.id.textViewSch);
        //calls the method that organizes the talks
        textView.setText(talkOrganizer(fileReader()));
    }

    //method that reads the "proposals.txt" file and organizes them into an ArrayList
    public ArrayList<Talk> fileReader() {
        String organizer = "";
        ArrayList<Talk> talks = new ArrayList<>();

        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("proposals.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String talkInfo = null;
            while ((talkInfo = bufferedReader.readLine()) != null) {
                //executes while there is line to be read
                Talk talk = new Talk();
                Integer time = 0;
                Integer timeAux = -1;

                for (int i = 0; i < talkInfo.length(); i++) {
                    timeAux = -1;
                    //check if there are numbers in the line information
                    if (Character.isDigit(talkInfo.charAt(i)))
                        timeAux = Character.getNumericValue(talkInfo.charAt(i));
                    if (timeAux != -1)
                        //calculates the time from the numbers drawn in the reading
                        time = (time * 10) + timeAux;
                }
                //assigns value 5 to rows that don't have numbers
                if (time == 0)
                    time = 5;

                talk.setTitle(talkInfo);
                talk.setTime(time);

                talks.add(talk);
            }

            talks.remove(talks.size()-1);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return talks;
    }

    //method that organizes the tracks (A and B)
    public String talkOrganizer(ArrayList<Talk> list) {
        String organizer = "";
        String trackAMorning = "";
        String trackAEvening = "";
        String trackBMorning = "";
        String trackBEvening = "";
        Double counter = 0.0;

        // method to organize the amount of talks according to theirs duration.
        for (Talk talk : list) {
            if (talk.getTime() == 60)
                talks60.add(talk);
            if (talk.getTime() == 45)
                talks45.add(talk);
            if (talk.getTime() == 30)
                talks30.add(talk);
            if (talk.getTime() == 5)
                talks5.add(talk);
        }

        for (Talk talk : talks60) {
            remmaningTalks.add(talk);
        }
        for (Talk talk : talks30) {
            remmaningTalks.add(talk);
        }
        for (Talk talk : talks45) {
            remmaningTalks.add(talk);
        }
        for (Talk talk : talks5) {
            remmaningTalks.add(talk);
        }

        //organization of tracks, according to the shift (morning/ evening)
        trackAMorning = organizer(remmaningTalks, "M");
        trackBMorning = organizer(remmaningTalks, "M");
        trackAEvening = organizer(remmaningTalks, "E");

        hour1= 13;
        for (Talk talk : remmaningTalks) {

            talk.setStartTime(hourCounter("E", counter));
            counter += talk.getTime();
            trackBEvening += talk.toString();
        }

        trackBEvening+= hourCounter("E", counter) + "  Evento de Networking\n";

        //string that shows the schedule
        organizer = "TRACK A: \n" + trackAMorning + "12:00 ALMOÇO\n" + trackAEvening + "\n" + "TRACK B: \n"
                + trackBMorning + "12:00 ALMOÇO\n" + trackBEvening;
        System.out.println(organizer);

        return organizer;
    }

    //method that separates the talks in shifts.
    public String organizer(ArrayList<Talk> remmaningTalksToOrganize, String option) {
        String track = "";
        Double counter = 0.0;
        Boolean itHas;

        used = new ArrayList<>();
        remmaningTalks =  new ArrayList<>();

        if(option.equals("M"))  hour1 = 9;
        else hour1=13;

        for (Talk talk : remmaningTalksToOrganize) {

            talk.setStartTime(hourCounter(option, counter));

            counter += talk.getTime();
            track += talk.toString();
            used.add(talk);
            if(option.equals("M")){
                if (counter == 180){
                    break;
                }
            }else{
                if (counter >= 180){
                    break;
                }
            }

        }
        if(option.equals("E")) track+= hourCounter("E", counter) + "  Evento de Networking\n";


        for (Talk talk : remmaningTalksToOrganize) {
            itHas = false;
            for (Talk talkCh : used) {
                if (talk.equals(talkCh)) {
                    itHas = true;
                }
            }
            if (!itHas)       remmaningTalks.add(talk);
        }

        return track;
    }

    //method responsible for calculating the starting time of each talk.
    public String hourCounter(String option, Double counter) {
        String hour = "";
        int hr = 0;
        Double min = 0.0;

        if(counter>=0) {
            hr = (int) (counter / 60);
            hour1 += hr;
            min = counter / 60;
            min = (min - hr) * 60;
            hour2 = min.intValue();

            if (hour2 < 10) {
                hour += hour1 + ":0" + hour2;
            } else
                hour += hour1 + ":" + hour2;

            if (option.equals("M")) hour1 = 9;
            else hour1 = 13;

            return hour;
        }else return "00:00";
    }

}
