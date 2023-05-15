package com.example.travelcity.SaveProcessTravel;

public class DataClass {
    private String dataTitle;
    private String dataDesc;
    private String dataDate;
    private String dataImage;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataTitle() {
        return dataTitle;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataDate() {
        return dataDate;
    }
    public String getDataImage() {
        return dataImage;
    }
    public DataClass(String dataTitle, String dataDesc, String dataDate, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataDate = dataDate;
        this.dataImage = dataImage;
    }
    public DataClass(){

    }
}
