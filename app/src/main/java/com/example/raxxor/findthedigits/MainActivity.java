package com.example.raxxor.findthedigits;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    HintsAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.main_hints_recyclerView);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HintsAdapter();
        recyclerView.setAdapter(adapter);


    }


    private static class generateNumbers{
        ArrayList<Integer> allNumbers = new ArrayList<>();
        ArrayList<Integer> correct = new ArrayList();
        ArrayList<Integer> incorrect = new ArrayList<>();
        ArrayList<Integer> hint1 = new ArrayList(),hint2 = new ArrayList(),hint3 = new ArrayList(),hint4 = new ArrayList(),hint5 = new ArrayList();

        generateNumbers(){
            for(int x = 0;x<10;x++){
                allNumbers.add(x);
            }
            generateNew();
        }
        void generateNew(){ //generate new numbers
            // shuffle
            Collections.shuffle(allNumbers);
            correct= (ArrayList) allNumbers.subList(0,3);
            incorrect= (ArrayList<Integer>) allNumbers.subList(3,10);

            /*
            Generate 5 hints as arrays
            One Number is correct and well placed
            */
            hint1.add(0,incorrect.get(0));
            hint1.add(1,incorrect.get(3));
            hint1.add(2,correct.get(2));

            //One Number is correct but wrong placed
            hint2.add(0,incorrect.get(0));
            hint2.add(1,incorrect.get(2));
            hint2.add(2,correct.get(1));


            //Two Numbers are correct but Wrong Places
            hint3.add(0,correct.get(2));
            hint3.add(1,correct.get(0));
            hint3.add(2,incorrect.get(0));


            //Nothing is correct
            hint4.add(0,incorrect.get(1));
            hint4.add(1,incorrect.get(4));
            hint4.add(2,incorrect.get(3));

            //One Number is correct but wrong place
            hint5.add(0,incorrect.get(1));
            hint5.add(1,incorrect.get(3));
            hint5.add(2,correct.get(0));
        }

    }

}
