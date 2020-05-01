package com.example.corona.Model.Global;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoronaGlobal {

@SerializedName("data")
@Expose
private List<DataG> data = null;
@SerializedName("_cacheHit")
@Expose
private Boolean cacheHit;

public List<DataG> getData() {
return data;
}

public void setData(List<DataG> data) {
this.data = data;
}

public Boolean getCacheHit() {
return cacheHit;
}

public void setCacheHit(Boolean cacheHit) {
this.cacheHit = cacheHit;
}

}