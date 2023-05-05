package com.xdu.nook.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostScript {
    private String title;
    private String content;
    private Integer advice;
    private String type;
}
