package com.example.corona.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryDeclare {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("sick")
    @Expose
    private String sick;
    @SerializedName("cough")
    @Expose
    private String cough;
    @SerializedName("sultry")
    @Expose
    private String sultry;
    @SerializedName("tired")
    @Expose
    private String tired;
    @SerializedName("good")
    @Expose
    private String good;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;

    public HistoryDeclare(Integer id, String createdAt, String sick, String cough, String sultry, String tired, String good, String status, String description) {
        this.id = id;
        this.createdAt = createdAt;
        this.sick = sick;
        this.cough = cough;
        this.sultry = sultry;
        this.tired = tired;
        this.good = good;
        this.status = status;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSick() {
        return sick;
    }

    public void setSick(String sick) {
        this.sick = sick;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getSultry() {
        return sultry;
    }

    public void setSultry(String sultry) {
        this.sultry = sultry;
    }

    public String getTired() {
        return tired;
    }

    public void setTired(String tired) {
        this.tired = tired;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}