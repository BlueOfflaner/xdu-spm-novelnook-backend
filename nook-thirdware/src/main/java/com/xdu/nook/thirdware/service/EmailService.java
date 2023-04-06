package com.xdu.nook.thirdware.service;

import com.xdu.nook.api.utils.R;
import com.xdu.nook.thirdware.utils.SendInfo;
import org.springframework.stereotype.Service;

public interface EmailService {
    public R sendEmail(SendInfo sendInfo);

    public R send(SendInfo sendInfo);
}
