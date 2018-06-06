package com.example.raxxor.findthedigits;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HintsAdapter extends RecyclerView.Adapter<HintsAdapter.ViewHolder> {

    private List<Hint> allHints;

    HintsAdapter(List<Hint> allHints){
        this.allHints=allHints;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout view = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.hint, parent,false);
        return new HintsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hint hint = allHints.get(position);
        ((TextView)holder.layout.findViewById(R.id.hint_textView_left) ).setText(String.valueOf(hint.hintIntegers[0]));
        ((TextView)holder.layout.findViewById(R.id.hint_textView_middle) ).setText(String.valueOf(hint.hintIntegers[1]));
        ((TextView)holder.layout.findViewById(R.id.hint_textView_right) ).setText(String.valueOf(hint.hintIntegers[2]));
        ((TextView)holder.layout.findViewById(R.id.hint_description) ).setText(hint.hintDescription);
    }

    @Override
    public int getItemCount() {
        return allHints.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;

        ViewHolder(View itemView) {
            super(itemView);
            layout = (ConstraintLayout) itemView;
        }
    }
}
