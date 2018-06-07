package com.example.raxxor.findthedigits;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
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

        adapter = new HintsAdapter(GenerateHints.generateNew());
        recyclerView.setAdapter(adapter);
        init();
    }



    public void init(){


        //Number picker
        NumberPicker np = findViewById(R.id.main_numberPicker_left);
        NumberPickerListener listener = new NumberPickerListener();
        NumberPickerListener listener1 = new NumberPickerListener();
        NumberPickerListener listener2 = new NumberPickerListener();
        np.setMaxValue(9);
        np.setMinValue(0);
        np.setValue(0);
        np.setOnScrollListener(listener);
        np.setOnValueChangedListener(listener);
        np = findViewById(R.id.main_numberPicker_middle);
        np.setMaxValue(9);
        np.setMinValue(0);
        np.setValue(0);
        np.setOnScrollListener(listener1);
        np.setOnValueChangedListener(listener1);
        np = findViewById(R.id.main_numberPicker_right);
        np.setMaxValue(9);
        np.setMinValue(0);
        np.setValue(0);
        np.setOnScrollListener(listener2);
        np.setOnValueChangedListener(listener2);
    }


    private class NumberPickerListener implements NumberPicker.OnScrollListener, NumberPicker.OnValueChangeListener{
        private int scrollState=SCROLL_STATE_IDLE;
        private NumberPicker view;


        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            this.view=picker;
            if(scrollState==SCROLL_STATE_IDLE){
                update();
            }
        }


        @Override
        public void onScrollStateChange(NumberPicker view, int scrollState) {
            this.scrollState=scrollState;
            this.view=view;

            if(scrollState==SCROLL_STATE_IDLE ){
                update();
            }
        }

        void update(){
            switch (view.getId()){
                case R.id.main_numberPicker_left:
                    if(view.getValue()==GenerateHints.correct.get(0)){
                        view.setBackgroundColor(Color.GREEN);
                    }
                    else{
                        view.setBackgroundColor(Color.RED);
                    }
                    break;
                case R.id.main_numberPicker_middle:
                    if(view.getValue()==GenerateHints.correct.get(1)){
                        view.setBackgroundColor(Color.GREEN);
                    }
                    else{
                        view.setBackgroundColor(Color.RED);
                    }
                    break;
                case R.id.main_numberPicker_right:
                    if(view.getValue()==GenerateHints.correct.get(2)){
                        view.setBackgroundColor(Color.GREEN);
                    }
                    else{
                        view.setBackgroundColor(Color.RED);
                    }
                    break;


            }

        }
    }



    private static class GenerateHints {

        static ArrayList<Integer> correct;


        static ArrayList<Hint> generateNew(){ //generate new numbers
            ArrayList<Hint> allHints = new ArrayList<>();
            ArrayList<Integer> allNumbers = new ArrayList<>();
            for(int x = 0;x<10;x++){
                allNumbers.add(x);
            }
            // shuffle
            Collections.shuffle(allNumbers);
            correct = new ArrayList<>(allNumbers.subList(0,3));
            ArrayList<Integer> incorrect = new ArrayList<>(allNumbers.subList(3,10));


            /*
            Generate 5 hints as arrays, statically
            One Number is correct and well placed
            */
            Hint hint = new Hint("One Number is correct and well placed",
                    new int[]{incorrect.get(0),incorrect.get(3),correct.get(2)});
            allHints.add(hint);

            //One Number is correct but wrong placed
            Hint hint1 = new Hint("One Number is correct, but in the wrong place",
                    new int[]{incorrect.get(0),incorrect.get(2),correct.get(1)} );
            allHints.add(hint1);

            //Two Numbers are correct but Wrong Places
            Hint hint2 = new Hint("Two Numbers are correct, but in the wrong Places",
                    new int[]{correct.get(2),correct.get(0),incorrect.get(0)});
            allHints.add(hint2);

            Hint hint3 = new Hint("Nothing is correct",
                    new int[]{incorrect.get(1),incorrect.get(4),incorrect.get(3)});
            allHints.add(hint3);

            Hint hint4 = new Hint("One Number is correct, but in the wrong place",
                    new int[]{incorrect.get(1),incorrect.get(3),correct.get(0)});
            allHints.add(hint4);

            //shuffle order
            Collections.shuffle(allHints);
            return allHints;
        }

    }

}
