package com.example.liu.androidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button mainButton = (Button) findViewById(R.id.principal_button);

    }

    public void buttonOnClick(View view){

        Intent intent = new Intent(MainActivity.this, Screen2.class);
        startActivity(intent);
    }


}
