package com.xdu.nook.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Information {
    private Long userId;
    private Long materialId;
    private String callNumber; // GUID
    private String type;

    private String advice;
    private String src;

}
