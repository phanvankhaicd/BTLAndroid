package com.ptit.ncovihdv.dto.response.crawl.corona;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 19-Jun-2020
 */
@Data
public class Today {
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("confirmed")
    @Expose
    private Integer confirmed;
}
