package com.example.raxxor.findthedigits;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {
    generateNumbers gen;
    EditText etInput1,etInput2,etInput3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //set views
        etInput1=(EditText)findViewById(R.id.input1);
        etInput2=(EditText)findViewById(R.id.input2);
        etInput3=(EditText)findViewById(R.id.input3);

        //Add listners
        etInput1.addTextChangedListener(this);
        etInput2.addTextChangedListener(this);
        etInput3.addTextChangedListener(this);
        updateText();
    }




    public void updateText(){
        gen = new generateNumbers();
        TextView text1 = (TextView)findViewById(R.id.hint1);
        TextView text2 = (TextView)findViewById(R.id.hint2);
        TextView text3 = (TextView)findViewById(R.id.hint3);
        TextView text4 = (TextView)findViewById(R.id.hint4);
        TextView text5 = (TextView)findViewById(R.id.hint5);
        String hint1 = String.valueOf(gen.hint1.toString());
        String hint2 = String.valueOf(gen.hint2.toString());
        String hint3 = String.valueOf(gen.hint3.toString());
        String hint4 = String.valueOf(gen.hint4.toString());
        String hint5 = String.valueOf(gen.hint5.toString());
        text1.setText(hint1);
        text2.setText(hint2);
        text3.setText(hint3);
        text4.setText(hint4);
        text5.setText(hint5);
    }

    @Override
    public void onClick(View v) {
        TextView input1 = (TextView) findViewById(R.id.input1);
        TextView input2 = (TextView) findViewById(R.id.input2);
        TextView input3 = (TextView) findViewById(R.id.input3);
        Snackbar bar = Snackbar.make(v, "right!", Snackbar.LENGTH_LONG);
        View snackView = bar.getView();
        TextView txtV = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        txtV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        try {
            if (Integer.parseInt(input1.getText().toString()) == gen.correct.get(0) && Integer.parseInt(input2.getText().toString()) == gen.correct.get(1) && Integer.parseInt(input3.getText().toString()) == gen.correct.get(2)) {
                //correct
                bar.setText("Correct!");
                //clear inputs
                EditText clear = (EditText) findViewById(R.id.input1);
                clear.setText("");
                clear.requestFocus();
                clear = (EditText) findViewById(R.id.input2);
                clear.setText("");
                clear = (EditText) findViewById(R.id.input3);
                clear.setText("");
                //new problem
                updateText();
            } else {
                //wrong
                bar.setText("Wrong!");
            }
        }
        catch(Exception E){
            // do nothing
            bar.setText("Input error");
        }
        bar.show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(count == 0){
            View view = getCurrentFocus();
            view = view.focusSearch(View.FOCUS_UP);
            view.requestFocus();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        try{
            int stringCount = s.length();
            View view = getCurrentFocus();
            if(stringCount>=1){
                //next focus
                View viewDown = view.focusSearch(View.FOCUS_DOWN);
                viewDown.requestFocus();
            }
        }
        catch(Exception E){
            //do nothing
        }
    }


    private static class generateNumbers{
        public ArrayList<Integer> allNumbers = new ArrayList<Integer>();
        public List<Integer> correct = new ArrayList(),incorrect = new ArrayList<Integer>();
        public ArrayList<Integer> hint1 = new ArrayList(),hint2 = new ArrayList(),hint3 = new ArrayList(),hint4 = new ArrayList(),hint5 = new ArrayList();

        public generateNumbers(){
            for(int x = 0;x<10;x++){
                allNumbers.add(x);
            }
            generateNew();
        }
        public void generateNew(){ //generate new numbers
            // shuffle
            Collections.shuffle(allNumbers);
            correct=allNumbers.subList(0,3);
            incorrect=allNumbers.subList(3,10);

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
