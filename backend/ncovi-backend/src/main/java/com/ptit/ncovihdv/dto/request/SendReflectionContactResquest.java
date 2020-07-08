package com.ptit.ncovihdv.dto.request;

import lombok.Data;

@Data
public class SendReflectionContactResquest {
    private Integer reflectionContactQuestion1;
    private Integer reflectionContactQuestion2;
    private Integer reflectionContactQuestion3;

//  @Override
//  public boolean isValid() {
//    return ObjectUtils.isEmpty(reflectionContactQuestion1)
//        && ObjectUtils.isEmpty(reflectionContactQuestion2)
//        && ObjectUtils.isEmpty(reflectionContactQuestion3);
//  }
}
