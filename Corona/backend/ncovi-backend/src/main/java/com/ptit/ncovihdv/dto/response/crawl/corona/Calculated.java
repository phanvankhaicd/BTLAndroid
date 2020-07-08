package com.ptit.ncovihdv.dto.response.crawl.corona;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 19-Jun-2020
 */
@Data
public class Calculated {
    @SerializedName("death_rate")
    @Expose
    private Integer deathRate;
    @SerializedName("recovery_rate")
    @Expose
    private Double recoveryRate;
    @SerializedName("recovered_vs_death_ratio")
    @Expose
    private Object recoveredVsDeathRatio;
    @SerializedName("cases_per_million_population")
    @Expose
    private Integer casesPerMillionPopulation;
}
