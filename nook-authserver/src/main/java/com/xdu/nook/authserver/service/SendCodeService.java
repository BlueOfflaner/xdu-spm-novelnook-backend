package com.xdu.nook.authserver.service;

import com.xdu.nook.api.utils.R;

public interface SendCodeService {
    R send(String to);
}
