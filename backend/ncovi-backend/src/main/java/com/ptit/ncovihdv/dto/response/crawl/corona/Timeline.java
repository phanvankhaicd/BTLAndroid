package com.ptit.ncovihdv.dto.response.crawl.corona;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 19-Jun-2020
 */
@Data
public class Timeline {
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("new_confirmed")
    @Expose
    private Integer newConfirmed;
    @SerializedName("new_recovered")
    @Expose
    private Integer newRecovered;
    @SerializedName("new_deaths")
    @Expose
    private Integer newDeaths;
    @SerializedName("is_in_progress")
    @Expose
    private Boolean isInProgress;
}
