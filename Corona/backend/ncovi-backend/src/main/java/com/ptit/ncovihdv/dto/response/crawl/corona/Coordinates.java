package com.ptit.ncovihdv.dto.response.crawl.corona;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 19-Jun-2020
 */
@Data
public class Coordinates {
    @SerializedName("latitude")
    @Expose
    private Integer latitude;
    @SerializedName("longitude")
    @Expose
    private Integer longitude;
}
