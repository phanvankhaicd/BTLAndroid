package com.ptit.ncovihdv.dto.response.crawl.trackcorona;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 19-Jun-2020
 */
@Data
public class TrackCoronaApiResonse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<DataItem> data = null;
}
