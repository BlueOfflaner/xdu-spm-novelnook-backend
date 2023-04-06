package com.xdu.nook.authserver.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendInfo {
    public String to;
    public String from;

    public String context;
}
