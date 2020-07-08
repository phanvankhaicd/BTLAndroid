package com.ptit.ncovihdv.dto.response.crawl.corona;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 19-Jun-2020
 */
@Data
public class CoronaApiResponse {
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("today")
    @Expose
    private Today today;
    @SerializedName("latest_data")
    @Expose
    private LatestData latestData;
    @SerializedName("timeline")
    @Expose
    private List<Timeline> timeline = null;
}
