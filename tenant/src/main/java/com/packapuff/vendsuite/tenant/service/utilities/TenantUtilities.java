package com.packapuff.vendsuite.tenant.service.utilities;

import org.springframework.stereotype.Component;

@Component
public class TenantUtilities {

    public boolean isBlank(String target) {
        if (null == target) {
            return true;
        }
        return "".equals(target.trim());
    }

}
