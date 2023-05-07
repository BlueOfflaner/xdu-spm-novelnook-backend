package com.xdu.nook.api.entity;

import com.xdu.nook.api.enums.PostscriptAdvice;
import com.xdu.nook.api.enums.PostscriptType;
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
    private String advice;
    private String type;

    public boolean checkType(PostscriptType postScriptType){
        return this.type.equals(postScriptType.getType());
    }

    public boolean checkAdvice(PostscriptAdvice advice){
        String advise = advice.getAdvice();
        return this.advice.equals(advise);
    }
}
