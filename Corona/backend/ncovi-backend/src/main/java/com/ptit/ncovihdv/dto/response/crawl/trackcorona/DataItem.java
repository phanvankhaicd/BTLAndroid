package com.ptit.ncovihdv.dto.response.crawl.trackcorona;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 19-Jun-2020
 */
@Data
public class DataItem {
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("dead")
    @Expose
    private Integer dead;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("updated")
    @Expose
    private String updated;
}
