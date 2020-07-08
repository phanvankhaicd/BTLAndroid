package com.ptit.ncovihdv.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HealthMonitorRequest implements IRequestData {

    private Integer healthMonitorSick;
    private Integer healthMonitorCough;
    private Integer healthMonitorSultry;
    private Integer healthMonitorTired;
    private Integer healthMonitorGood;

    @Override
    public boolean isValid() {
        if (healthMonitorGood == 1) {
            if (healthMonitorSick == 0 &&
                    healthMonitorCough == 0 &&
                    healthMonitorSultry == 0 &&
                    healthMonitorTired == 0)
                return true;
            else
                return false;
        }
        return true;
    }
}
