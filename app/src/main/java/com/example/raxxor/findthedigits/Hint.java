package com.example.raxxor.findthedigits;

public class Hint {
    String hintDescription;
    int[] hintIntegers;

    Hint(String hintDescription, int[] hintIntegers){
        this.hintDescription=hintDescription;
        this.hintIntegers=hintIntegers;
    }
    public int[] getHintIntegers() {
        return hintIntegers;
    }
    public String getHintDescription() {
        return hintDescription;
    }
}
