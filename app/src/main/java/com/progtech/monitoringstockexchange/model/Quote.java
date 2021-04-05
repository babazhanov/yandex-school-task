package com.progtech.monitoringstockexchange.model;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("c")
    private double mC;
    @SerializedName("h")
    private double mH;
    @SerializedName("l")
    private double mL;
    @SerializedName("o")
    private double mO;
    @SerializedName("pc")
    private double mPc;
    @SerializedName("t")
    private int mT;

    public double getC() {
        return mC;
    }

    public void setC(double c) {
        mC = c;
    }

    public double getH() {
        return mH;
    }

    public void setH(double h) {
        mH = h;
    }

    public double getL() {
        return mL;
    }

    public void setL(double l) {
        mL = l;
    }

    public double getO() {
        return mO;
    }

    public void setO(double o) {
        mO = o;
    }

    public double getPc() {
        return mPc;
    }

    public void setPc(double pc) {
        mPc = pc;
    }

    public int getT() {
        return mT;
    }

    public void setT(int t) {
        mT = t;
    }
}
