package com.xdu.nook.api.entity;

import com.xdu.nook.api.enums.InformationSrc;
import com.xdu.nook.api.enums.InformationType;
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
    private InformationType type;
    private Integer advice;
    private InformationSrc src;

}
