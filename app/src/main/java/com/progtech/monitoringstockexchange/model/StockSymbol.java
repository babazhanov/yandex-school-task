package com.progtech.monitoringstockexchange.model;

import com.google.gson.annotations.SerializedName;

public class StockSymbol {
    @SerializedName("currency")
    private String mCurrency;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("displaySymbol")
    private String mDisplaySymbol;
    @SerializedName("figi")
    private String mFigi;
    @SerializedName("mic")
    private String mMic;
    @SerializedName("symbol")
    private String mSymbol;
    @SerializedName("type")
    private String mType;

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDisplaySymbol() {
        return mDisplaySymbol;
    }

    public void setDisplaySymbol(String displaySymbol) {
        mDisplaySymbol = displaySymbol;
    }

    public String getFigi() {
        return mFigi;
    }

    public void setFigi(String figi) {
        mFigi = figi;
    }

    public String getMic() {
        return mMic;
    }

    public void setMic(String mic) {
        mMic = mic;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
