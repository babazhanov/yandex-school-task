package com.progtech.monitoringstockexchange.model;

import com.google.gson.annotations.SerializedName;

public class CompanyProfile {

    @SerializedName("country")
    private String mCountry;
    @SerializedName("currency")
    private String mCurrency;
    @SerializedName("exchange")
    private String mExchange;
    @SerializedName("ipo")
    private String mIpo;
    @SerializedName("marketCapitalization")
    private double mMarketCapitalization;
    @SerializedName("name")
    private String mName;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("shareOutstanding")
    private double mShareOutstanding;
    @SerializedName("ticker")
    private String mTicker;
    @SerializedName("weburl")
    private String mWeburl;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("finnhubIndustry")
    private String mFinnhubIndustry;

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public String getExchange() {
        return mExchange;
    }

    public void setExchange(String exchange) {
        mExchange = exchange;
    }

    public String getIpo() {
        return mIpo;
    }

    public void setIpo(String ipo) {
        mIpo = ipo;
    }

    public double getMarketCapitalization() {
        return mMarketCapitalization;
    }

    public void setMarketCapitalization(int marketCapitalization) {
        mMarketCapitalization = marketCapitalization;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public double getShareOutstanding() {
        return mShareOutstanding;
    }

    public void setShareOutstanding(double shareOutstanding) {
        mShareOutstanding = shareOutstanding;
    }

    public String getTicker() {
        return mTicker;
    }

    public void setTicker(String ticker) {
        mTicker = ticker;
    }

    public String getWeburl() {
        return mWeburl;
    }

    public void setWeburl(String weburl) {
        mWeburl = weburl;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    public String getFinnhubIndustry() {
        return mFinnhubIndustry;
    }

    public void setFinnhubIndustry(String finnhubIndustry) {
        mFinnhubIndustry = finnhubIndustry;
    }
}
